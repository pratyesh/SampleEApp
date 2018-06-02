package com.ledexpo.android;

import android.app.Application;

import com.prt.app.util.ImageUtil;

public class SampleEApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageUtil.init(this);
    }
}
