package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.DataBindingModule;
import com.android.spotifyapp.di.modules.RXModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.scopes.AuthScope;
import com.android.spotifyapp.ui.activities.AuthActivity;

import dagger.Component;
@AuthScope
@Component(modules = {ViewModelsModule.class, ContextModule.class, RXModule.class, DataBindingModule.class})
public interface AuthComponent {
    void inject(AuthActivity authActivity);
}
