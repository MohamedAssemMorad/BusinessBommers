package com.example.qrdz4162.businessbommers.application;

import android.app.Application;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class BusinessBommersApp extends Application {

    public static final String BASE_IMAGE_URL = "http://office.businessboomers.net:666/dresscode/web/media/image/";

    private static BusinessBommersApp instance;

    public static BusinessBommersApp getInstance() {
        if (instance == null){
            throw new IllegalStateException("Something went horribly wrong!!, no application attached!");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}