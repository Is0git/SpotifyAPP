package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.data.repositories.RecentlyPlayedRepository;
import com.android.spotifyapp.di.scopes.RecentlyPlayedScope;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@RecentlyPlayedScope
public interface RecentlyPlayedRepositoryComponent {
    void inject(RecentlyPlayedRepository recentlyPlayedRepository);
}
