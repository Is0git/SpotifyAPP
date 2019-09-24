package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.homeRepositories.PlaylistTracksRepository;
import com.android.spotifyapp.di.scopes.PlaylistTracksScope;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@PlaylistTracksScope
public interface MyPlaylistTracksComponent {
    void inject(PlaylistTracksRepository myPlaylistTracksRepository);
}
