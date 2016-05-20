package com.example.anas.forecastapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anas on 20/05/2016.
 */
public class Utils {

    public static SharedPreferences getAppSharedPreference(Activity activity) {
        SharedPreferences sharedPreference = activity.getSharedPreferences(
                Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreference;
    }

    public static SharedPreferences getAppSharedPreference(Context context) {
        SharedPreferences sharedPreference = context.getSharedPreferences(
                Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreference;
    }
}
