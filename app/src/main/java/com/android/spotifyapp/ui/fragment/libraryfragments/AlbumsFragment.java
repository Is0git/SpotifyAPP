package com.android.spotifyapp.ui.fragment.libraryfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.spotifyapp.data.viewModelPackage.libraryViewModels.AlbumsLibraryViewModel;
import com.android.spotifyapp.databinding.AlbumFragmentBinding;
import com.android.spotifyapp.di.components.DaggerAlbumComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.libraryadapters.AlbumLibraryAdapter;

import javax.inject.Inject;

public class AlbumsFragment extends Fragment {
    @Inject
    AlbumFragmentBinding albumFragmentBinding;
    @Inject
    AlbumsLibraryViewModel albumsLibraryViewModel;
    @Inject
    AlbumLibraryAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerAlbumComponent.builder()
                .adaptersModule(new AdaptersModule())
                .fragmentBindingModule(new FragmentBindingModule(inflater, this))
                .viewModelsModule(new ViewModelsModule(this))
                .build().inject(this);
        albumFragmentBinding.albumsRecyclerView.setAdapter(albumAdapter);
        albumsLibraryViewModel.getAlbums(20).observe(getViewLifecycleOwner(), albums -> albumAdapter.setAlbums(albums));
        return albumFragmentBinding.getRoot();
    }
}
