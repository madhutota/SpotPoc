package com.example.spotpoc.app;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

    }



    public static MyApplication getInstance() {
        return mInstance;
    }
}
