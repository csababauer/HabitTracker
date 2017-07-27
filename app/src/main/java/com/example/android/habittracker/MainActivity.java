package com.example.android.habittracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittracker.Data.Contract;
import com.example.android.habittracker.Data.DbHelper;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private DbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new DbHelper(this);

        displayDatabaseInfo();
        }

        private void displayDatabaseInfo(){

            // Create and/or open a database to read from it
            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            // project is a String array which contains all the columns I am interested
            String [] projection = {
                    Contract.RunEntry._ID,
                    Contract.RunEntry.COLUMN_DAY_NAME,
                    Contract.RunEntry.COLUMN_DISTANCE
            };
            //and then pass the projection to the query method
            Cursor cursor = db.query(
                    Contract.RunEntry.TABLE_NAME,   // The table to query
                    projection,                     // The columns to return
                    null,                           // The columns for the WHERE clause
                    null,                           // The values for the WHERE clause
                    null,                           // Don't group the rows
                    null,                           // Don't filter by row groups
                    null                            // The sort order
            );


            TextView displayView = (TextView) findViewById(R.id.id);


            try {
                // Display the number of rows in the Cursor (which reflects the number of rows in the
                // runs table in the database).
                // Create a header in the Text View that looks like this:
                //
                // The runs table contains <number of rows in Cursor>.
                // _id - name of day - distance
                //
                // In the while loop below, iterate through the rows of the cursor and display
                // the information from each column in this order.
                displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
                displayView.append(Contract.RunEntry._ID + " - " +
                        Contract.RunEntry.COLUMN_DAY_NAME + " - " +
                        Contract.RunEntry.COLUMN_DISTANCE + "\n");

                // Figure out the index of each column
                int idColumnIndex = cursor.getColumnIndex(Contract.RunEntry._ID);
                int dayColumnIndex = cursor.getColumnIndex(Contract.RunEntry.COLUMN_DAY_NAME);
                int distanceColumnIndex = cursor.getColumnIndex(Contract.RunEntry.COLUMN_DISTANCE);

                // Iterate through all the returned rows in the cursor
                while (cursor.moveToNext()) {
                    // Use that index to extract the String or Int value of the word
                    // at the current row the cursor is on.
                    int currentID = cursor.getInt(idColumnIndex);
                    String currentDay = cursor.getString(dayColumnIndex);
                    int currentDistance = cursor.getInt(distanceColumnIndex);
                    // Display the values from each column of the current row in the cursor in the TextView
                    displayView.append(("\n" + currentID + " - " +
                            currentDay + " - " +
                            currentDistance));
                }
            }
            finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                cursor.close();
            }
    }

}
