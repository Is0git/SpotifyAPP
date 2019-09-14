package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.ActivityBindingModule;
import com.android.spotifyapp.di.modules.ActivityViewModelModule;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.CustomViewBindingMoudle;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.scopes.BaseActivityScope;
import com.android.spotifyapp.ui.activities.BaseActivity;

import dagger.Component;
@BaseActivityScope
@Component(modules = {ActivityViewModelModule.class, FragmentBindingModule.class, ContextModule.class, ActivityBindingModule.class, CustomViewBindingMoudle.class})
public interface BaseComponent {
    void injectActivity(BaseActivity baseActivity);
}
