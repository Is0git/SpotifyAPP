package com.android.spotifyapp.di.modules;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.spotifyapp.data.viewModelPackage.homeViewModels.AlbumFullViewModel;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.ArtistViewModel;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.AuthViewModel;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.HomeViewModel;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.MyPlaylistFullViewModel;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.MyPlaylistViewModel;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.PlaylistTracksViewModel;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.RecentlyPlayedViewModel;
import com.android.spotifyapp.data.viewModelPackage.YoutubePlayerViewmodel;
import com.android.spotifyapp.data.viewModelPackage.libraryViewModels.AlbumsLibraryViewModel;
import com.android.spotifyapp.data.viewModelPackage.libraryViewModels.SongsLibraryViewModel;
import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.AlbumFullFragmentScope;
import com.android.spotifyapp.di.scopes.AlbumScope;
import com.android.spotifyapp.di.scopes.AuthScope;
import com.android.spotifyapp.di.scopes.FullPlaylistScope;
import com.android.spotifyapp.di.scopes.PlaylistTracksScope;
import com.android.spotifyapp.di.scopes.RecentlyPlayedScope;
import com.android.spotifyapp.di.scopes.SongsLibraryScope;
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
        return ViewModelProviders.of(fragment.getActivity()).get(HomeViewModel.class);
    }

    @Provides
     MyPlaylistViewModel myPlaylistViewModel() {
        return ViewModelProviders.of(fragment.getActivity()).get(MyPlaylistViewModel.class);

    }

    @Provides
    @YoutubeScope
     YoutubePlayerViewmodel youtubePlayerViewmodel() {
        return ViewModelProviders.of(fragment.getActivity()).get(YoutubePlayerViewmodel.class);
    }

    @Provides
     ArtistViewModel artistViewModel() {
        return ViewModelProviders.of(fragment.getActivity()).get(ArtistViewModel.class);
    }

    @Provides
    @AuthScope

     AuthViewModel authViewModel(@ActivityContext Context context) {
        return ViewModelProviders.of((AuthActivity) context).get(AuthViewModel.class);
    }
    @Provides
    @RecentlyPlayedScope

     RecentlyPlayedViewModel recentlyPlayedViewModel() {
        return ViewModelProviders.of(fragment.getActivity()).get(RecentlyPlayedViewModel.class);
    }

    @Provides
    @FullPlaylistScope
     MyPlaylistFullViewModel myPlaylistFullViewModel() {
        return ViewModelProviders.of(fragment.getActivity()).get(MyPlaylistFullViewModel.class);
    }
    @Provides
    @AlbumFullFragmentScope
     AlbumFullViewModel albumFullViewModel() {
        return ViewModelProviders.of(fragment.getActivity()).get(AlbumFullViewModel.class);
    }

    @Provides
    @PlaylistTracksScope
     PlaylistTracksViewModel playlistTracksViewModel() {
        return ViewModelProviders.of(fragment.getActivity()).get(PlaylistTracksViewModel.class);
    }

    @Provides
    @SongsLibraryScope
    SongsLibraryViewModel songsLibraryViewModel() {
        return ViewModelProviders.of(fragment.getActivity()).get(SongsLibraryViewModel.class);
    }
    @Provides
    @AlbumScope
    AlbumsLibraryViewModel albumsLibraryViewModel() {
        return ViewModelProviders.of(fragment.getActivity()).get(AlbumsLibraryViewModel.class);
    }
}
