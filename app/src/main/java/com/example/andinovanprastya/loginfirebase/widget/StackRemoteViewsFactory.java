package com.example.andinovanprastya.loginfirebase.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.andinovanprastya.loginfirebase.R;
import com.example.andinovanprastya.loginfirebase.model.Config;
import com.example.andinovanprastya.loginfirebase.model.Kamar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{
    private List<Bitmap> mWidgetItems = new ArrayList<>();
    private Context context;
    private int mAppWidgetId;
    private Cursor cursor;
    Kamar data;



    public StackRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }


    @Override
    public void onCreate() {

        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.kawi3));
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.merbabu1));
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.decorner4));
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.omah1));
        mWidgetItems.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.helena3));

    }


    @Override
    public void onDataSetChanged() {
//        final long token = Binder.clearCallingIdentity();
//        cursor = context.getContentResolver().query(Config.GhEntry.CONTENT_URI, null, null, null, null);
//        Binder.restoreCallingIdentity(token);

    }


    @Override
    public void onDestroy() {

    }


    @Override
    public int getCount() {
        return mWidgetItems.size();
//        return cursor.getCount();
    }


    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.imageView,mWidgetItems.get(position) );

        Bundle extras = new Bundle();
        extras.putInt(NewAppWidget.EXTRA_ITEM,position );
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

//        if(cursor.moveToPosition(position)){
//            data = new Kamar(cursor);
//            String tittle = data.getName();
//            rv.setTextViewText(R.id.tv_tittle, tittle);
//        }


        rv.setOnClickFillInIntent(R.id.imageView,fillInIntent );
        return rv;
    }

    /**
     * This allows for the use of a custom loading view which appears between the time that
     * {@link #getViewAt(int)} is called and returns. If null is returned, a default loading
     * view will be used.
     *
     * @return The RemoteViews representing the desired loading view.
     */
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }


    @Override
    public int getViewTypeCount() {
        return 1;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
