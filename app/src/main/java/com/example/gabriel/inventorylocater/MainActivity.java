package com.example.gabriel.inventorylocater;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gabriel.inventorylocater.database.sqlite.LocationTable;
import com.example.gabriel.inventorylocater.database.sqlite.SQLiteHelper;
import com.example.gabriel.inventorylocater.database.sqlite.SQLiteManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private SQLiteManager sqLiteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final DataBaseHelper myDb = new DataBaseHelper(this);
        sqLiteManager = new SQLiteManager(this);

        /*
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocationTable.COLUMN_NAME, "home1");

        long id = SQLiteHelper.insertTable(LocationTable.TABLE_NAME, contentValues);

        LocationTable locationTable = new LocationTable();
        Object obj = SQLiteHelper.getObject(LocationTable.TABLE_NAME, LocationTable.getColumns(), "rooms=?", new String[]{"5"}, LocationTable.class);
        if (obj instanceof ArrayList) {
            Log.d(TAG, "Array of cursors");
            for (LocationTable lt : (List<LocationTable>) obj) {
                Log.d(TAG, "deleted: " + lt.getId());
                SQLiteHelper.deleteRow(LocationTable.TABLE_NAME, "id=?", new String[]{String.valueOf(lt.getId())});
            }
        } else {
            locationTable = (LocationTable) obj;
            Log.d(TAG, locationTable.getLocationName());
        }*/

        Log.d(TAG, "This is a test");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search_item:
                startActivity(new Intent(this, DisplaySearchActivity.class));
                return true;
            case R.id.rooms:
                startActivity(new Intent(this, DisplayRoomsActivity.class));
                return true;
            case R.id.new_item:
                startActivity(new Intent(this, DisplayNewItemActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
