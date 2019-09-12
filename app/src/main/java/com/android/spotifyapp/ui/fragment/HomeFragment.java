package com.android.spotifyapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.spotifyapp.App;
import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.Post.MyPlaylistPost;
import com.android.spotifyapp.data.network.model.Recommendations;
import com.android.spotifyapp.data.viewModelPackage.HomeViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistViewModel;
import com.android.spotifyapp.databinding.HomeFragmentBinding;
import com.android.spotifyapp.di.components.DaggerHomeComponent;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.DataBindingModule;
import com.android.spotifyapp.di.modules.DialogModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.activities.BaseActivity;
import com.android.spotifyapp.ui.adapters.homeadapters.HomeHorizontal;
import com.android.spotifyapp.ui.adapters.homeadapters.MyPlaylistsAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.RecommendedAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.SliderAdapter;
import com.android.spotifyapp.ui.globalState.CurrentSongState;
import com.android.spotifyapp.utils.Dialogs.Dialog;
import java.util.Objects;
import javax.inject.Inject;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_followers;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_id;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_image_url;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_name;

public class HomeFragment extends Fragment implements HomeHorizontal.OnItemListener, MyPlaylistsAdapter.PlaylistListener, RecommendedAdapter.onItemListener {
    @Inject HomeHorizontal homeHorizontal;
    @Inject MyPlaylistsAdapter myPlaylistsAdapter;
    @Inject RecommendedAdapter recommendedAdapter;

    @Inject HomeViewModel homeViewModel;
    @Inject MyPlaylistViewModel myPlaylistViewModel;

    @Inject SliderAdapter sliderAdapter;

    @Inject Dialog dialog;

    @Inject HomeFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Dagger
        DaggerHomeComponent.builder()
                .viewModelsModule(new ViewModelsModule(this))
                .dialogModule(new DialogModule(this))
                .contextModule(new ContextModule(getActivity()))
                .dataBindingModule(new DataBindingModule(inflater, container))
                .build().injectFragment(this);

        //Recently played list
        binding.recentlyPlayedList.setAdapter(homeHorizontal);
        homeHorizontal.setListener(this);
        homeViewModel.getRecentlyPlayedLiveData().observe(getViewLifecycleOwner(), recentlyPlayed -> homeHorizontal.UpdateData(recentlyPlayed));

        //MyPlaylist List
        binding.MyPlaylistsList.setAdapter(myPlaylistsAdapter);
        registerForContextMenu(binding.MyPlaylistsList);
        myPlaylistsAdapter.setPlaylistListener(this);
        myPlaylistViewModel.getMyPlaylistLiveData().observe(getViewLifecycleOwner(), myPlaylist -> myPlaylistsAdapter.UpdateList(myPlaylist));

        //Recommended List
        binding.recommendedList.setAdapter(recommendedAdapter);
        recommendedAdapter.setOnItemListener(this);
        homeViewModel.getRecommendationsLiveData().observe(getViewLifecycleOwner(), recommendations -> recommendedAdapter.UpdateData(recommendations));

        //Slider
        binding.slider.setSliderAdapter(sliderAdapter);
        homeViewModel.getUserTopTracksLiveData(5).observe(getViewLifecycleOwner(), userTopTracks -> sliderAdapter.UpdateData(userTopTracks));

        return binding.getRoot();
    }

    //start Player
    @Override
    public void onClick(int position, String title) {
        CurrentSongState currentSongState = CurrentSongState.getInstance();
        currentSongState.setTitle(title);
        ((BaseActivity) Objects.requireNonNull(getActivity())).startPlayer();
    }

    @Override
    public void onPlaylistItemClick(String id) {
        myPlaylistViewModel.setPlaylist_id(id);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Options ");
        Objects.requireNonNull(getActivity()).getMenuInflater().inflate(R.menu.playlist_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)  {
        switch(item.getItemId()) {
            case R.id.item_add:
               dialog.dialogshow();
                return true;
            case R.id.item_delete:
                if(myPlaylistViewModel.getPlaylist_id() != null) {
                    myPlaylistViewModel.deletePlaylist(myPlaylistViewModel.getPlaylist_id());
                }
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && data.getData() != null)
            dialog.selectResult(data);


    }

    public void setData(String title, String description, String url, boolean status) {
//        List<MyPlaylistPost.Images> images = new ArrayList<>();
//        images.add(new MyPlaylistPost.Images(250, "https://i.scdn.co/image/012ecd119617ac24ab56620ace4b81735b172686", null));
        MyPlaylistPost myPlaylistPost = new MyPlaylistPost(title, description, status, false);
//        Log.d("REAL", "setData: " + myPlaylistPost.getImages().get(0).getUrl());
        myPlaylistViewModel.createPlaylist(myPlaylistPost);
//        MyPlaylistPost myPlaylistPost = new MyPlaylistPost(new List<MyPlaylistPost.Images>().add(new MyPlaylistPost.Images(null, url, null)), title, description, status);


    }
    //on recommended artist click
    @Override
    public void onClick(Recommendations recommendations, int position) {
        Bundle params = new Bundle();
        params.putString(artist_name, recommendations.getMtracks().get(position).getMartists().get(0).getName());
        params.putInt(artist_followers, recommendations.getMtracks().get(position).getArtist().getFollowers().getTotal());
        params.putString(artist_image_url, recommendations.getMtracks().get(position).getArtist().getImages().get(0).getUrl());
        params.putString(artist_id, recommendations.getMtracks().get(position).getMartists().get(0).getId());
        Fragment artist_fragment = new ArtistFragment();
        artist_fragment.setArguments(params);
        Objects.requireNonNull(getActivity())
                .getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, artist_fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}

