package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.homeRepositories.ArtistRepository;
import com.android.spotifyapp.di.scopes.ArtistRepositoryScope;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@ArtistRepositoryScope
public interface ArtistRepositoryComponent {
    void inject(ArtistRepository artistRepository);
}
