package com.link.logovanje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Logovanje.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Korisnik";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_KORISNICKO_IME = "korisnicko_ime";
    private static final String COLUMN_SIFRA = "sifra";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_KORISNICKO_IME + " TEXT, " +
                COLUMN_SIFRA + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long dodajKorisnika(String korisnickoIme, String sifra) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_KORISNICKO_IME, korisnickoIme);
        values.put(COLUMN_SIFRA, sifra);

        long result = db.insert(TABLE_NAME, null, values);

        db.close();

        return result;
    }

    public boolean proveriKorisnika(String korisnickoIme, String sifra) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_KORISNICKO_IME, COLUMN_SIFRA};
        String selection = COLUMN_KORISNICKO_IME + " = ? AND " + COLUMN_SIFRA + " = ?";
        String[] selectionArgs = {korisnickoIme, sifra};

        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        boolean korisnikPostoji = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return korisnikPostoji;
    }

}
