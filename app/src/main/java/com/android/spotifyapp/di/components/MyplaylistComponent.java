package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.MyPlaylistRepository;
import com.android.spotifyapp.di.modules.MyplaylistModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {MyplaylistModule.class})
@Singleton
public interface MyplaylistComponent {
    void inject(MyPlaylistRepository myPlaylistRepository);
}
