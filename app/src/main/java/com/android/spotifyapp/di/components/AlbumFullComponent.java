package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.AlbumFullFragmentScope;
import com.android.spotifyapp.ui.fragment.AlbumFullFragment;

import dagger.Component;

@Component(modules = {ViewModelsModule.class, FragmentBindingModule.class, AdaptersModule.class})
@AlbumFullFragmentScope
public interface AlbumFullComponent {
    void inject(AlbumFullFragment albumFullFragment);
}
