package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.homeRepositories.BaseRepository;
import com.android.spotifyapp.di.scopes.BaseRepositoryScope;

import dagger.Component;

@Component(dependencies = {AppComponent.class})
@BaseRepositoryScope
public interface BaseRepositoryComponent {
    void inject(BaseRepository baseRepository);
}
