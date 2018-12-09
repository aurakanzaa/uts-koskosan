package com.example.andinovanprastya.loginfirebase;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String SHARED_PREF_FILE = "appwidgetku";
    private static final String COUNT_KEY = "count";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // dapatkan update count
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREF_FILE, 0 );
        int count = prefs.getInt(COUNT_KEY+appWidgetId,0 );
        count++;

        // waktu pentimpanan dan format date
        String dateString = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_id,String.valueOf(appWidgetId) );
        views.setTextViewText(R.id.appwidget_update,context.getResources().getString(R.string.date_count_format, count, dateString) );

        // simpan kebali ke shared pref
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putInt(COUNT_KEY+appWidgetId,count );
        prefEditor.apply();

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            CharSequence widgetText = context.getString(R.string.appwidget_text);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

