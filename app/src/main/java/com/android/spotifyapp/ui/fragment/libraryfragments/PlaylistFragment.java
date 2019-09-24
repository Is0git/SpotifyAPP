package com.android.spotifyapp.ui.fragment.libraryfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.android.spotifyapp.databinding.PlaylistFragmentBinding;
import com.android.spotifyapp.ui.adapters.libraryadapters.LibraryViewPager;

public class PlaylistFragment extends Fragment {
    PlaylistFragmentBinding playlistFragmentBinding;
    private LibraryViewPager libraryViewPager;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        playlistFragmentBinding = PlaylistFragmentBinding.inflate(inflater);
        return playlistFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        playlistFragmentBinding.tabLayout.setupWithViewPager(playlistFragmentBinding.pager);
        libraryViewPager = new LibraryViewPager(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        playlistFragmentBinding.pager.setAdapter(libraryViewPager);
    }
}
