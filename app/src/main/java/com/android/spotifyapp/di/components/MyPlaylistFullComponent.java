package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.MyPlaylistFullRepository;
import com.android.spotifyapp.di.scopes.MyPlaylistFullScope;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@MyPlaylistFullScope
public interface MyPlaylistFullComponent {
    void inject(MyPlaylistFullRepository myPlaylistFullRepository);
}
