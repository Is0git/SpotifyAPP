package com.android.spotifyapp.ui.fragment.view;

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
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistFullViewModel;
import com.android.spotifyapp.databinding.FullMyplaylistsBinding;
import com.android.spotifyapp.di.components.DaggerFullPlaylistComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.Artist.ArtistFull.MyPlaylistFullAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.MyPlaylistsAdapter;

import javax.inject.Inject;

public class MyPlaylistFragment extends Fragment implements MyPlaylistsAdapter.PlaylistListener {
    @Inject FullMyplaylistsBinding fullMyplaylistsBinding;
    @Inject MyPlaylistFullViewModel albumFullViewModel;
    @Inject MyPlaylistFullAdapter myPlaylistFullAdapter;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerFullPlaylistComponent
                .builder().fragmentBindingModule(new FragmentBindingModule(inflater, this))
                .viewModelsModule(new ViewModelsModule(this))
                .adaptersModule(new AdaptersModule())
                .build().inject(this);

        fullMyplaylistsBinding.fullRecyclerView.setAdapter(myPlaylistFullAdapter);
        myPlaylistFullAdapter.setListener(this);
        albumFullViewModel.getLiveDataPageList().observe(getViewLifecycleOwner(), items -> myPlaylistFullAdapter.submitList(items));

        albumFullViewModel.getState().observe(getViewLifecycleOwner(), networkState -> {});
        return fullMyplaylistsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onPlaylistItemClick(String id) {

    }

    @Override
    public void playlistOnClick(String id, String name) {
        Bundle params = new Bundle();
        params.putString("name", name);
        params.putString("id", id);
        navController.navigate(R.id.action_myPlaylistFragment_to_playlistTracksFragment, params);
    }
}
