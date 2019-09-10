package com.android.spotifyapp.di.modules;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.spotifyapp.data.ViewModels.ArtistViewModel;
import com.android.spotifyapp.data.ViewModels.AuthViewModel;
import com.android.spotifyapp.data.ViewModels.HomeViewModel;
import com.android.spotifyapp.data.ViewModels.MyPlaylistViewModel;
import com.android.spotifyapp.data.ViewModels.YoutubePlayerViewmodel;
import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.AuthScope;
import com.android.spotifyapp.di.scopes.YoutubeScope;
import com.android.spotifyapp.ui.activities.AuthActivity;
import com.android.spotifyapp.ui.fragment.ArtistFragment;
import com.android.spotifyapp.ui.fragment.HomeFragment;
import com.android.spotifyapp.ui.fragment.YoutubeFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelsModule {
   private Fragment fragment;

    public ViewModelsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public HomeViewModel homeViewModel() {
        return ViewModelProviders.of((HomeFragment) fragment).get(HomeViewModel.class);
    }

    @Provides
    public MyPlaylistViewModel myPlaylistViewModel() {
        return ViewModelProviders.of((HomeFragment) fragment).get(MyPlaylistViewModel.class);

    }

    @Provides
    @YoutubeScope
    public YoutubePlayerViewmodel youtubePlayerViewmodel() {
        return ViewModelProviders.of((YoutubeFragment) fragment).get(YoutubePlayerViewmodel.class);
    }

    @Provides
    public ArtistViewModel artistViewModel() {
        return ViewModelProviders.of((ArtistFragment) fragment).get(ArtistViewModel.class);
    }

    @Provides
    @AuthScope

    public AuthViewModel authViewModel(@ActivityContext Context context) {
        return ViewModelProviders.of((AuthActivity) context).get(AuthViewModel.class);
    }

}
