package com.android.spotifyapp.utils;

import android.view.View;

public class ProgressBar {
    public static void progressBarVisible(android.widget.ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }
    public static void progressBarUnvisible(android.widget.ProgressBar progressBar) {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
