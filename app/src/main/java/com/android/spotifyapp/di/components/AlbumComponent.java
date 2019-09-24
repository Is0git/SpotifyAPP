package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.AlbumScope;
import com.android.spotifyapp.ui.fragment.libraryfragments.AlbumsFragment;

import dagger.Component;

@Component(modules = {ViewModelsModule.class, AdaptersModule.class, FragmentBindingModule.class})
@AlbumScope
public interface AlbumComponent {
    void inject(AlbumsFragment albumsFragment);
}
