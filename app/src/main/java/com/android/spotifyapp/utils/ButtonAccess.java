package com.android.spotifyapp.utils;

import android.widget.Button;

public class ButtonAccess {
    public static void disable(Button button) {
        button.setAlpha(0.5f);
        button.setEnabled(false);
    }
    public static void enable(Button button) {
        button.setAlpha(1);
        button.setEnabled(true);
    }
}
