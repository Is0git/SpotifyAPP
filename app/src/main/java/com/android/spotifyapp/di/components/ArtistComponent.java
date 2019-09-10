package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.ArtistRepository;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.RecyclerViewModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.ui.fragment.ArtistFragment;

import dagger.Component;

@ArtistFragmentScope
@Component(modules = {AdaptersModule.class, ViewModelsModule.class, RecyclerViewModule.class, AdaptersModule.class, ContextModule.class},dependencies = {AppComponent.class})

public interface ArtistComponent {
    void injectRepository(ArtistRepository artistRepository);
    void injectFragment(ArtistFragment artistFragment);
}
