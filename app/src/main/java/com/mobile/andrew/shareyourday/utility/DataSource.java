package com.mobile.andrew.shareyourday.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.mobile.andrew.shareyourday.model.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 08/03/2016.
 */
public class DataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] entriesAllColumns = {DatabaseHelper.COLUMN_ENTRY_ID, DatabaseHelper.COLUMN_TITLE, DatabaseHelper.COLUMN_IMAGE_RESOURCE, DatabaseHelper.COLUMN_DESCRIPTION, DatabaseHelper.COLUMN_DATE};


    public DataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    // Opens the database to use it
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close() {
        dbHelper.close();
    }

    public long createEntry(String title, int imageResource, String description, String date) {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, title);
        values.put(DatabaseHelper.COLUMN_IMAGE_RESOURCE, imageResource);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(DatabaseHelper.COLUMN_DATE, date);
        long insertId = database.insert(DatabaseHelper.TABLE_ENTRIES, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();
        return insertId;
    }

    public void deleteEntry(Entry entry) {
        if (!database.isOpen())
            open();

        database.delete(DatabaseHelper.TABLE_ENTRIES, DatabaseHelper.COLUMN_ENTRY_ID + " =?", new String[]{Long.toString(entry.getId())});

        if (database.isOpen())
            close();

    }

    public void updateEntry(Entry entry) {
        if (!database.isOpen())
            open();

        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.COLUMN_TITLE, entry.getEntryTitle());
        args.put(DatabaseHelper.COLUMN_IMAGE_RESOURCE, entry.getImageResource());
        args.put(DatabaseHelper.COLUMN_DESCRIPTION, entry.getDescription());
        args.put(DatabaseHelper.COLUMN_DATE, DateConverter.convertDateToString(entry.getDate()));

        database.update(DatabaseHelper.TABLE_ENTRIES, args, DatabaseHelper.COLUMN_ENTRY_ID + "=?", new String[]{Long.toString(entry.getId())});

        if (database.isOpen())
            close();
    }

    public List<Entry> getAllEntriesFromDatabase() {
        if (!database.isOpen())
            open();

        List<Entry> entries = new ArrayList<Entry>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_ENTRIES, entriesAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Entry entry = cursorToEntry(cursor);
            entries.add(entry);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        if (database.isOpen())
            close();

        return entries;
    }

    private Entry cursorToEntry(Cursor cursor) {
        try {
            Entry entry = new Entry();
            entry.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ENTRY_ID)));
            entry.setEntryTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE)));
            entry.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE_RESOURCE)));
            entry.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION)));
            String dateString = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE));
            entry.setDate(DateConverter.convertStringToDate(dateString));
            return entry;
        } catch (CursorIndexOutOfBoundsException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    public Entry getAssignment(long columnId) {
        if (!database.isOpen())
            open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_ENTRIES, entriesAllColumns, DatabaseHelper.COLUMN_ENTRY_ID + "=?", new String[]{Long.toString(columnId)}, null, null, null);

        cursor.moveToFirst();
        Entry entry = cursorToEntry(cursor);
        cursor.close();

        if (database.isOpen())
            close();

        return entry;
    }
}
