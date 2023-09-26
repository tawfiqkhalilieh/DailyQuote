package com.example.dailyquote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "quotesdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "quotes";
    private static final String ID_COL = "id";
    private static final String QUOTE_COL = "quote";
    private static final String AUTHOR_COL = "author";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + QUOTE_COL + " TEXT NOT NULL,"
                + AUTHOR_COL + " TEXT)");
    }


    // this method is use to add new user to our sqlite database.
    public void insertQuote(
            String quote, String author
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(QUOTE_COL, quote);
        values.put(AUTHOR_COL, author);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<HashMap<String, String>> getAllQuotes() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME  , null);


        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> quote = new HashMap<String, String>();
                quote.put("id", cursor.getString(0));
                quote.put("quote", cursor.getString(1));
                quote.put("author", cursor.getString(2));
                res.add(quote);
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }

        cursor.close();
        return res;
    }

    public HashMap<String, String> GetRandomQuote() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> res = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME  , null);


        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> quote = new HashMap<String, String>();
                quote.put("id", cursor.getString(0));
                quote.put("quote", cursor.getString(1));
                quote.put("author", cursor.getString(2));
                res.add(quote);
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        cursor.close();

        return res.get(ThreadLocalRandom.current().nextInt(0, res.size()));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}