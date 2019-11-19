package com.example.gabriel.inventorylocater;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "InventoryLocator.db";
    public static final String TABLE_NAME = "Rooms";

    public static final String KEY_ID = "ID";
    public static final String KEY_ROOM = "NAME";
    public static final String KEY_FLOOR = "FLOOR";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ROOM + " TEXT, " + KEY_FLOOR + " INTEGER)");
    }

    public boolean insertData(String name, int floor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ROOM, name);
        contentValues.put(KEY_FLOOR, floor);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1) {
            return false;
        }

        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sb, int oldVersion, int newVerion) {

    }
}
