package com.android.spotifyapp.di.components;

import com.android.spotifyapp.di.modules.AppModule;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    @RetrofitQualifier
    Retrofit getRetrofit();
}
