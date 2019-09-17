package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.scopes.RecentlyPlayedScope;
import com.android.spotifyapp.ui.fragment.view.RecentlyPlayedFragment;

import dagger.Component;

@Component(modules = {FragmentBindingModule.class})
@RecentlyPlayedScope
public interface RecentlyPlayedComponent {
    void inject(RecentlyPlayedFragment recentlyPlayedFragment);
}
