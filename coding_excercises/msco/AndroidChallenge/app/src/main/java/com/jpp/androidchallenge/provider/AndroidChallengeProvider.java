package com.jpp.androidchallenge.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * ContentProvider used to manage the storage layer
 */
public class AndroidChallengeProvider extends ContentProvider {

    private static final String TAG = AndroidChallengeProvider.class.getName();

    // Match for all TASKS
    private static final int TASKS = 10001;
    // Match for specific TASK ID
    private static final int TASK_ID = 10002;

    // UriMatcher instance
    private static final UriMatcher mUriMatcher = buildUriMatcher();

    // Database helper instance.
    private DatabaseHelper mDatabaseHelper;


    /**
     * Builds a UriMatcher that will be used to match each Uri received with a given identifier.
     */
    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = AndroidChallengeContract.AUTHORITY;

        // add matches for TASKS
        matcher.addURI(authority, AndroidChallengeContract.Tables.TASK, TASKS);
        matcher.addURI(authority, AndroidChallengeContract.Tables.TASK + "/*", TASK_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return ((mDatabaseHelper == null) ? false : true);
    }

    @Override
    public String getType(Uri uri) {
        final int match = mUriMatcher.match(uri);
        switch (match) {
            case TASKS:
                return AndroidChallengeContract.Tasks.CONTENT_TYPE;
            case TASK_ID:
                return AndroidChallengeContract.Tasks.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        final int match = mUriMatcher.match(uri);
        switch (match) {
            default:
                /* Almost all cases can be managed with a selection builder */
                final SelectionBuilder builder = buildSimpleSelection(uri);
                Cursor cursor = builder.where(selection, selectionArgs).query(db, projection, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
        }
    }


    /**
     * Build a simple SelectionBuilder to match the requested Uri.
     */
    private SelectionBuilder buildSimpleSelection(Uri uri) {
        final SelectionBuilder builder = new SelectionBuilder();
        final int match = mUriMatcher.match(uri);
        switch (match) {
            case TASKS:
                return builder.table(AndroidChallengeContract.Tables.TASK);
            case TASK_ID:
                final String taskId = AndroidChallengeContract.Tasks.getTaskId(uri);
                return builder.table(AndroidChallengeContract.Tables.TASK).where(AndroidChallengeContract.Tasks._ID + "=?", taskId);
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        final int match = mUriMatcher.match(uri);
        switch (match) {
            case TASKS:
                Log.d(TAG, "Insert new Task " + values.toString());
                db.insertOrThrow(AndroidChallengeContract.Tables.TASK, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return AndroidChallengeContract.Tasks.buildTaskUri(values.getAsString(AndroidChallengeContract.Tasks._ID));
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int retVal = -1;
        final int match = mUriMatcher.match(uri);
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        switch (match) {
            default:
                final SelectionBuilder builder = buildSimpleSelection(uri);
                retVal = builder.where(selection, selectionArgs).delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
        }
        return retVal;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int retVal = -1;
        final int match = mUriMatcher.match(uri);
        switch (match) {
            default:
                final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
                final SelectionBuilder builder = buildSimpleSelection(uri);
                retVal = builder.where(selection, selectionArgs).update(db, values);
                getContext().getContentResolver().notifyChange(uri, null);
        }
        return retVal;
    }
}
