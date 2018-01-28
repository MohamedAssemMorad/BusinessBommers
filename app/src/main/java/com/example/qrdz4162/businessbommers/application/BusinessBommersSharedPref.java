package com.example.qrdz4162.businessbommers.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class BusinessBommersSharedPref {

    public static final String PREFERENCE_ACCESS_TOKEN_KEY = "access_token";
    private static final String PREFERENCE_ACCESS_TOKEN_DEFAULT = "";


    private BusinessBommersSharedPref() {
        // prevent any instance from this class
    }

    public static String getAccessToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(PREFERENCE_ACCESS_TOKEN_KEY, PREFERENCE_ACCESS_TOKEN_DEFAULT);
    }

    public static void setAccessToken(Context context , String accessToken) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_ACCESS_TOKEN_KEY, accessToken);
        editor.apply();
    }
}
