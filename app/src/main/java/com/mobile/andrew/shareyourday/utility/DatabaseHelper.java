package com.mobile.andrew.shareyourday.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by andre on 08/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database info
    private static final String DATABASE_NAME = "myhomeworktutorial.db";
    private static final int DATABASE_VERSION = 2;

    // Assignments
    public static final String TABLE_ENTRIES = "entries";
    public static final String COLUMN_ENTRY_ID = "entry_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE_RESOURCE = "image_resource";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";

    private static final String DATABASE_CREATE_ENTRIES =

                "CREATE TABLE " + TABLE_ENTRIES +
                    "(" +
                    COLUMN_ENTRY_ID + " integer primary key autoincrement, " +
                    COLUMN_TITLE + " text not null, " +
                        COLUMN_IMAGE_RESOURCE + " integer, " +
                        COLUMN_DESCRIPTION + " text not null, " +
                        COLUMN_DATE + " text not null" +
                    ");";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	/*
     	* When the database gets upgraded you should handle the update to make sure there is no data loss.
     	* This is the default code you put in the upgrade method, to delete the table and call the oncreate again.
     	*/
        Log.w(DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
        onCreate(db);
    }
}
