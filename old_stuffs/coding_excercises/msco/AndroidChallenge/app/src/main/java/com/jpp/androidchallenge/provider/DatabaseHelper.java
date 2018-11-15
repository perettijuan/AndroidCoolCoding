package com.jpp.androidchallenge.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jpp.androidchallenge.provider.AndroidChallengeContract.Tables;
import com.jpp.androidchallenge.provider.AndroidChallengeContract.Tasks;

/**
 * Abstraction used to handle the database used to storage the application's data.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "androidchallengetasks";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // sentence to create the TASK table
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + Tables.TASK + "("
                + Tasks._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Tasks.TASK_COLOR_IDENTIFIER + " INTEGER,"
                + Tasks.TASK_DEFINITION + " TEXT)");

        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing for the moment.
    }
}
