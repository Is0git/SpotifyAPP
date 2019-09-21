package com.android.spotifyapp.di.modules;

import android.util.Log;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.viewModelPackage.AlbumFullViewModel;
import com.android.spotifyapp.data.viewModelPackage.ArtistViewModel;
import com.android.spotifyapp.data.viewModelPackage.HomeViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistFullViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistViewModel;
import com.android.spotifyapp.data.viewModelPackage.PlaylistTracksViewModel;
import com.android.spotifyapp.data.viewModelPackage.RecentlyPlayedViewModel;
import com.android.spotifyapp.databinding.AlbumFullFragmentBinding;
import com.android.spotifyapp.databinding.ArtistLayoutBinding;
import com.android.spotifyapp.databinding.FullMyplaylistsBinding;
import com.android.spotifyapp.databinding.FullRecentlyplayedBinding;
import com.android.spotifyapp.databinding.FullRecommendedBinding;
import com.android.spotifyapp.databinding.HomeFragmentBinding;
import com.android.spotifyapp.databinding.PlaylistTracksFragmentBinding;
import com.android.spotifyapp.di.scopes.AlbumFullFragmentScope;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.di.scopes.FullPlaylistScope;
import com.android.spotifyapp.di.scopes.FullRecommenedScope;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;
import com.android.spotifyapp.di.scopes.PlaylistTracksScope;
import com.android.spotifyapp.di.scopes.RecentlyPlayedScope;
import com.android.spotifyapp.ui.activities.BaseActivity;
import com.android.spotifyapp.ui.fragment.ArtistFragment;
import com.android.spotifyapp.ui.fragment.PlaylistTracksFragment;

import java.util.Objects;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentBindingModule {
    private LayoutInflater inflater;
    private Fragment fragment;


    public FragmentBindingModule(LayoutInflater inflater, Fragment fragment) {
        this.inflater = inflater;
        this.fragment = fragment;
    }

    @Provides
    @HomeFragmentScope
    public HomeFragmentBinding homeFragmentBinding(HomeViewModel homeViewModel, MyPlaylistViewModel myPlaylistViewModel) {
        HomeFragmentBinding homeFragmentBinding = HomeFragmentBinding.inflate(inflater);
        homeFragmentBinding.setHomeViewModel(homeViewModel);
        homeFragmentBinding.setPlaylistViewModel(myPlaylistViewModel);
        homeFragmentBinding.setLifecycleOwner(Objects.requireNonNull(((BaseActivity) inflater.getContext()).getSupportFragmentManager().findFragmentById(R.id.fragment_container)).getViewLifecycleOwner());
        return homeFragmentBinding;
    }

    @Provides
    @ArtistFragmentScope
    public ArtistLayoutBinding artistLayoutBinding(ArtistViewModel artistViewModel) {
        Log.d("HELPME", "artistLayoutBinding: " );
        ArtistLayoutBinding artistLayoutBinding = ArtistLayoutBinding.inflate(inflater);
        artistLayoutBinding.setArtistViewModel(artistViewModel);
        artistLayoutBinding.setLifecycleOwner(((ArtistFragment) fragment).getViewLifecycleOwner());
        return artistLayoutBinding;
    }
    @Provides
    @RecentlyPlayedScope
    public FullRecentlyplayedBinding fullRecentlyplayedBinding(RecentlyPlayedViewModel recentlyPlayedViewModel) {
        FullRecentlyplayedBinding fullRecentlyplayedBinding = FullRecentlyplayedBinding.inflate(inflater);
        fullRecentlyplayedBinding.setViewModel(recentlyPlayedViewModel);
        fullRecentlyplayedBinding.setLifecycleOwner(fragment.getViewLifecycleOwner());
        return fullRecentlyplayedBinding;
    }

    @Provides
    @FullPlaylistScope
    FullMyplaylistsBinding fullMyplaylistsBinding(MyPlaylistFullViewModel myPlaylistFullViewModel) {
        FullMyplaylistsBinding fullMyplaylistsBinding = FullMyplaylistsBinding.inflate(inflater);
        fullMyplaylistsBinding.setViewModel(myPlaylistFullViewModel);
        fullMyplaylistsBinding.setLifecycleOwner(fragment.getViewLifecycleOwner());
        return fullMyplaylistsBinding;
    }

    @Provides
    @FullRecommenedScope
    FullRecommendedBinding fullRecommendedBinding() {
        FullRecommendedBinding fullRecommendedBinding = FullRecommendedBinding.inflate(inflater);
        return  fullRecommendedBinding;
    }

    @Provides
    @AlbumFullFragmentScope
    AlbumFullFragmentBinding albumFullFragmentBinding(AlbumFullViewModel albumFullViewModel) {
        AlbumFullFragmentBinding albumFullFragmentBinding = AlbumFullFragmentBinding.inflate(inflater);
        albumFullFragmentBinding.setAlbumViewModel(albumFullViewModel);
        albumFullFragmentBinding.setLifecycleOwner(fragment.getViewLifecycleOwner());
        return albumFullFragmentBinding;
    }

    @Provides
    @PlaylistTracksScope
    PlaylistTracksFragmentBinding playlistTracksFragmentBinding(PlaylistTracksViewModel playlistTracksViewModel) {
        PlaylistTracksFragmentBinding playlistTracksFragmentBinding = PlaylistTracksFragmentBinding.inflate(inflater);
        playlistTracksFragmentBinding.setViewModel(playlistTracksViewModel);
        playlistTracksFragmentBinding.setLifecycleOwner(fragment.getViewLifecycleOwner());
        return playlistTracksFragmentBinding;

    }

}
