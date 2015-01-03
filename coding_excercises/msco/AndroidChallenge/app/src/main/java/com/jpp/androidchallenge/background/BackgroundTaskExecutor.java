package com.jpp.androidchallenge.background;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;


public abstract class BackgroundTaskExecutor<T> {

    static final int RESULT_OK = 1;
    static final int RESULT_ERROR = 2;

    protected ContentResolver mContentResolver;
    private WeakReference<IBackgroundExecutionListener> mListenerReference;


    public BackgroundTaskExecutor(Context context, IBackgroundExecutionListener listener) {
        if (listener != null) {
            mListenerReference = new WeakReference<IBackgroundExecutionListener>(listener);
        }

        mContentResolver = context.getContentResolver();
    }

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
