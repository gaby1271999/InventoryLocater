package com.example.gabriel.inventorylocater.database.sqlite;

import android.database.Cursor;

public class RoomTable implements Table {

    public static final String TABLE_NAME = "rooms";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOCATION_NAME = "location_name";
    public static final String COLUMN_ROOM_NAME = "room_name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LOCATION_NAME + " VARCHAR(50) NOT NULL, " +
            COLUMN_ROOM_NAME + " VARCHAR(50) NOT NULL)";

    private int id;
    private String locoationName;
    private String roomName;

    public RoomTable() { }

    public RoomTable(int id, String locationName, String roomName) {
        this.id = id;
        this.locoationName = locationName;
        this.roomName = roomName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocoationName() {
        return locoationName;
    }

    public void setLocoationName(String locoationName) {
        this.locoationName = locoationName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public static String[] getColumns() {
        return new String[]{COLUMN_ID, COLUMN_LOCATION_NAME, COLUMN_ROOM_NAME};
    }

    @Override
    public void fillObject(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        setLocoationName(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION_NAME)));
        setRoomName(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM_NAME)));
    }
}
