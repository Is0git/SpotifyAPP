package com.android.spotifyapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.Post.MyPlaylistPost;
import com.android.spotifyapp.data.viewModelPackage.HomeViewModel;
import com.android.spotifyapp.data.viewModelPackage.MyPlaylistViewModel;
import com.android.spotifyapp.databinding.HomeFragmentBinding;
import com.android.spotifyapp.di.components.DaggerHomeComponent;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.DialogModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.homeadapters.HomeHorizontal;
import com.android.spotifyapp.ui.adapters.homeadapters.MyPlaylistsAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.RecommendedAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.SliderAdapter;
import com.android.spotifyapp.ui.globalState.CurrentSongState;
import com.android.spotifyapp.ui.youtube.Player;
import com.android.spotifyapp.utils.Dialogs.Dialog;
import com.android.spotifyapp.utils.onClickHandler;
import com.android.spotifyapp.ui.listeners.ListenersInterface;

import java.util.Objects;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements HomeHorizontal.OnItemListener, MyPlaylistsAdapter.PlaylistListener, RecommendedAdapter.onItemListener, ListenersInterface {
    @Inject HomeHorizontal homeHorizontal;
    @Inject MyPlaylistsAdapter myPlaylistsAdapter;
    @Inject RecommendedAdapter recommendedAdapter;

    @Inject HomeViewModel homeViewModel;
    @Inject MyPlaylistViewModel myPlaylistViewModel;

    @Inject SliderAdapter sliderAdapter;

    @Inject Dialog dialog;

    @Inject HomeFragmentBinding binding;
    private NavController navigation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        //Dagger
        DaggerHomeComponent.builder()
                .viewModelsModule(new ViewModelsModule(this))
                .dialogModule(new DialogModule(this))
                .contextModule(new ContextModule(getActivity()))
                .fragmentBindingModule(new FragmentBindingModule(inflater, this))
                .build().injectFragment(this);
        //Recently played list
        binding.setListeners(this);

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

        binding.invalidateAll();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigation = Navigation.findNavController(view);

    }

    //start Player
    @Override
    public void onClick(String title) {
        CurrentSongState currentSongState = CurrentSongState.getInstance();
        currentSongState.setTitle(title);
        Player.startPlayer(getActivity());
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
    public void onClick(String name, int followers, String image_url, String id) {
        onClickHandler.artistClickNavigate(name, followers, image_url, id, navigation);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ONTEST", "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ONTEST", "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ONTEST", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("ONTEST", "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ONTEST", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ONTEST", "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ONTEST", "onDetach: ");
    }

    @Override
    public void recentlyPlayedViewAllClick(View view) {
        switch(view.getId()) {
            case R.id.view_rencently_played:
                navigation.navigate(R.id.action_home_to_recentlyPlayedFragment);
                break;
            case R.id.view_my_playlists:
                navigation.navigate(R.id.action_home_to_myPlaylistFragment);
                break;
            case R.id.view_recommended_artists:
                navigation.navigate(R.id.action_home_to_recommendedArtistsFragment);
                break;
        }

    }
}

