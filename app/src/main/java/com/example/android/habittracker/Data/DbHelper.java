package com.example.android.habittracker.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.Data.Contract.RunEntry;

/**
 * it imports the definitions of the Contract activity's
 * this helps other classes to use RunEntry
 */
/** this helps other classes to use RunEntry*/


/**
 * Created by Csaba on 27/07/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    /** Name of the database file */
    private static final String DATABASE_NAME = "dailyrun.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link DbHelper}.
     *
     * @param context of the app
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + RunEntry.TABLE_NAME + " ("
                + RunEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RunEntry.COLUMN_DAY_NAME + " TEXT NOT NULL, "
                + RunEntry.COLUMN_DISTANCE + " INTEGER NOT NULL, )";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /** this method insert record */
    public void insertRecord(String day, int distance) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues habitValues = new ContentValues();
        habitValues.put(RunEntry.COLUMN_DAY_NAME, day);
        habitValues.put(RunEntry.COLUMN_DISTANCE, distance);

        sqLiteDatabase.insert(Contract.RunEntry.TABLE_NAME, null, habitValues);
    }

    public Cursor queryAllRecords() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] projection = {
                Contract.RunEntry._ID,
                RunEntry.COLUMN_DAY_NAME,
                RunEntry.COLUMN_DISTANCE,
        };

        return sqLiteDatabase.query(
                Contract.RunEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }


    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
