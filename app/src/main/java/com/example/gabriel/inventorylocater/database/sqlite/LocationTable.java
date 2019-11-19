package com.example.gabriel.inventorylocater.database.sqlite;

import android.database.Cursor;

public class LocationTable implements Table {

    public static final String TABLE_NAME = "locations";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "location_name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " VARCHAR(50) NOT NULL)";

    private int id;
    private String locationName;

    public LocationTable() { }

    public LocationTable(int id, String locationName) {
        this.id = id;
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public static String[] getColumns() {
        return new String[]{COLUMN_ID, COLUMN_NAME};
    }

    @Override
    public void fillObject(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        setLocationName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
    }
}
