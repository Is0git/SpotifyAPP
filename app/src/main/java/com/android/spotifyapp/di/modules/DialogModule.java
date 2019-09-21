package com.android.spotifyapp.di.modules;

import android.content.Context;


import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.ui.fragment.HomeFragment;
import com.android.spotifyapp.ui.Dialogs.Dialog;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)

public class DialogModule {
    private HomeFragment homeFragment;

    public DialogModule(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Provides
    public Dialog getDialog(@ActivityContext Context context) {
        return new Dialog(context, homeFragment);
    }
}
