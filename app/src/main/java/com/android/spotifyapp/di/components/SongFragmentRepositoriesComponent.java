package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.libraryRepositories.SongsFragmentRepository;
import com.android.spotifyapp.di.scopes.SongsLibraryScope;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@SongsLibraryScope
public interface SongFragmentRepositoriesComponent {

    void inject(SongsFragmentRepository songsFragmentRepository);
}
