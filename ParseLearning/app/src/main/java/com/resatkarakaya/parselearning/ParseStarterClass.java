package com.resatkarakaya.parselearning;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration
                .Builder(this)
                .applicationId("jp5DAqV7cx8UMkjTR29Rx2Oh6l9LiMc6icnOmBt1")
                .clientKey("")
                .server("")
                .build()
        ); //parse konfigürasyonu

    }
}
