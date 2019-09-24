package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.homeRepositories.HomeRepository;
import com.android.spotifyapp.di.scopes.HomeRepositoryScope;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@HomeRepositoryScope
public interface HomeRepositoryComponent {
    void inject(HomeRepository homeRepository);
}
