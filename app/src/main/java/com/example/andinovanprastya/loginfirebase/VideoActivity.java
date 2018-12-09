package com.example.andinovanprastya.loginfirebase;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.andinovanprastya.loginfirebase.model.VideoModel;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    ListView listView;
    TextView textLoading;


    // array list untuk menyimpan video
    ArrayList<String> listVideo;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        listView = findViewById(R.id.listView);

        // menampilkan tulisan loading
        // set menjadi invisible
        textLoading = (TextView) findViewById(R.id.textLoading);
        textLoading.setVisibility(VideoView.INVISIBLE);

        // mennampilkan list pada layout
        listVideo = new ArrayList<>();
        listVideo.add("DeCorner");
        listVideo.add("Kawi");
        listVideo.add("Helena");
        listVideo.add("Merbabu");
        listVideo.add("Omah Koe");

        // memanggil data dari VideoModel
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, VideoModel.guesthouse);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            // menampilkan data, harus seuai dengan ArrayList
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // buat playbacknya media playernya
                VideoModel videoModel = VideoModel.guesthouse[(int) id];
                Uri videoUri = dapatkanMedia(videoModel.getVideoRawId());
                buatPlayer(videoUri);
//                switch (position){
//                    case 0:
//                        videoView.setVideoURI(Uri.parse("android.resource://"+ getPackageName()
//                                + "/" + R.raw.decorner));
//                        break;
//                    case 1:
//                        videoView.setVideoURI(Uri.parse("android.resource://"+ getPackageName()
//                                + "/" + R.raw.kawi));
//                        break;
//                    case 2:
//                        videoView.setVideoURI(Uri.parse("android.resource://"+ getPackageName()
//                                + "/" + R.raw.helena));
//                        break;
//                    case 3:
//                        videoView.setVideoURI(Uri.parse("android.resource://"+ getPackageName()
//                                + "/" + R.raw.merbabu));
//                        break;
//                    case 4:
//                        videoView.setVideoURI(Uri.parse("android.resource://"+ getPackageName()
//                                + "/" + R.raw.omahkoe));
//                        break;
//                }

                // menambah controller unruk playback
                videoView.setMediaController(new MediaController(VideoActivity.this));

                videoView.requestFocus();
                videoView.start();
            }
        });


    }

    // dapatkanMedia() merupakan parameter nama media
    // tujuannya meload video secara dinamis
    private Uri dapatkanMedia(String namaMedia){
//        return Uri.parse("android.resource://" +getPackageName()+
//        "/raw/"+ namaMedia);
        if(URLUtil.isValidUrl(namaMedia)){
            // media name dari external url
            return Uri.parse(namaMedia);
        } else {
            return Uri.parse("android.resource://" +getPackageName()+
                    "/raw/"+ namaMedia);
        }
    }

    // method player agar lebih dinamis
    private void buatPlayer(final Uri videoUri){

        // text loading
        textLoading.setVisibility(VideoView.VISIBLE);

        // set resource video
        videoView.setVideoURI(videoUri);

        // menambah controller untuk playback
//        videoView.setMediaController(new MediaController(VideoActivity.this));


        // agar posisi controller melekat pada video view
        MediaController mc = new MediaController(VideoActivity.this);
        videoView.setMediaController(mc);
        mc.setAnchorView(videoView);

        // listener untuk event onPrepared() event (dijalankan saat setiap dimainkan)
        videoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        
                        // sembunyikan text loading
                        textLoading.setVisibility(VideoView.INVISIBLE);

                        // start mainkan video
                        videoView.start();
                    }
                }
        );

        // listener untuk event onCompletion() (saat selesai dimainkan)
        videoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(VideoActivity.this, "Selesai" , Toast.LENGTH_SHORT ).show();

                        //kembalikan ke posisi frame 0
                        videoView.seekTo(0);
                    }
                }
        );
        videoView.requestFocus();
        videoView.start();
    }
}
