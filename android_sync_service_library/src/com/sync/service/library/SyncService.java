package com.sync.service.library;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ResultReceiver;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Service that recreates the logic given by {@link IntentService} with the
 * singularity of allowing parallel requests.
 * 
 * @author Juan P. Peretti
 * 
 */
public abstract class SyncService extends Service {


    private static final String TAG = SyncService.class.getName();

    public static final String INTENT_ACTION = "com.sync.service.library.INTENT_ACTION";

    // Looper to be associated to the HandlerThread.
    private volatile Looper mServiceLooper = null;
    // Handler to associate the Looper
    private volatile WorkerHandler mWorkerHandler = null;
    // Execution service id
    private int mExecutionId = -1;
    private ServiceBroadcastReceiver mReceiver;

    /**
     * A {@link Handler} that is associated to a Looper. This Handler will be
     * the one that actually executes the work.
     */
    @SuppressLint("HandlerLeak")
    private final class WorkerHandler extends Handler {


        /**
         * Class constructor.
         * 
         * @param looper
         *            - the looper to attach this handler to.
         */
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            onHandleIntent((Intent) msg.obj);
            stopSelf();
        }

    }

    /**
     * A {@link Runnable} that will execute tasks that can be executed in
     * parallel with others.
     */
    private final class WorkerParallelRunner implements Runnable {


        private Intent mTaskIntent = null;

        public WorkerParallelRunner(Intent intent) {
            mTaskIntent = intent;
        }

        @Override
        public void run() {
            if (mTaskIntent != null) {
                onHandleIntent(mTaskIntent);
            }
            stopSelf();
        }
    }

    /**
     * Default class constructor.<br>
     * Do not call this constructor from your code.
     */
    public SyncService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Does not return a binder on purpose.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
         * Start a thread that can be used to attach a Looper. This Looper will
         * be used to associate handlers that manage the requests.
         */
        HandlerThread thread = new HandlerThread(TAG);
        thread.start();

        /*
         * Associate the Looper that will manage the Handlers to the started
         * thread.
         */
        mServiceLooper = thread.getLooper();
        mWorkerHandler = new WorkerHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IServiceRequest request = intent.getExtras().getParcelable(SyncConstants.EXTRA_SERVICE_REQUEST_ID);

        if (request.canRunInParallel()) {
            /*
             * If the request can run in parallel with any other one, start a
             * new Runnable that executes the request.
             */
            Runnable parallelRunner = new WorkerParallelRunner(intent);
            new Thread(parallelRunner, TAG).start();
        } else {
            /*
             * If the request can not be executed in parallel with another, it
             * will be enqueued in the Handler to be executed when possible.
             */
            Message msg = mWorkerHandler.obtainMessage();
            mExecutionId = startId;
            msg.arg1 = startId;
            msg.obj = intent;
            mWorkerHandler.sendMessage(msg);

        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop and destroy the looper attached to the service
        mServiceLooper.quit();
    }

    /**
     * Method called on a different thread that the one that makes the request.
     * This is the place where the actual work is done.<br>
     * When the work is done, the message will be delivered to the
     * {@link ResultReceiver} attached to the request (if any).
     * 
     * @param intent
     */
    protected void onHandleIntent(Intent intent) {
        registerReceiver();

        // Extract the IServiceRequest to know what executor use
        IServiceRequest serviceRequest = intent.getParcelableExtra(SyncConstants.EXTRA_SERVICE_REQUEST_ID);
        // Extract the ResultReceiver that will be used to notify
        ResultReceiver resultReceiver = intent.getParcelableExtra(SyncConstants.EXTRA_STATUS_RECEIVER);
        // Extract any extra data that comes in the Intent
        Bundle extras = intent.getExtras();

        
            /*
             * Notify the beginning of the work and send any information to the
             * listener.
             */
            extras = prepareForExecution(extras);
            if (resultReceiver != null) {
            resultReceiver.send(SyncStatus.STATUS_RUNNING.getValue(), extras);}

            try {
                Executor executor = getExecutor(serviceRequest);

                if (executor == null) {
                    if (resultReceiver != null) {
                        extras.putString(Intent.EXTRA_TEXT,
                                "Invalid EXTRA_SERVER_REQUEST_ID: " + serviceRequest.toString());
                        resultReceiver.send(SyncStatus.STATUS_ERROR.getValue(), extras);
                    }
                    return;
                }
                extras = executor.execute(extras, getApplicationContext());

            } catch (ServiceException e) {
                if (resultReceiver != null) {

                	if(extras == null) {
                		extras = new Bundle();
                	}
                    extras.putSerializable(SyncConstants.EXTRA_SERVICE_EXCEPTION, e);
                    extras.putParcelable(SyncConstants.EXTRA_SERVICE_REQUEST_ID, serviceRequest);
                    resultReceiver.send(SyncStatus.STATUS_ERROR.getValue(), extras);
                    resultReceiver = null;
                }
            }

            if (extras == null) {
                extras = new Bundle();
            }
            extras.putParcelable(SyncConstants.EXTRA_SERVICE_REQUEST_ID, serviceRequest);

            if (resultReceiver != null) {
                resultReceiver.send(SyncStatus.STATUS_FINISHED.getValue(), extras);
                resultReceiver = null;
            }
            unregisterReceiver();
 
    }

    private void registerReceiver() {
        mReceiver = new ServiceBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(INTENT_ACTION);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, intentFilter);
    }

    public final class ServiceBroadcastReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            if (INTENT_ACTION.equals(intent.getAction())) {
                stopAll();
            }
        }
    }

    private void stopAll() {
        clearQueue();
        if (mExecutionId != -1) {
            unregisterReceiver();
            stopSelf(mExecutionId);
        }
    }

    private void unregisterReceiver() {
        if (mReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
        }
    }

    /**
     * Used to clear the messages queue
     */
    private void clearQueue() {
        mWorkerHandler.removeMessages(0);
    }

    /**
     * Called just before the work execution starts. Receives a {@link Bundle}
     * with the data received by the SyncService and any Bundle that this method
     * prepares will be sent to the request listener. <br>
     * Note that the same object that this method receives as parameter (the
     * {@link Bundle} with the data received by the SyncService) will be sent to
     * the listener. <br>
     * Bare in mind that the idea is not to remove information from it, but to
     * append the info to the Bundle.
     * 
     * 
     * @param extras
     *            - the {@link Bundle} with any extra data received.
     * @return - a prepared {@link Bundle} with data to be sent to the listener.
     */
    protected abstract Bundle prepareForExecution(Bundle extras);

    /**
     * Find the proper executor for the operation in progress
     * 
     * @param req
     *            - the {@link IServiceRequest} made
     * @return - the {@link Executor} found (if not found, a null value MUST BE
     *         returned and an exception will be thrown)
     */
    protected abstract Executor getExecutor(IServiceRequest req);

}
