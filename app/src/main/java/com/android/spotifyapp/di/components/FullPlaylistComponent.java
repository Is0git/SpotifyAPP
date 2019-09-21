package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.FullPlaylistScope;
import com.android.spotifyapp.ui.fragment.view.MyPlaylistFragment;

import dagger.Component;

@FullPlaylistScope
@Component(modules = {FragmentBindingModule.class, AdaptersModule.class, ViewModelsModule.class})
public interface FullPlaylistComponent {
    void inject(MyPlaylistFragment myPlaylistFragment);
}
