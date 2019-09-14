package com.android.spotifyapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.spotifyapp.data.network.model.User;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_user;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.user_country;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.user_id;

public class UserUtil {
    public static void userSave(User user, Context context) {
        SharedPreferences.Editor editor = SharedPreferencesUtil.getPreferences(shared_preferences_user, context.getApplicationContext()).edit();
        editor.putString(user_id, user.getId());
        editor.putString(user_country, user.getCountry());
        editor.apply();
    }
}
