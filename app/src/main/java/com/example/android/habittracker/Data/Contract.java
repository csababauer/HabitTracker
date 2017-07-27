package com.example.android.habittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by Csaba on 27/07/2017.
 */

public class Contract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private Contract() {}

    /**
     * Inner class that defines constant values for the database table.
     * Each entry in the table represents a single item.
     */
    public static final class RunEntry implements BaseColumns {

        /** Name of database table for each runs */
        public final static String TABLE_NAME = "runs";

        /**
         * Unique ID number for the daily run (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Day of the week Type: TEXT
         */
        public final static String COLUMN_DAY_NAME ="day";

        /**
         * Distance
         *
         * Type: INTEGER
         */
        public final static String COLUMN_DISTANCE = "distance";


    }
}
