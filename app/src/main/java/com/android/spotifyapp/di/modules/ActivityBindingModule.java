package com.android.spotifyapp.di.modules;

import android.app.Activity;
import android.content.Context;

import androidx.databinding.DataBindingUtil;

import com.android.spotifyapp.R;
import com.android.spotifyapp.databinding.ActivityAuthBinding;
import com.android.spotifyapp.databinding.ActivityBaseActivityBinding;
import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.AuthScope;
import com.android.spotifyapp.di.scopes.BaseActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityBindingModule {

    @Provides
    @BaseActivityScope
    public ActivityBaseActivityBinding activityBaseActivityBinding(@ActivityContext Context context) {
        return DataBindingUtil.setContentView((Activity) context, R.layout.activity_base_activity);
    }

    @Provides
    @AuthScope
    public ActivityAuthBinding activityAuthBinding(@ActivityContext Context context) {
        return DataBindingUtil.setContentView((Activity) context, R.layout.activity_auth);
    }
}
