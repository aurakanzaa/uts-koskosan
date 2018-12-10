package com.example.andinovanprastya.loginfirebase.model;

import android.net.Uri;
import android.provider.BaseColumns;

public class Config {
    public static final String AUTHORITY = "com.example.andinovanprastya.loginfirebase";
    public static final String PATH_TASKS ="listruang";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class GhEntry implements BaseColumns {
        public static Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_TASKS).build();

        public static final String DATABASE_TABLE = "gh";
        public static final String CREATE_TABLE = "create table gh(id integer primary key autoincrement, nama text null, alamat text null, telp integer null, harga text null);";
        public static final String FIELD_ID = "id";
        public static final String FIELD_NAMA = "nama";
        public static final String FIELD_ALAMAT = "alamat";
        public static final String FIELD_TELP = "telp";
        public static final String FIELD_HARGA = "harga";
    }

    public static final String BUNDLE_EXTRA_ITEM = "com.example.andinovanprastya.loginfirebase.EXTRA_ITEM";
    public static final String BUNDLE_TOAST_ACTION = "com.example.andinovanprastya.loginfirebase.TOAST_ACTION";
}
