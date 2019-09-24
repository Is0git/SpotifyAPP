package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.viewModelPackage.homeViewModels.MyPlaylistFullViewModel;
import com.android.spotifyapp.di.scopes.AlbumFullViewmodelScope;

import dagger.Component;
@AlbumFullViewmodelScope
@Component(dependencies = {AppComponent.class})
public interface AlbumFullViewmodelComponent {
    void inject(MyPlaylistFullViewModel albumFullViewModel);
}
