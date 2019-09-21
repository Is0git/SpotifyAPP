package com.android.spotifyapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.viewModelPackage.AlbumFullViewModel;
import com.android.spotifyapp.databinding.AlbumFullFragmentBinding;
import com.android.spotifyapp.di.components.DaggerAlbumFullComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.States.NetworkState;
import com.android.spotifyapp.ui.adapters.Artist.ArtistFull.AlbumFullAdapter;

import javax.inject.Inject;

public class AlbumFullFragment extends Fragment {
    @Inject AlbumFullFragmentBinding albumFullFragmentBinding;
    @Inject AlbumFullViewModel albumFullViewModel;
    @Inject AlbumFullAdapter albumFullAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerAlbumFullComponent.builder()
                .fragmentBindingModule(new FragmentBindingModule(inflater, this))
                .viewModelsModule(new ViewModelsModule(this))
                .adaptersModule(new AdaptersModule())
                .build().inject(this);
        if (getArguments() != null) {
            albumFullViewModel.init(getArguments().getString("id"));
        }
        albumFullFragmentBinding.fullRecyclerView.setAdapter(albumFullAdapter);
        albumFullViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), items -> albumFullAdapter.submitList(items));

        //Recyclerview progress logic is in fragment xml file
        albumFullViewModel.getState().observe(getViewLifecycleOwner(), networkState -> { });

        return albumFullFragmentBinding.getRoot();
    }
}
