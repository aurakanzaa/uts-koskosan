package com.example.andinovanprastya.loginfirebase.fragment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andinovanprastya.loginfirebase.R;
import com.example.andinovanprastya.loginfirebase.model.Kamar;
import com.example.imagewatermark2.ImageWaterMarkView;
import com.example.imagewatermark2.WaterMarkParamBean;
import com.example.imagewatermark2.WaterMarkType;

import java.util.ArrayList;


public class KamarDetailFragment extends Fragment {
    private long kosId;
    ImageWaterMarkView waterMarkView;

    public KamarDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        WaterMarkParamBean paramBean3 = new WaterMarkParamBean().setType(WaterMarkType.TYPE_TEXT)
//                .setLeft(30)
//                .setTop(30)
//                .setWidth(450)
//                .setHeight(50)
//                .setUserInputText("Kosan Malang----")
//                .setFontColor("#000000")
//                .setFontSize(60);
//        ArrayList<WaterMarkParamBean> list = new ArrayList<>();
////        list.add(paramBean2);
//        list.add(paramBean3);
////        list.add(paramBean);
//        waterMarkView.setWaterMarkData(list);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kamar_detail, container, false);
    }

    public void setKos(long id){
        this.kosId = id;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        if(view != null){
            TextView title = view.findViewById(R.id.textTitle);
            Kamar tipekamar = Kamar.jeniskamar[(int) kosId];
            title.setText(tipekamar.getName());

            TextView fasilitas = view.findViewById(R.id.textFasilitas);
            fasilitas.setText(tipekamar.getFasilitas());

            TextView harga = view.findViewById(R.id.textPrice);
            harga.setText(tipekamar.getHarga());

            ImageView img = view.findViewById(R.id.fotoKamar);
            img.setImageResource(tipekamar.getGambar());

//            waterMarkView= (ImageWaterMarkView) view.findViewById(R.id.fotoKamar);
//            waterMarkView.setImageBitmap(BitmapFactory.decodeResource(getResources(),tipekamar.getGambar()));
        }
    }
}
