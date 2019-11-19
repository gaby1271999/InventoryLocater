package com.example.gabriel.inventorylocater.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteManager extends SQLiteOpenHelper {

    /*
    https://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
     */

    public static SQLiteOpenHelper sqLiteOpenHelper;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inventory_locator";

    /*
    private List<LocationTable> locationTableList = new ArrayList<>();
    private List<RoomTable> roomTableList = new ArrayList<>();*/

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        sqLiteOpenHelper = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocationTable.CREATE_TABLE);
        db.execSQL(RoomTable.CREATE_TABLE);
        db.execSQL(ImagePathTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO for each version add new collums for older versions
        //https://thebhwgroup.com/blog/how-android-sqlite-onupgrade
    }
}
