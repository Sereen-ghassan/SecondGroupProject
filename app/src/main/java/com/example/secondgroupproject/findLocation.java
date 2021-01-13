package com.example.secondgroupproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class findLocation extends AppCompatActivity {

    private static final int REQUEST_CODE = 1000;
    TextView txt_location;
    Button update, stop;

    LocationRequest locationRequest;
    LocationCallback locationCallback;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public void onRequestPermissionsResult(int requestcode, String[] permissions, int[] grantResult) {
        super.onRequestPermissionsResult(requestcode, permissions, grantResult);

        switch (requestcode) {
            case REQUEST_CODE: {
                if (grantResult.length > 0) {
                    if (grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    } else if (grantResult[0] == PackageManager.PERMISSION_DENIED) {

                    }

                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.location_activiy);
        txt_location = findViewById(R.id.txt_location);
        update = findViewById(R.id.btn_start_update);
        stop = findViewById(R.id.btn_stop_update);

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            buildLocationOnRequest();
            buildLocationCallBack();

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ActivityCompat.checkSelfPermission(findLocation.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(findLocation.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(findLocation.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

                        return;
                    }
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    update.setEnabled(!update.isEnabled());
                    stop.setEnabled(!stop.isEnabled());
                }
            });

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ActivityCompat.checkSelfPermission(findLocation.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(findLocation.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(findLocation.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

                        return;
                    }
                    fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                    update.setEnabled(!update.isEnabled());
                    stop.setEnabled(!stop.isEnabled());
                }
            });

        }

    }

    Geocoder geocoder = null;

    private void buildLocationCallBack() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    geocoder = new Geocoder(findLocation.this);
                    try {
                        List<Address> address = geocoder.getFromLocation(locationResult.getLastLocation().getLatitude(),
                                locationResult.getLastLocation().getLongitude(), 1);
                        txt_location.setText(address.get(0).getAddressLine(0));
                    } catch (Exception e) {
                        txt_location.setText("Cannot get Street Address!");
                    }
                }
            }
        };
    }

    @SuppressLint("RestrictedApi")
    private void buildLocationOnRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10);
    }

}
