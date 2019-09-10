package com.android.spotifyapp.di.components;

import androidx.lifecycle.ViewModelProviders;

import com.android.spotifyapp.data.repositories.YoutubePlayerRepository;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.modules.YoutubeModule;
import com.android.spotifyapp.di.qualifiers.YoutubeQualifier;
import com.android.spotifyapp.di.scopes.YoutubeScope;
import com.android.spotifyapp.ui.fragment.YoutubeFragment;

import dagger.Component;
import okhttp3.OkHttpClient;

@YoutubeScope
@Component(modules = {YoutubeModule.class, ViewModelsModule.class})
public interface YoutubeComponent {

    void inject(YoutubePlayerRepository youtubePlayerRepository);
    void injectFragment(YoutubeFragment youtubeFragment);
}
