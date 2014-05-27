package com.jpp.foods.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.jpp.foods.provider.FoodsAtLifesumContract.Tables;

/**
 * Database helper that represents the actual database.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodsAtLifesumDatabaseHelper extends SQLiteOpenHelper {


    // Database Name
    static final String DATABASE_NAME = "foods.db";

    private static final int DATABASE_VERSION = 1;

    public FoodsAtLifesumDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the feeds table
        db.execSQL("CREATE TABLE " + Tables.FOODS + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FoodsAtLifesumContract.Foods.CATEGORY_ID + " INTEGER NOT NULL,"
                + FoodsAtLifesumContract.Foods.SERVER_ID + " INTEGER NOT NULL,"
                + FoodsAtLifesumContract.Foods.TITLE + " TEXT,"
                + FoodsAtLifesumContract.Foods.CATEGORY + " TEXT,"
                + FoodsAtLifesumContract.Foods.SODIUM + " INTEGER,"
                + FoodsAtLifesumContract.Foods.CARBOHYDRATES + " INTEGER,"
                + FoodsAtLifesumContract.Foods.CALORIES + " INTEGER,"
                + FoodsAtLifesumContract.Foods.SUGAR + " INTEGER,"
                + FoodsAtLifesumContract.Foods.CHOLESTEROL + " INTEGER,"
                + FoodsAtLifesumContract.Foods.POTASSIUM + " INTEGER,"
                + FoodsAtLifesumContract.Foods.FIBER + " INTEGER,"
                + FoodsAtLifesumContract.Foods.FAT + " INTEGER,"
                + FoodsAtLifesumContract.Foods.PROTEIN + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // In blank on purpose
    }

}
