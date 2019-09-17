package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.scopes.FullPlaylistScope;
import com.android.spotifyapp.ui.fragment.view.MyPlaylistFragment;

import dagger.Component;

@FullPlaylistScope
@Component(modules = {FragmentBindingModule.class})
public interface FullPlaylistComponent {
    void inject(MyPlaylistFragment myPlaylistFragment);
}
