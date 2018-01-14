package com.example.am.oi;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aleks on 04.12.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "Dieta";
    public static final String database_table = "Napoje";
    private static final String KEY_NAPOJ = "napoj";

    DataBaseHelper(Context context) {
        super(context, "Dieta", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Napoje(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        KEY_NAPOJ +" TEXT," +
                        "ILOSC INTEGER);" + "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + database_table);
        onCreate(db);
    }

    public boolean wstawdane(String NAPOJ, String ILOSC) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAPOJ, NAPOJ);
        cv.put("ILOSC", ILOSC);

        if (db.insert(database_table, null, cv) == -1) {
            return false;
        } else {
            return true;
        }
    }
    public SQLiteCursor pobierzDane(){
        SQLiteDatabase db=this.getWritableDatabase();
        SQLiteCursor kursor= (SQLiteCursor) db.rawQuery("SELECT * FROM " + database_table, null);
        return kursor;
    }


}
