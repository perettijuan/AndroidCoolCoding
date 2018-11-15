package com.jpp.androidchallenge.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * The contract defined by the application to handle all the storage data.
 */
public class AndroidChallengeContract {

    public static final String AUTHORITY = "com.jpp.androidchallenge.provider";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    /**
     * Interface that contains the names of all tables defined in the storage layer.
     */
    interface Tables {

        static final String TASK = "task";
    }

    /**
     * Interface that defines the columns that the TASK table contains.
     */
    interface TaskColumns {
        static final String TASK_COLOR_IDENTIFIER = "task_color_identifier";
        static final String TASK_DEFINITION = "task_desfinition";
    }


    /**
     * Interface that defines the  public contract that the Tasks has for the entire application.
     * Each element that needs to access to the storage to handle a Task, needs to use this contract.
     */
    public static class Tasks implements TaskColumns, BaseColumns {


        // The URI used to identify actions related to Task
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(Tables.TASK).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.androidchallenge.tasks";

        public static final String DEFAULT_SORT = _ID;

        public static String getTaskId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildTaskUri(String id) {
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }
    }
}
