package com.android.spotifyapp.di.modules;

import com.android.spotifyapp.di.qualifiers.AuthQualifier;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.spotifyapp.utils.Contracts.SpotifyAuthContract.POST_BASE_URL;

@Module(includes = {AppModule.class})
public class LoginModule {

    @Provides
    @Singleton
    @AuthQualifier
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return   new Retrofit
                .Builder()
                .baseUrl(POST_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
