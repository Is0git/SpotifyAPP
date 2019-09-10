package com.android.spotifyapp.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Piccaso {
    public static void loadImage(String url, @Nullable Integer position, Context context, View view) {
        Picasso.with(context)
                .load(url).fit()
                .into((ImageView) view);
    }

    public static void loadImageWithCallBack(String url, @Nullable Integer position, Context context, View view, View progressView) {
        ProgressBar.progressBarVisible((android.widget.ProgressBar) progressView);
        Picasso.with(context)
                .load(url).fit()
                .into((ImageView) view, new Callback() {
                    @Override
                    public void onSuccess() {
                        ProgressBar.progressBarUnvisible((android.widget.ProgressBar) progressView);
                    }

                    @Override
                    public void onError() {
                        ProgressBar.progressBarUnvisible((android.widget.ProgressBar) progressView);
                    }
                });
    }
}
