package com.jpp.androidchallenge.background;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Abstract class used to define a signature for all the classes that need to
 * execute a background operation (in a different thread that the caller thread).
 * It will execute the task in a background thread and will notify to any registered
 * client on the caller thread.
 *
 * @param <T> - the type of data to process in the operation.
 */
public abstract class BackgroundTaskExecutor<T> {

    static final int RESULT_OK = 1;
    static final int RESULT_ERROR = 2;

    protected ContentResolver mContentResolver;
    // Use a weak reference since this can be an Activity or Fragment
    private WeakReference<IBackgroundExecutionListener> mListenerReference;


    /**
     * Class constructor.
     *
     * @param context  - a reference to a context
     * @param listener - if this parameter is not null, then it will receive all notifications.
     */
    public BackgroundTaskExecutor(Context context, IBackgroundExecutionListener listener) {
        if (listener != null) {
            mListenerReference = new WeakReference<IBackgroundExecutionListener>(listener);
        }

        mContentResolver = context.getContentResolver();
    }

    /**
     * Method called to actually execute the background operation.
     *
     * @param params - the type of data to process.
     */
    public void execute(T... params) {
        BackgroundExecutor executor = new BackgroundExecutor();
        executor.execute(params);
    }

    /**
     * This is an AsyncTask that takes care of executing the background execution
     * and notifying the result to the calling thread.
     */
    private class BackgroundExecutor extends AsyncTask<T, Void, Integer> {

        @Override
        protected Integer doInBackground(T... params) {
            return executeBackgroundImpl(params);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            processResult(integer);
        }
    }


    /**
     * Called in the client's thread to notify the result (if any listener is interested).
     *
     * @param result - the integer that identifies the result.
     */
    private void processResult(int result) {
        if (mListenerReference != null) {
            IBackgroundExecutionListener listener = mListenerReference.get();
            switch (result) {
                case RESULT_OK:
                    listener.onSuccess();
                    break;
                case RESULT_ERROR:
                    listener.onError();
                    break;
                default:
                    throw new IllegalArgumentException("You need to implement the result correctly");
            }
        }
    }

    /**
     * Method actually called in the background thread to execute the needed task.
     *
     * @param param - the parameters needed by the executor.
     * @return - the result obtained after the task.
     */
    protected abstract int executeBackgroundImpl(T... param);
}
