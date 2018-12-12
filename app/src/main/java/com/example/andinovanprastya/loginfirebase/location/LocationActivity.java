package com.example.andinovanprastya.loginfirebase.location;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.andinovanprastya.loginfirebase.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;



public class LocationActivity extends AppCompatActivity implements DapatkanAlamatTask.onTaskSelesai{

    //button picker map untuk memilih guest house
    public Button button_pilihguesthouse;
    private Button mLocationButton;
    private TextView mLocationTextView;
    private static final int REQUEST_LOCATION_PERMISSION =1;
    private Location mLastLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private ImageView mAndroidImageView;
    private AnimatorSet mRotateAnim;
    private boolean mTrackingLocation;
    private LocationCallback mLocationCallback;
    private PlaceDetectionClient mPlaceDetectionClient;
    private String mLastPlaceName;
    private static final int REQUEST_PICK_PLACE = 1;

    @Override
    public void onTaskCompleted(final String result) throws  SecurityException{
        if (mTrackingLocation){

            // placelikelihood mencari lokasi yang hampir mirip yang akan di parsing menjadi alamat
            Task<PlaceLikelihoodBufferResponse> placeResult =
                    mPlaceDetectionClient.getCurrentPlace(null);
            placeResult.addOnCompleteListener
                    (new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                            if (task.isSuccessful()) {
                                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                                float maxLikelihood = 0;
                                Place currentPlace = null;

                                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                                    // setting untuk mengatur ke arah mana user akan terbaca
                                    if (maxLikelihood < placeLikelihood.getLikelihood()) {
                                        maxLikelihood = placeLikelihood.getLikelihood();
                                        currentPlace = placeLikelihood.getPlace();
                                    }
                                }
                                //tampilkan di ui menampilkan alamat/ lokasi saat ini
                                if (currentPlace != null) {
                                    //ubah icon berdasar tipe lokasi
                                    setTipeLokasi(currentPlace);

                                    mLocationTextView.setText(
                                            getString(R.string.alamat_text,
                                                    currentPlace.getName(),
                                                    result,
                                                    System.currentTimeMillis()));
                                    likelyPlaces.release();

                                }

                            } else {
                                mLocationTextView.setText(
                                        getString(R.string.alamat_text,
                                                "Nama lokasi tidak ditemukan!",
                                                result,
                                                System.currentTimeMillis()));
                            }
                        }
                    });

        }

    }

    // ketika place ditemukan
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Place place = PlacePicker.getPlace(this, data);

            setTipeLokasi(place);
            //untuk mendapatkan nama lokasi
            mLocationTextView.setText(
                    getString(R.string.alamat_text,
                            place.getName(),
                            place.getAddress(),
                            System.currentTimeMillis()));

        } else {
            mLocationTextView.setText("Anda belum memilih lokasi");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //untuk mendapat informasi lokasi device
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this);

        //deklarasi button, textview dan imageview
        mLocationButton = (Button) findViewById(R.id.button_location);
        mLocationTextView = (TextView) findViewById(R.id.textview_location);
        mAndroidImageView = (ImageView) findViewById(R.id.imageview_android);
        button_pilihguesthouse = (Button) findViewById(R.id.button_pilihguesthouse);

        //animasi
        mRotateAnim = (AnimatorSet) AnimatorInflater.loadAnimator
                (this, R.animator.rotate);
        mRotateAnim.setTarget(mAndroidImageView);

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mulaiTrackingLokasi();
                if (!mTrackingLocation){
                    mulaiTrackingLokasi();
                } else {
                    stopTrackingLokasi();
                }
            }
        });

        //button pilih guesthouse untuk menampilkan location picker
        button_pilihguesthouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try{
                    startActivityForResult(builder.build(LocationActivity.this), REQUEST_PICK_PLACE );
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e){
                    e.printStackTrace();
                }
            }
        });



        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult){
                //jika tracking aktif, proses reverse geocode menjadi data alamat
                if (mTrackingLocation){
                    new DapatkanAlamatTask(LocationActivity.this, LocationActivity.this)
                            .execute(locationResult.getLastLocation());
                }
            }
        };

    }

    //untuk mentracking lokasi device
    private void mulaiTrackingLokasi() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            // locationcallback digunakan untuk mengganti fungsi this (onLocationResult dan onLocationChanged)
            // ConnectionCallbacks digunakan untuk mengetahui apakah koneksi dari LocationClient ke services berhasil atau tidak.
            // OnConnectionFailedListener digunakan untuk menghandle ketika terjadi kegagalan saat koneks
            // LocationListener digunakan untuk mengetahui ketika apabila ada update lokasi.
            //

            mFusedLocationClient.requestLocationUpdates
                    (getLocationRequest(), mLocationCallback, null);
            // mFusedLocationClient meminta lokasi terakhir yang diketahui, yang harus Anda lakukan hanyalah menelepo

            //menampilkan text loading
            mLocationTextView.setText(getString(R.string.alamat_text,
                    "Sedang mencari nama tempat",
                    "Sedang mencari alamat",
                    System.currentTimeMillis()));
            mTrackingLocation = true;
            mLocationButton.setText("Stop Tracking Lokasi");
            mRotateAnim.start();
        }
    }

    // jika permission tidak disetujui maka muncul toast maka hasil akan berhenti
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                // jika permission diijinkan, mulaiTrackingLokasi()
                // jika tidak, tampilkan Toast
                if (grantResult.length > 0
                        && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    mulaiTrackingLokasi();
                } else {
                    Toast.makeText(this,
                            "Permission bapakknya gak dapet masbro, mesakke", Toast.LENGTH_SHORT).show();
                }
        }
    }

    //menghentikan pencarian lokasi device
    private void stopTrackingLokasi(){
        if (mTrackingLocation){
            mTrackingLocation = false;
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            mLocationButton.setText("Mulai Tracking Lokasi");
            mLocationTextView.setText("Tracking sedang dihentikan");
            mRotateAnim.end();
        }
    }

    //request untuk mencari lokasi device

    // interval yg digunakan untuk mencari lokasi
    private LocationRequest getLocationRequest(){
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);

        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    //mencari tipe lokasi
    private void setTipeLokasi(Place currentPlace){
        int drawableID = -1;
        for (Integer placeType : currentPlace.getPlaceTypes()){
            switch (placeType){
                case Place.TYPE_ROOM:
                    drawableID = R.drawable.merbabu1;
                    break;
            }
        }
        //menampilkan animasi tidak berada di guest house ketika posisi device tidak di lokasi guest house
        if (drawableID < 0) {
            drawableID = R.drawable.tidakguesthouse;
        }
        mAndroidImageView.setImageResource(drawableID);
    }

}
