package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.homeRepositories.AlbumFullRepository;
import com.android.spotifyapp.di.scopes.AlbumFullRepositoryScope;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@AlbumFullRepositoryScope
public interface AlbumFullRepositoryComponent {

    void inject(AlbumFullRepository albumFullRepository);
}
