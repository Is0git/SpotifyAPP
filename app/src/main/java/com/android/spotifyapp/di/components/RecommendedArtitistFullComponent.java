package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.scopes.FullRecommenedScope;
import com.android.spotifyapp.ui.fragment.view.RecommendedArtistsFragment;

import dagger.Component;
@FullRecommenedScope
@Component(modules = {FragmentBindingModule.class})
public interface RecommendedArtitistFullComponent {
    void inject(RecommendedArtistsFragment recommendedArtistsFragment);
}
