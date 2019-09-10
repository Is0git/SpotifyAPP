package com.android.spotifyapp;

import android.app.Activity;
import android.app.Application;

import com.android.spotifyapp.di.components.AppComponent;
import com.android.spotifyapp.di.components.DaggerAppComponent;

public class App extends Application {

    AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();

    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
