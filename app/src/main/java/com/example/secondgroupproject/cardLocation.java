package com.example.secondgroupproject;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.net.URL;

public class cardLocation extends AppCompatActivity {
    BitmapFactory mIcon_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        Locality locality ;

        Intent intent = getIntent();
        int id = (int)intent.getExtras().get("Id");
        locality = veiwUponLocation.subjectsList.get(id);

        ImageView image = (ImageView)findViewById(R.id.imageId2);
        TextView txtPrice= (TextView)findViewById(R.id.price);
        TextView txtLocalityType= (TextView)findViewById(R.id.localitytype);
        TextView txtSurface = (TextView)findViewById(R.id.surface);
        TextView txtRooms = (TextView)findViewById(R.id.rooms);
        TextView txtLoc = findViewById(R.id.location);
        TextView txtDes = findViewById(R.id.description);

        Glide.with(cardLocation.this).load(locality.getImageID()).into(image);
        txtPrice.setText(locality.getPrice());
        txtLocalityType.setText(locality.getLocalityType());
        txtSurface.setText(locality.getSurface());
        txtRooms.setText(locality.getRooms());
        txtLoc.setText(locality.getLocation());
        txtDes.setText(locality.getDescription());

    }
}
