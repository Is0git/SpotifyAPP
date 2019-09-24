package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.SongsLibraryScope;
import com.android.spotifyapp.ui.fragment.libraryfragments.SongsFragment;

import dagger.Component;

@Component(modules = {ViewModelsModule.class, FragmentBindingModule.class, AdaptersModule.class})
@SongsLibraryScope
public interface SongsComponent {
    void inject(SongsFragment songsFragment);
}
