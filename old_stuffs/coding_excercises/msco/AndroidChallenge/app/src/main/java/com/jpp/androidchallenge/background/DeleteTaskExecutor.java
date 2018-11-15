package com.jpp.androidchallenge.background;

import android.content.Context;

import com.jpp.androidchallenge.model.Task;
import com.jpp.androidchallenge.provider.AndroidChallengeContract;

/**
 * A BackgroundTaskExecutor that will delete a Task from the local storage.
 */
public class DeleteTaskExecutor extends BackgroundTaskExecutor<Task> {

    public DeleteTaskExecutor(Context context, IBackgroundExecutionListener listener) {
        super(context, listener);
    }

    @Override
    protected int executeBackgroundImpl(Task... param) {
        int result = RESULT_ERROR;
        if (param != null) {
            for (Task task : param) {
                try {
                    int id = task.getId();
                    mContentResolver.delete(AndroidChallengeContract.Tasks.CONTENT_URI, AndroidChallengeContract.Tasks._ID + "=?", new String[]{String.valueOf(id)});
                    result = RESULT_OK;
                } catch (Exception e) {
                    result = RESULT_ERROR;
                }
            }
        }
        return result;
    }
}
