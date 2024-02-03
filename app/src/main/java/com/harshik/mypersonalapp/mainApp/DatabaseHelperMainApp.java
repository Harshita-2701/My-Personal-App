package com.harshik.mypersonalapp.mainApp;

//import static com.harshik.mypersonalapp.contants.DbConstants.TABLE_USER_DETAILS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.harshik.mypersonalapp.contants.DbConstants.*;

public class DatabaseHelperMainApp extends SQLiteOpenHelper {

    public DatabaseHelperMainApp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Check if the database exists
    public boolean isDatabaseExists(Context context) {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).getPath(), null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            // Database does not exist
        }

        if (db != null) {
            db.close();
        }

        return db != null;
    }

    // Check if a specific table exists
    public boolean isTableExists(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
        boolean tableExists = cursor.getCount() > 0;
        cursor.close();
        return tableExists;
    }

    // Check if at least one record exists in the user_details table
    public boolean isUserDetailsRecordExists() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + MAIN_APP_TABLE_USER_DETAILS + " LIMIT 1", null);
        boolean recordExists = cursor.getCount() > 0;
        cursor.close();
        return recordExists;
    }

    // SQL statement to create the user_details table
    private static final String CREATE_MAIN_APP_TABLE_USER_DETAILS =
            "CREATE TABLE " + MAIN_APP_TABLE_USER_DETAILS + " (" +
                    MAIN_APP_COLUMN_NAME + " VARCHAR(255) NOT NULL PRIMARY KEY, " +
                    MAIN_APP_COLUMN_GENDER + " VARCHAR(10) NOT NULL, " +
                    MAIN_APP_COLUMN_AGE + " INTEGER NOT NULL);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user_details table
        db.execSQL(CREATE_MAIN_APP_TABLE_USER_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    // Add a method to insert user details into the database
    public Boolean insertUserDetails(String name, String gender, int age) {

        ContentValues values = new ContentValues();
        values.put(MAIN_APP_COLUMN_NAME, name);
        values.put(MAIN_APP_COLUMN_GENDER, gender);
        values.put(MAIN_APP_COLUMN_AGE, age);

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            // Insert the new row, and return the primary key value of the new row
            db.insert(MAIN_APP_TABLE_USER_DETAILS, null, values);

            // Close the database after use
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
