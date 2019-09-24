package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.libraryRepositories.AlbumRepository;
import com.android.spotifyapp.di.scopes.AlbumScope;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@AlbumScope
public interface AlbumRepositoryComponent {
    void inject(AlbumRepository albumRepository);
}
