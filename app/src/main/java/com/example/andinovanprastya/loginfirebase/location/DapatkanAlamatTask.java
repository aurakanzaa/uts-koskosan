package com.example.andinovanprastya.loginfirebase.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DapatkanAlamatTask extends AsyncTask<Location, Void, String>{

    private Context mContext;
    private onTaskSelesai mListener;

    DapatkanAlamatTask(Context applicationContext, onTaskSelesai listener){
        mContext = applicationContext;
        mListener = listener;
    }

    @Override
    protected String doInBackground(Location... locations) {

        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        Location location = locations[0];
        List<Address> alamat = null;
        String resultMessage = "";

        try {
            alamat = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
        } catch (IOException ioException) {
            resultMessage = "Service tidak tersedia";
            Log.e("LokasiError", resultMessage, ioException);

        } catch (IllegalArgumentException illegalArgumentException){
            // Tangkap error input koordinat yang invalid
            resultMessage = "Koordinat invalid";
            Log.e("LokasiError", resultMessage + ". " +
            "Latitude = " + location.getLatitude() +
            ", Longitude = " + location.getLongitude(),
                    illegalArgumentException);
        }

        // jika alamat tidak ditemukan, tampilkan error
        if (alamat == null || alamat.size() == 0){
            if (resultMessage.isEmpty()){
                resultMessage = "Alamat tidak ditemukan";
                Log.e("LokasiError", resultMessage);
            }
        } else {
            //jika alamat ketemu, proses dan masukkan ke resultMessage
            Address address = alamat.get(0);
            ArrayList<String> barisAlamat = new ArrayList<>();

            //dapatkan baris alamat menggunakan fungsi getAddressLine & gabungkan
            for (int i=0; i<= address.getMaxAddressLineIndex(); i++){
                barisAlamat.add(address.getAddressLine(i));
            }
            //Gabungkan line alamat dipisah baris baru
            resultMessage = TextUtils.join("\n", barisAlamat);
        }

        return resultMessage;
    }

    @Override
    protected void onPostExecute(String alamat) {
        mListener.onTaskCompleted(alamat);
        super.onPostExecute(alamat);
    }

    interface onTaskSelesai {
        void onTaskCompleted(String result);
    }
}