package com.android.spotifyapp.ui.adapters.Artist.ArtistFull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.spotifyapp.databinding.AlbumFullFragmentBinding;

public class AlbumFullFragment extends Fragment {
    private AlbumFullFragmentBinding albumFullFragmentBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        albumFullFragmentBinding = AlbumFullFragmentBinding.inflate(inflater);
        return albumFullFragmentBinding.getRoot();
    }
}
