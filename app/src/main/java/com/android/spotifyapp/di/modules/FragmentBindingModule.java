package com.android.spotifyapp.di.modules;

import android.util.Log;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.viewModelPackage.ArtistViewModel;
import com.android.spotifyapp.data.viewModelPackage.HomeViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistViewModel;
import com.android.spotifyapp.databinding.ArtistLayoutBinding;
import com.android.spotifyapp.databinding.FullMyplaylistsBinding;
import com.android.spotifyapp.databinding.FullRecommendedBinding;
import com.android.spotifyapp.databinding.HomeFragmentBinding;
import com.android.spotifyapp.databinding.RecentlyPlayedFragmentBinding;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.di.scopes.FullPlaylistScope;
import com.android.spotifyapp.di.scopes.FullRecommenedScope;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;
import com.android.spotifyapp.di.scopes.RecentlyPlayedScope;
import com.android.spotifyapp.ui.activities.BaseActivity;
import com.android.spotifyapp.ui.fragment.ArtistFragment;

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
    public RecentlyPlayedFragmentBinding recentlyPlayedFragmentBinding() {
        RecentlyPlayedFragmentBinding recentlyPlayedFragmentBinding = RecentlyPlayedFragmentBinding.inflate(inflater);
        return recentlyPlayedFragmentBinding;
    }

    @Provides
    @FullPlaylistScope
    FullMyplaylistsBinding fullMyplaylistsBinding() {
        FullMyplaylistsBinding fullMyplaylistsBinding = FullMyplaylistsBinding.inflate(inflater);
        return fullMyplaylistsBinding;
    }

    @Provides
    @FullRecommenedScope
    FullRecommendedBinding fullRecommendedBinding() {
        FullRecommendedBinding fullRecommendedBinding = FullRecommendedBinding.inflate(inflater);
        return  fullRecommendedBinding;
    }

}
