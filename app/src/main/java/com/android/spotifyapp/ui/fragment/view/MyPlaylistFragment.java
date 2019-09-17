package com.android.spotifyapp.ui.fragment.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;

import com.android.spotifyapp.data.network.dataSources.AlbumDataSource;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.viewModelPackage.AlbumFullViewModel;
import com.android.spotifyapp.databinding.FullMyplaylistsBinding;
import com.android.spotifyapp.di.components.DaggerFullPlaylistComponent;
import com.android.spotifyapp.di.modules.FragmentBindingModule;

import javax.inject.Inject;

public class MyPlaylistFragment extends Fragment {
    @Inject
    FullMyplaylistsBinding fullMyplaylistsBinding;
    AlbumFullViewModel albumFullViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("makeitwork", "onCreateView: " + "LOL");
        DaggerFullPlaylistComponent
                .builder().fragmentBindingModule(new FragmentBindingModule(inflater, this))
                .build().inject(this);
        albumFullViewModel = ViewModelProviders.of(this).get(AlbumFullViewModel.class);
//        albumFullViewModel.getLiveDataPageList().observe(getViewLifecycleOwner(), new Observer<PagedList<Artistsalbum.Items>>() {
//            @Override
//            public void onChanged(PagedList<Artistsalbum.Items> items) {
////                Log.d("WUTT", "onChanged: " +  items.get(0).getId());
////                Toast.makeText(getContext(), "tast: " + items.get(0).getId(), Toast.LENGTH_SHORT).show();
//            }
//        });



        return fullMyplaylistsBinding.getRoot();
    }
}
