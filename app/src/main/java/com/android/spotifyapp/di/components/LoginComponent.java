package com.android.spotifyapp.di.components;

import com.android.spotifyapp.data.repositories.AuthRepository;
import com.android.spotifyapp.di.modules.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {LoginModule.class})
@Singleton

public interface LoginComponent {
    void inject(AuthRepository authRepository);
}
