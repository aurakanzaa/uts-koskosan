package com.example.andinovanprastya.loginfirebase.model;

import com.example.andinovanprastya.loginfirebase.R;

public class Kamar {
    private String nama_kamar;
    private String fasilitas;
    private String harga;
    private int gambar;

    public static final Kamar[] jeniskamar= {
            new Kamar("Compact Room", "Kamar 2x3m, Kasur Kecil, Lemari, Gantungan baju, Kamar mandi bersama, Listrik", "Rp. 500.000 / Bulan", R.drawable.compact),
            new Kamar("Standart Room", "Kamar 3x4m,Kasur standar, Lemari, Gantungan baju, Kamar mandi bersama, Listrik, Wifi", "Rp. 650.000 / Bulan", R.drawable.standart),
            new Kamar("Standart Room Doble", "Kamar 3x4m, Kasur standar dobel, Lemari besar, Gantungan baju, Kamar mandi bersama, Listrik, Wifi", "Rp. 750.000 / Bulan", R.drawable.dobel),
            new Kamar("Premium Room", "Kamar 3x4m, Kasur besar, Lemari besar, Gantungan baju, Kamar mandi pribadi (Shower, Air hangat), Listrik, Wifi, Kipas Angin", "Rp. 1.000.000 / Bulan", R.drawable.premium),
            new Kamar("Anak Sultan Room", "Kamar 4x7m, Kasur springbed, Lemari Besar, Gantungan baju, Kamar mandi pribadi (shower, bathup, air hangat), Kolam renang pribadi, Kulkas 5 Pintu, AC, TV, Telpon, Dapur pribadi, Pembantu", "Rp. 5.000.000 / Bulan", R.drawable.sultan),

    };

    public Kamar(String name, String facilitation, String price, int image) {
        this.nama_kamar = name;
        this.fasilitas = facilitation;
        this.harga = price;
        this.gambar = image;
    }

    public String getName() {
        return nama_kamar;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public String getHarga() {
        return harga;
    }

    public int getGambar() {
        return gambar;
    }

    public String toString() {
        return this.nama_kamar;
    }
}
