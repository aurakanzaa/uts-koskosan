package com.example.andinovanprastya.loginfirebase.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "guesthouse.db";
    private static  final int DATABASE_VERSION = 1;

    public DataHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table gh(id_gh integer primary key autoincrement, nama text null, alamat text null, telp integer null, harga text null);";
        Log.d("Data", "onCreate : "+ CREATE_TABLE);
        String CREATE_TABLE_FAVORITE = "CREATE TABLE favorite (" +
                Config.GhEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Config.GhEntry.FIELD_NAMA + " TEXT NOT NULL, " +
                Config.GhEntry.FIELD_ALAMAT + " TEXT NOT NULL, " +
                Config.GhEntry.FIELD_TELP + " TEXT NOT NULL, " +
                Config.GhEntry.FIELD_HARGA + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE_FAVORITE);

        String isi1 = "INSERT INTO gh(id_gh, nama, alamat, telp, harga)VALUES ('','De Corner', 'malang1', '0341-321433', 'Rp. 500.000,-');";
        db.execSQL(isi1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
