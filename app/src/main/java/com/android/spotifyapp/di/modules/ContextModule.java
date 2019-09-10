package com.android.spotifyapp.di.modules;

import android.app.Activity;
import android.content.Context;

import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;

import dagger.Module;
import dagger.Provides;

@Module

public class ContextModule {
    private Context context;

    public ContextModule(Activity context) {
        this.context = context;
    }

    @Provides
    @ActivityContext
    Context getContext() {
        return this.context;
    }
}
