package com.android.spotifyapp.ui.youtube;

import android.content.Context;
import androidx.fragment.app.FragmentTransaction;
import com.android.spotifyapp.R;
import com.android.spotifyapp.ui.activities.BaseActivity;
import com.android.spotifyapp.ui.fragment.YoutubeFragment;

public class Player {
    public static void startPlayer(Context context) {
        ((BaseActivity)context)
                .getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.youtube_fragment, new YoutubeFragment())
                .commit();
    }
}
