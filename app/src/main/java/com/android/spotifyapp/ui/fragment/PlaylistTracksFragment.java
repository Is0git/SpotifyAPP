package com.android.spotifyapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.PlaylistTracksViewModel;
import com.android.spotifyapp.databinding.PlaylistTracksFragmentBinding;
import com.android.spotifyapp.di.components.DaggerPlaylistTracksComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.homeadapters.HomeFull.MyPlaylistTracks.PlaylistTracksAdapter;

import javax.inject.Inject;

public class PlaylistTracksFragment extends Fragment {
    @Inject
    PlaylistTracksFragmentBinding playlistTracksFragmentBinding;
    @Inject
    PlaylistTracksViewModel playlistTracksViewModel;
    @Inject
    PlaylistTracksAdapter playlistTracksAdapter;
        NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerPlaylistTracksComponent.builder()
                .adaptersModule(new AdaptersModule())
                .fragmentBindingModule(new FragmentBindingModule(inflater, this))
                .viewModelsModule(new ViewModelsModule(this))
                .build().inject(this);
        if (getArguments() != null) {
            playlistTracksFragmentBinding.title.setText(getArguments().getString("name"));
            playlistTracksViewModel.init(getArguments().getString("id"));
        }
        playlistTracksFragmentBinding.playlistRecyclerView.setAdapter(playlistTracksAdapter);
        playlistTracksViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), items -> playlistTracksAdapter.submitList(items));


        return playlistTracksFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        navController.popBackStack(R.id.action_playlistTracksFragment_to_home, false);
//        navController.navigateUp()
    }
}
