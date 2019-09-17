package com.android.spotifyapp.di.components;

import android.app.Application;

import com.android.spotifyapp.data.viewModelPackage.AlbumFullViewModel;
import com.android.spotifyapp.di.scopes.AlbumFullViewmodelScope;

import dagger.Component;
@AlbumFullViewmodelScope
@Component(dependencies = {AppComponent.class})
public interface AlbumFullViewmodelComponent {
    void inject(AlbumFullViewModel albumFullViewModel);
}
