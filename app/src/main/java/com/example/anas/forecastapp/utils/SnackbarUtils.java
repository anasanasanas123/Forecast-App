package com.example.anas.forecastapp.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.example.anas.forecastapp.R;


/**
 * Created by anas on 20/05/2016.
 */
public class SnackbarUtils {

    private static Snackbar snackbar;

    public static void loadSnackBar(String message, Activity activity) {

        snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_LONG).setAction(
                activity.getResources().getString(R.string.ok),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });
        snackbar.setActionTextColor(activity.getResources().getColor(R.color.colorPrimary));
        View snackbarView = snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbarView.setBackgroundColor(Color.parseColor("#323232"));
        snackbar.show();
    }

    public static void loadSnackBar(String message, Activity activity, View v) {

        snackbar = Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                .setAction(activity.getResources().getString(R.string.ok),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                            }
                        });
        snackbar.setActionTextColor(activity.getResources().getColor(R.color.colorPrimary));
        View snackbarView = snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbarView.setBackgroundColor(Color.parseColor("#323232"));
        snackbar.show();
    }

    public static void cancelSnackBar() {
        if (snackbar != null)
            snackbar.dismiss();
    }

    public static void infinityLoadSnackBar(String message, Activity activity) {

        snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_INDEFINITE).setAction(
                activity.getResources().getString(R.string.ok),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                    }
                });
        snackbar.setActionTextColor(activity.getResources().getColor(
                R.color.colorPrimary));
        View snackbarView = snackbar.getView();
        TextView tv = (TextView) snackbarView.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbarView.setBackgroundColor(Color.parseColor("#323232"));
        snackbar.show();
    }
}
