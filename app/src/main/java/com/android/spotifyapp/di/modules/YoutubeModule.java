package com.android.spotifyapp.di.modules;

import com.android.spotifyapp.di.components.AppComponent;
import com.android.spotifyapp.di.qualifiers.YoutubeQualifier;
import com.android.spotifyapp.di.scopes.YoutubeScope;
import com.android.spotifyapp.utils.Contracts.YoutubeAPIContract;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class YoutubeModule {
    @Provides
    @YoutubeScope
    HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }
    @Provides
    @YoutubeScope

    OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @YoutubeScope
    @Provides
    @YoutubeQualifier
    public Retrofit retrofit( OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(YoutubeAPIContract.getBaseUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
