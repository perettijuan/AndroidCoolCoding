package com.jpp.androidchallenge.background;

import android.content.ContentValues;
import android.content.Context;

import com.jpp.androidchallenge.model.Task;
import com.jpp.androidchallenge.model.TaskColor;
import com.jpp.androidchallenge.provider.AndroidChallengeContract;

/**
 * A BackgroundTaskExecutor that will add a new task to the data base.
 */
public class AddTaskExecutor extends BackgroundTaskExecutor<Task> {

    public AddTaskExecutor(Context context, IBackgroundExecutionListener listener) {
        super(context, listener);
    }

    @Override
    protected int executeBackgroundImpl(Task... param) {
        int result = RESULT_ERROR;
        if (param != null) {
            for (Task task : param) {
                try {
                    TaskColor color = task.getTaskColor();
                    ContentValues values = new ContentValues();
                    values.put(AndroidChallengeContract.Tasks.TASK_COLOR_IDENTIFIER, color.getIdentifier());
                    values.put(AndroidChallengeContract.Tasks.TASK_DEFINITION, task.getTask());
                    mContentResolver.insert(AndroidChallengeContract.Tasks.CONTENT_URI, values);
                    result = RESULT_OK;
                } catch (Exception e) {
                    result = RESULT_ERROR;
                }
            }
        }
        return result;
    }
}
