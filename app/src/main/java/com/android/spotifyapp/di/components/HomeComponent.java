package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.HomeRepository;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.DialogModule;
import com.android.spotifyapp.di.modules.RecyclerViewModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;
import com.android.spotifyapp.ui.fragment.HomeFragment;

import dagger.Component;
@HomeFragmentScope
@Component(modules = {RecyclerViewModule.class, AdaptersModule.class, ViewModelsModule.class, DialogModule.class}, dependencies = {AppComponent.class})

public interface HomeComponent {
    void inject(HomeRepository homeRepository);
    void injectFragment(HomeFragment homeFragment);


}
