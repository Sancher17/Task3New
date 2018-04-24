package com.example.alex.dagger;

import android.app.Application;


public class AppInject extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
    }

    public static AppComponent getComponent(){
        return component;
    }
}
