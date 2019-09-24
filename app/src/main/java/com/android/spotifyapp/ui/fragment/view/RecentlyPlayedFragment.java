package com.android.spotifyapp.ui.fragment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.spotifyapp.data.viewModelPackage.homeViewModels.RecentlyPlayedViewModel;
import com.android.spotifyapp.databinding.FullRecentlyplayedBinding;
import com.android.spotifyapp.di.components.DaggerRecentlyPlayedComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.homeadapters.HomeFull.RecentlyPlayedFull;

import javax.inject.Inject;

public class RecentlyPlayedFragment extends Fragment {
    @Inject
    FullRecentlyplayedBinding fullRecentlyplayedBinding;
    @Inject
    RecentlyPlayedViewModel recentlyPlayedViewModel;
    @Inject
    RecentlyPlayedFull adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerRecentlyPlayedComponent.builder()
                .fragmentBindingModule(new FragmentBindingModule(inflater, this))
                .viewModelsModule(new ViewModelsModule(this))
                .adaptersModule(new AdaptersModule())
                .build().inject(this);
        fullRecentlyplayedBinding.recentlyRecyclerView.setAdapter(adapter);
        recentlyPlayedViewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), items -> adapter.submitList(items));

        recentlyPlayedViewModel.getState().observe(getViewLifecycleOwner(), networkState -> {});
        return fullRecentlyplayedBinding.getRoot();
    }
}
