package com.example.andinovanprastya.loginfirebase.model;

public class VideoModel {
    private String nama;
    private String durasi;
    private String videoRawId;


    // Array nama video dan detailnya

    public static final VideoModel[] guesthouse ={
            new VideoModel("DeCorner",
                    "2:23",
                    "https://youtu.be/p5AykYigWek"),
            new VideoModel("Kawi",
                    "2:13",
                    "https://youtu.be/piiGlq8mP04"),
            new VideoModel("Helena",
                    "2:27",
                    "https://youtu.be/dhZ5nmt_efU"),
            new VideoModel("Merbabu",
                    "3:02",
                    "https://youtu.be/N86-uaAymeY"),
            new VideoModel("Omah Koe",
                    "1:56",
                    "https://youtu.be/sRUorqq159o")
    };

    // setiap data punya nama, deskripsi dan gambar
    private VideoModel(String nama, String durasi, String videoRawId){
        this.nama = nama;
        this.durasi = durasi;
        this.videoRawId = videoRawId;
    }

    public String getNama(){
        return nama;
    }

    public String getDurasi(){
        return durasi;
    }

    public String getVideoRawId(){
        return videoRawId;
    }

    @Override
    public String toString() {
        return this.nama;
    }
}
