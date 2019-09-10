package com.android.spotifyapp.utils;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;

public class CheckProgressBar {
    public static void checkprogressBar(RecyclerView recyclerView, android.widget.ProgressBar progressBar) {
        if(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() == 0) {
            ProgressBar.progressBarVisible(progressBar);
        }
        else {
            ProgressBar.progressBarUnvisible(progressBar);
        }
    }
    public static void checkSliderProgress(SliderView sliderView, android.widget.ProgressBar progressBar) {
        if(sliderView.getSliderAdapter().getCount() == 0) {
            ProgressBar.progressBarVisible(progressBar);
        }
        else {
            ProgressBar.progressBarUnvisible(progressBar);
        }
    }

    public static void checkViewPagerProgress(ViewPager viewPager, android.widget.ProgressBar progressBar) {
        if(Objects.requireNonNull(viewPager.getAdapter()).getCount() == 0) {
            ProgressBar.progressBarVisible(progressBar);
        }
        else {
            ProgressBar.progressBarUnvisible(progressBar);
        }
    }

}
