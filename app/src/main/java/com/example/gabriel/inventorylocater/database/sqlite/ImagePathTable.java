package com.example.gabriel.inventorylocater.database.sqlite;

import android.database.Cursor;

public class ImagePathTable implements Table {

    public static final String TABLE_NAME = "image_locations";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ROOM_ID = "room_id";
    public static final String COLUMN_IMAGE_PATH = "image_path";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ROOM_ID + " INTEGER NOT NULL, " +
            COLUMN_IMAGE_PATH + " TEXT NOt NULL)";

    private int id;
    private int roomID;
    private String imagePath;

    public ImagePathTable() { }

    public ImagePathTable(int id, int roomID, String imagePath) {
        this.id = id;
        this.roomID = roomID;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public static String[] getColumns() {
        return new String[]{COLUMN_ID, COLUMN_ROOM_ID, COLUMN_IMAGE_PATH};
    }

    @Override
    public void fillObject(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        setRoomID(cursor.getInt(cursor.getColumnIndex(COLUMN_ROOM_ID)));
        setImagePath(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH)));
    }
}
