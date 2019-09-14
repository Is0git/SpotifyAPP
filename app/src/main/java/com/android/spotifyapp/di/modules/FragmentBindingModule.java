package com.android.spotifyapp.di.modules;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.viewModelPackage.ArtistViewModel;
import com.android.spotifyapp.data.viewModelPackage.HomeViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistViewModel;
import com.android.spotifyapp.databinding.ArtistLayoutBinding;
import com.android.spotifyapp.databinding.HomeFragmentBinding;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;
import com.android.spotifyapp.ui.activities.BaseActivity;

import java.util.Objects;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentBindingModule {
    private LayoutInflater inflater;
    private ViewGroup container;


    public FragmentBindingModule(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        this.container = container;
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
        artistLayoutBinding.setLifecycleOwner(Objects.requireNonNull(((BaseActivity) container.getContext()).getSupportFragmentManager().findFragmentById(R.id.fragment_container)).getViewLifecycleOwner());
        return artistLayoutBinding;
    }


}
