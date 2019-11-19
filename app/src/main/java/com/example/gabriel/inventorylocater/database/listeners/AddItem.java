package com.example.gabriel.inventorylocater.database.listeners;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.gabriel.inventorylocater.R;
import com.example.gabriel.inventorylocater.database.sqlite.LocationTable;
import com.example.gabriel.inventorylocater.database.sqlite.SQLiteHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AddItem implements View.OnClickListener {

    private int type;

    public AddItem(int type) {
        this.type = type;
    }

    @Override
    public void onClick(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();

        if (type == 1) {
            addLocationToSQLDB(viewGroup);
        } else if (type == 2) {
            addRoomToSQLDB(viewGroup);
        }
    }

    private void addLocationToSQLDB(ViewGroup viewGroup) {
        //TODO check for negative values, values that starts with a 0 and an empty name.

        String locationName = ((EditText) viewGroup.findViewById(R.id.location_name)).getText().toString();

        ContentValues contentValues = new ContentValues(0);
        contentValues.put(LocationTable.COLUMN_NAME, locationName);

        SQLiteHelper.insertTable(LocationTable.TABLE_NAME, contentValues);
    }

    private void addRoomToSQLDB(ViewGroup viewGroup) {
        String locationName = ((EditText) viewGroup.findViewById(R.id.room_location_name)).getText().toString();
        String roomnName = ((EditText) viewGroup.findViewById(R.id.room_room_name)).getText().toString();

        List<LocationTable> locationTables = (List<LocationTable>) SQLiteHelper.getObject(LocationTable.TABLE_NAME, LocationTable.getColumns(), LocationTable.COLUMN_NAME + "=?", new String[]{locationName}, LocationTable.class);

        if (locationTables.size() == 0) {
            //TODO display error message
            Log.d(TAG, "location doesnt exists");
            return;
        }

        LinearLayout linearLayout = viewGroup.findViewById(R.id.room_horizontal_photo_display);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);

            if (view instanceof RelativeLayout) {
                Log.d(TAG, ((RelativeLayout) view).getChildAt(0).getClass().getSimpleName());
                ImageView imageView = (ImageView) ((RelativeLayout) view).getChildAt(0);

                //TODO deprecated
                Bitmap bitmap = imageView.getDrawingCache();

                File directory = view.getContext().getDir(locationName, Context.MODE_PRIVATE);
                File file = new File(directory,System.currentTimeMillis() + ".png");

                try {
                    OutputStream ops = view.getContext().getContentResolver().openOutputStream(Uri.fromFile(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ops);
                } catch (FileNotFoundException exception) {
                    Log.d(TAG, "File could not be found!");
                }
            }
        }
        //Log.d(TAG, ((RelativeLayout) linearLayout.getChildAt(1)).getChildAt(0).getClass().getSimpleName());


        /*
        View[] views = viewGroup.findViewById(R.id.room_horizontal_photo_display);

        for (View view : views) {
            if (view instanceof RelativeLayout) {

            }
        }*/
    }
}
