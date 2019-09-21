package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.PlaylistTracksScope;
import com.android.spotifyapp.ui.fragment.PlaylistTracksFragment;

import dagger.Component;

@Component(modules = {AdaptersModule.class, ViewModelsModule.class, FragmentBindingModule.class})
@PlaylistTracksScope
public interface PlaylistTracksComponent {

    void inject(PlaylistTracksFragment playlistTracksFragment);
}
