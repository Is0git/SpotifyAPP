package com.android.spotifyapp.ui.dataBinding;

import android.view.View;
import android.widget.ProgressBar;

import androidx.databinding.BindingAdapter;


public class ProgressVisibility {
    @BindingAdapter("setVisibility")
    public static void setProgressVisibility(ProgressBar progressBar, int status) {
        if(status == View.VISIBLE)
        progressBar.setVisibility(View.VISIBLE);
        else if(status == View.INVISIBLE) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

//    @BindingAdapter("attachedProgressBar")
//            public static void attachedProgressBar(ImageView imageView, ProgressBar progressBar){
//                    imageDownloader
//    }
}
