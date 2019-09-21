package com.android.spotifyapp.utils;

import android.os.Bundle;

import androidx.navigation.NavController;

import com.android.spotifyapp.R;

import java.util.Objects;

import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_followers;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_id;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_image_url;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_name;

public class onClickHandler {
    public static void albumClickNavigate(String name, int followers, String image_url, String id, NavController navController) {
        Bundle params = new Bundle();
        params.putString(artist_name, name);
        params.putInt(artist_followers, followers);
        params.putString(artist_image_url, image_url);
        params.putString(artist_id,  id);
        navController.navigate(R.id.action_artistFragment_self, params);
        }


    public static void  artistClickNavigate(String name, int followers, String image_url, String id, NavController navController) {
        Bundle params = new Bundle();
        params.putString(artist_name, name);
        params.putInt(artist_followers, followers);
        params.putString(artist_image_url, image_url);
        params.putString(artist_id,  id);
        navController.navigate(R.id.action_homeFragment_to_artistFragment, params);
    }
    public static void onPlaylistClick(String name, String id, NavController controller) {
        Bundle params = new Bundle();
        params.putString("name", name);
        params.putString("id", id);
        controller.navigate(R.id.action_home_to_playlistTracksFragment, params);
    }

}
