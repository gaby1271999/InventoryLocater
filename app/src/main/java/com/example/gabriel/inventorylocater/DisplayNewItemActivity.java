package com.example.gabriel.inventorylocater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gabriel.inventorylocater.database.listeners.AddItem;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayNewItemActivity extends AppCompatActivity {

    private String[] fruits = {"Apple", "Appy", "Banana", "Cherry", "Date", "Pear"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_new_item);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.item_room_name);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, fruits);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.textView4);
                textView.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.location_submit_button).setOnClickListener(new AddItem(1));
        findViewById(R.id.room_submit_button).setOnClickListener(new AddItem(2));
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        findViewById(R.id.location_constraint).setVisibility(View.GONE);
        findViewById(R.id.room_constraint).setVisibility(View.GONE);
        findViewById(R.id.item_constraint).setVisibility(View.GONE);


        switch (view.getId()) {
            case R.id.radio_location_button:
                if (checked) {
                    findViewById(R.id.location_constraint).setVisibility(View.VISIBLE);
                }

                break;
            case R.id.radio_room_button:
                if (checked) {
                    findViewById(R.id.room_constraint).setVisibility(View.VISIBLE);
                }

                break;
            case R.id.radio_item_button:
                if (checked) {
                    findViewById(R.id.item_constraint).setVisibility(View.VISIBLE);
                }

                break;
        }
    }

    /*
    private File createImageFile(String dir, String name) throws IOException {
        File directory = this.getDir(dir, Context.MODE_PRIVATE);
        File image = File.createTempFile(name, ".jpg", directory);

        return image;
    }*/

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void takeRoomPhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            /*
            File photoFile = null;
            try {
                photoFile = createImageFile("", "photo1");
            } catch (IOException e) {

            }

            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.gabriel.inventorylocator", photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);*/
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            //}
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            LinearLayout linearLayout = findViewById(R.id.room_horizontal_photo_display);

            RelativeLayout relativeLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            relLayoutParams.setMargins(linearLayout.getChildCount() != 0 ? 5 : 0, 0, 0, 0);
            relativeLayout.setLayoutParams(relLayoutParams);

            ImageView imageView = new ImageView(this);
            RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
            imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            imageView.setLayoutParams(imageLayoutParams);
            imageView.setAdjustViewBounds(true);
            imageView.setOnClickListener(new ImageClickListener(1));
            imageView.setImageBitmap(imageBitmap);


            LinearLayout linearLayoutButtons = new LinearLayout(this);
            RelativeLayout.LayoutParams imageDeleteKeepLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageDeleteKeepLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            imageDeleteKeepLayoutParams.setMargins(0, 15, 0, 0);
            linearLayoutButtons.setLayoutParams(imageDeleteKeepLayoutParams);
            linearLayoutButtons.setVisibility(View.GONE);
            linearLayoutButtons.setOrientation(LinearLayout.HORIZONTAL);

            ImageView delete = new ImageView(this);
            delete.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            ((LinearLayout.LayoutParams) delete.getLayoutParams()).setMargins(0, 0, 5, 0);
            delete.setOnClickListener(new ImageClickListener(2));
            delete.setImageResource(R.drawable.delete);

            ImageView keep = new ImageView(this);
            keep.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            ((LinearLayout.LayoutParams) keep.getLayoutParams()).setMargins(5, 0, 0, 0);
            keep.setOnClickListener(new ImageClickListener(3));
            keep.setImageResource(R.drawable.keep);

            relativeLayout.addView(imageView);

            linearLayoutButtons.addView(delete);
            linearLayoutButtons.addView(keep);
            relativeLayout.addView(linearLayoutButtons);

            linearLayout.addView(relativeLayout);
        }
    }
}
