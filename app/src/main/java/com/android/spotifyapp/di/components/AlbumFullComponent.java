package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.Artist.ArtistFull.AlbumFullFragment;

import dagger.Component;

@Component(modules = {ViewModelsModule.class})
public interface AlbumFullComponent {
    void inject(AlbumFullFragment albumFullFragment);
}
