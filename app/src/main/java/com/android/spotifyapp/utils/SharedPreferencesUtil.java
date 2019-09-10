package com.android.spotifyapp.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    public static SharedPreferences getPreferences(String name, Context application) {
        return application.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
}
