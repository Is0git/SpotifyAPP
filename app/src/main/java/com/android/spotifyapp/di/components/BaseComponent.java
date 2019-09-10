package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.BaseRepository;
import com.android.spotifyapp.di.modules.ActivityViewModelModule;
import com.android.spotifyapp.di.scopes.BaseActivityScope;
import com.android.spotifyapp.ui.activities.BaseActivity;

import dagger.Component;
@BaseActivityScope
@Component(modules = {ActivityViewModelModule.class}, dependencies = {AppComponent.class})
public interface BaseComponent {
    void inject(BaseRepository baseRepository);
    void injectActivity(BaseActivity baseActivity);
}
