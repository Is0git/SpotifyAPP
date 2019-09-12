package com.android.spotifyapp.di.modules;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.viewModelPackage.ArtistViewModel;
import com.android.spotifyapp.data.viewModelPackage.HomeViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistViewModel;
import com.android.spotifyapp.databinding.ActivityAuthBinding;
import com.android.spotifyapp.databinding.ArtistLayoutBinding;
import com.android.spotifyapp.databinding.HomeFragmentBinding;
import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.di.scopes.AuthScope;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;
import com.android.spotifyapp.ui.activities.BaseActivity;
import com.android.spotifyapp.ui.fragment.ArtistFragment;
import com.android.spotifyapp.ui.fragment.HomeFragment;

import java.util.Objects;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBindingModule {
    private LayoutInflater inflater;
    private ViewGroup container;


    public DataBindingModule(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        this.container = container;
    }

    @Provides
    @AuthScope
    public ActivityAuthBinding activityAuthBinding(@ActivityContext Context context) {
        return DataBindingUtil.setContentView((Activity) context, R.layout.activity_auth);
    }

    @Provides
    @HomeFragmentScope
    public HomeFragmentBinding homeFragmentBinding(HomeViewModel homeViewModel, MyPlaylistViewModel myPlaylistViewModel) {
        HomeFragmentBinding homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        homeFragmentBinding.setHomeViewModel(homeViewModel);
        homeFragmentBinding.setPlaylistViewModel(myPlaylistViewModel);
        homeFragmentBinding.setLifecycleOwner(Objects.requireNonNull(((BaseActivity) container.getContext()).getSupportFragmentManager().findFragmentById(R.id.fragment_container)).getViewLifecycleOwner());
        return homeFragmentBinding;
    }

    @Provides
    @ArtistFragmentScope
    public ArtistLayoutBinding artistLayoutBinding(ArtistViewModel artistViewModel) {
        ArtistLayoutBinding artistLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        artistLayoutBinding.setArtistViewModel(artistViewModel);
        artistLayoutBinding.setLifecycleOwner(Objects.requireNonNull(((BaseActivity) container.getContext()).getSupportFragmentManager().findFragmentById(R.id.fragment_container)).getViewLifecycleOwner());
        return artistLayoutBinding;
    }
}
