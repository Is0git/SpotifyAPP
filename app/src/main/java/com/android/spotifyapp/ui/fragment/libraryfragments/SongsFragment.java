package com.android.spotifyapp.ui.fragment.libraryfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.android.spotifyapp.data.network.model.libmodel.Tracks;
import com.android.spotifyapp.data.viewModelPackage.libraryViewModels.SongsLibraryViewModel;
import com.android.spotifyapp.databinding.SongsFragmentBinding;
import com.android.spotifyapp.di.components.DaggerSongsComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.libraryadapters.SongsAdapter;

import javax.inject.Inject;

public class SongsFragment extends Fragment {
    @Inject
    SongsFragmentBinding songsFragmentBinding;
    @Inject
    SongsLibraryViewModel songsLibraryViewModel;
    @Inject
    SongsAdapter songsAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerSongsComponent.builder()
                .fragmentBindingModule(new FragmentBindingModule(inflater, this))
                .viewModelsModule(new ViewModelsModule(this))
                .adaptersModule(new AdaptersModule())
                .build().inject(this);

        songsFragmentBinding.songsRecyclerView.setAdapter(songsAdapter);
        songsLibraryViewModel.getTracksLiveData(30).observe(getViewLifecycleOwner(), tracks -> songsAdapter.setTracks(tracks));

        return songsFragmentBinding.getRoot();
    }
}
