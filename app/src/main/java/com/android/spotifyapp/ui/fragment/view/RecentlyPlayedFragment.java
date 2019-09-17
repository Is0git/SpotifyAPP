package com.android.spotifyapp.ui.fragment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.spotifyapp.databinding.RecentlyPlayedFragmentBinding;
import com.android.spotifyapp.di.components.DaggerRecentlyPlayedComponent;
import com.android.spotifyapp.di.modules.FragmentBindingModule;

import javax.inject.Inject;

public class RecentlyPlayedFragment extends Fragment {
@Inject RecentlyPlayedFragmentBinding recentlyPlayedFragmentBinding;
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    DaggerRecentlyPlayedComponent.builder()
            .fragmentBindingModule(new FragmentBindingModule(inflater, this))
            .build().inject(this);
    
    return recentlyPlayedFragmentBinding.getRoot();
}
}
