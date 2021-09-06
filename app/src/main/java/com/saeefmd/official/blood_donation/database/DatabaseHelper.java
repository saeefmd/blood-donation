package com.saeefmd.official.blood_donation.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    // Database name
    private static final String DATABASE_NAME = "quotes_db";

    // Database version
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE " +
                Quote.TABLE_NAME + " ( " +
                Quote.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Quote.COLUMN_QUOTE + " TEXT, " +
                Quote.COLUMN_AUTHOR + " TEXT)";

        // Create table
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertQuote(String quote, String author) {

        // Open database as writable
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Quote.COLUMN_QUOTE, quote);
        values.put(Quote.COLUMN_AUTHOR, author);

        // Insert new values into database and keep the row id
        long id = db.insert(Quote.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public Quote getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Quote.TABLE_NAME,
                new String[]{Quote.COLUMN_ID,Quote.COLUMN_QUOTE, Quote.COLUMN_AUTHOR},
                Quote.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Quote note = new Quote(
                cursor.getInt(cursor.getColumnIndex(Quote.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Quote.COLUMN_QUOTE)),
                cursor.getString(cursor.getColumnIndex(Quote.COLUMN_AUTHOR)));

        // close the db connection
        cursor.close();

        return note;
    }
}
