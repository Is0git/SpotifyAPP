package com.android.spotifyapp.di.modules;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.spotifyapp.data.viewModelPackage.AlbumFullViewModel;
import com.android.spotifyapp.data.viewModelPackage.ArtistViewModel;
import com.android.spotifyapp.data.viewModelPackage.AuthViewModel;
import com.android.spotifyapp.data.viewModelPackage.HomeViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistFullViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistViewModel;
import com.android.spotifyapp.data.viewModelPackage.PlaylistTracksViewModel;
import com.android.spotifyapp.data.viewModelPackage.RecentlyPlayedViewModel;
import com.android.spotifyapp.data.viewModelPackage.YoutubePlayerViewmodel;
import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.AlbumFullFragmentScope;
import com.android.spotifyapp.di.scopes.AuthScope;
import com.android.spotifyapp.di.scopes.FullPlaylistScope;
import com.android.spotifyapp.di.scopes.PlaylistTracksScope;
import com.android.spotifyapp.di.scopes.RecentlyPlayedScope;
import com.android.spotifyapp.di.scopes.YoutubeScope;
import com.android.spotifyapp.ui.activities.AuthActivity;
import com.android.spotifyapp.ui.fragment.AlbumFullFragment;
import com.android.spotifyapp.ui.fragment.ArtistFragment;
import com.android.spotifyapp.ui.fragment.HomeFragment;
import com.android.spotifyapp.ui.fragment.YoutubeFragment;
import com.android.spotifyapp.ui.fragment.view.MyPlaylistFragment;
import com.android.spotifyapp.ui.fragment.view.RecentlyPlayedFragment;

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
    @Provides
    @RecentlyPlayedScope

    public RecentlyPlayedViewModel recentlyPlayedViewModel() {
        return ViewModelProviders.of((RecentlyPlayedFragment) fragment).get(RecentlyPlayedViewModel.class);
    }

    @Provides
    @FullPlaylistScope
    public MyPlaylistFullViewModel myPlaylistFullViewModel() {
        return ViewModelProviders.of((MyPlaylistFragment) fragment).get(MyPlaylistFullViewModel.class);
    }
    @Provides
    @AlbumFullFragmentScope
    public AlbumFullViewModel albumFullViewModel() {
        return ViewModelProviders.of((AlbumFullFragment)fragment).get(AlbumFullViewModel.class);
    }

    @Provides
    @PlaylistTracksScope
    public PlaylistTracksViewModel playlistTracksViewModel() {
        return ViewModelProviders.of(fragment).get(PlaylistTracksViewModel.class);
    }
}
