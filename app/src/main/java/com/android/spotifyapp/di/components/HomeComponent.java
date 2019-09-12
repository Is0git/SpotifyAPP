package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.DataBindingModule;
import com.android.spotifyapp.di.modules.DialogModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;
import com.android.spotifyapp.ui.fragment.HomeFragment;

import dagger.Component;
@HomeFragmentScope
@Component(modules = {AdaptersModule.class, ViewModelsModule.class, DialogModule.class, DataBindingModule.class})

public interface HomeComponent {

    void injectFragment(HomeFragment homeFragment);


}
