package com.example.gabriel.inventorylocater.database.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SQLiteHelper {

    public static long insertTable(String tableName, ContentValues values) {
        SQLiteDatabase db = SQLiteManager.sqLiteOpenHelper.getWritableDatabase();

        long id = db.insert(tableName, null, values);

        db.close();

        return id;
    }

    public static Object getObject(String tableName, String[] columns, String whereClause, String[] whereArgs, Class<? extends Table> obj) {
        List<Table> tables = new ArrayList<>();
        SQLiteDatabase db = SQLiteManager.sqLiteOpenHelper.getReadableDatabase();

        Cursor cursor = db.query(tableName, columns, whereClause, whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                try {
                    Table table = (Table) obj.newInstance();

                    table.fillObject(cursor);
                    tables.add(table);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        Log.d(TAG, tables.size() + "");

        return tables;
    }

    public static int updateRow(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = SQLiteManager.sqLiteOpenHelper.getWritableDatabase();

        return db.update(tableName, values, whereClause, whereArgs);
    }

    public static void deleteRow(String tableName, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = SQLiteManager.sqLiteOpenHelper.getWritableDatabase();

        db.delete(tableName, whereClause, whereArgs);

        db.close();
    }
}
