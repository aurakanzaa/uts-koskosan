package com.example.andinovanprastya.loginfirebase.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.andinovanprastya.loginfirebase.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationActivity extends AppCompatActivity implements DapatkanAlamatTask.onTaskSelesai{

    private Button mLocationButton;
    private TextView mLocationTextView;
    private static final int REQUEST_LOCATION_PERMISSION =1;
    private Location mLastLocation;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    public void onTaskCompleted(String result){
        //update UI dengan tampilan hasil alamat
        mLocationTextView.setText(getString(R.string.alamat_text,
                result, System.currentTimeMillis()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mLocationButton = (Button) findViewById(R.id.button_location);
        mLocationTextView = (TextView) findViewById(R.id.textview_location);

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            Log.d("GETPERMISI", "getLocation: permissions granted");
//            Log.d("GETPERMISI", "getLocation: permissions granted");
            mFusedLocationClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                // lakukan reverse geocode AsyncTask
                                new DapatkanAlamatTask(LocationActivity.this, LocationActivity.this).execute(location);

//                                mLastLocation = location;
//                                mLocationTextView.setText(
//                                        getString(R.string.location_text,
//                                                mLastLocation.getLatitude(),
//                                                mLastLocation.getLongitude(),
//                                                mLastLocation.getTime())
//                                );
                            } else {
                                mLocationTextView.setText("Lokasi tidak tersedia");
                            }
                        }
                    }
            );
        }
        //menampilkan text loading
        mLocationTextView.setText(getString(R.string.alamat_text,
                "Sedang mencari alamat",
                System.currentTimeMillis()));
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                // jika permission diijinkan, mulaiTrackingLokasi()
                // jika tidak, tampilkan Toast
                if (grantResult.length > 0
                        && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    getLocation();
                } else {
                    Toast.makeText(this,
                            "Permission bapakknya gak dapet masbro, mesakke", Toast.LENGTH_SHORT).show();
                }
        }
    }


}
