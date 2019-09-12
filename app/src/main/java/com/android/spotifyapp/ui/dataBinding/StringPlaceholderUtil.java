package com.android.spotifyapp.ui.dataBinding;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.android.spotifyapp.R;

public class StringPlaceholderUtil {
    @BindingAdapter("setPlaceHolderText")
    public static void test(TextView textView, int items) {
        if(items == 1) textView.setText(R.string.items_1);
        else textView.setText(textView.getContext().getString(R.string.playlist_items, items));
    }
}
