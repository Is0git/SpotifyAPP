package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.ui.fragment.ArtistFragment;

import dagger.Component;

@ArtistFragmentScope
@Component(modules = {AdaptersModule.class, ViewModelsModule.class, AdaptersModule.class, ContextModule.class, FragmentBindingModule.class})

public interface ArtistComponent {
    void injectFragment(ArtistFragment artistFragment);
}
