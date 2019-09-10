package com.android.spotifyapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.App;
import com.android.spotifyapp.R;
import com.android.spotifyapp.data.ViewModels.HomeViewModel;
import com.android.spotifyapp.data.ViewModels.MyPlaylistViewModel;
import com.android.spotifyapp.data.network.model.Post.MyPlaylistPost;
import com.android.spotifyapp.data.network.model.Recommendations;
import com.android.spotifyapp.di.components.DaggerHomeComponent;
import com.android.spotifyapp.di.components.HomeComponent;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.DialogModule;
import com.android.spotifyapp.di.modules.RecyclerViewModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.qualifiers.MyPlaylistListQualifier;
import com.android.spotifyapp.di.qualifiers.RecentlyPlayedQualifier;
import com.android.spotifyapp.di.qualifiers.RecommendedListQualifier;
import com.android.spotifyapp.ui.GlobalState.CurrentSongState;
import com.android.spotifyapp.ui.activities.BaseActivity;
import com.android.spotifyapp.ui.adapters.Home.HomeHorizontal;
import com.android.spotifyapp.ui.adapters.Home.MyPlaylistsAdapter;
import com.android.spotifyapp.ui.adapters.Home.RecommendedAdapter;
import com.android.spotifyapp.ui.adapters.Home.SliderAdapter;
import com.android.spotifyapp.utils.CheckProgressBar;
import com.android.spotifyapp.utils.Dialogs.Dialog;
import com.android.spotifyapp.utils.SharedPreferencesUtil;
import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;



public class HomeFragment extends Fragment implements HomeHorizontal.OnItemListener, MyPlaylistsAdapter.PlaylistListener, RecommendedAdapter.onItemListener {
    @Inject @RecentlyPlayedQualifier RecyclerView recyclerView;
    @Inject @MyPlaylistListQualifier RecyclerView MyPlaylistRecyclerView;
    @Inject @RecommendedListQualifier RecyclerView recommendedRecyclerView;

    @Inject HomeHorizontal homeHorizontal;
    @Inject MyPlaylistsAdapter myPlaylistsAdapter;
    @Inject RecommendedAdapter recommendedAdapter;

    @Inject HomeViewModel homeViewModel;
    @Inject MyPlaylistViewModel myPlaylistViewModel;

    @Inject SliderAdapter sliderAdapter;

    @Inject Dialog dialog;

    @BindView(R.id.progressBarRecommended) ProgressBar progressBar_recommended;
    @BindView(R.id.progressBarRecentlyPlayed) ProgressBar progressBar_recently;
    @BindView(R.id.progressBarPlaylists) ProgressBar progressBar_playlists;
    @BindView(R.id.progressBarHomeSlider) ProgressBar progressBar_slider;
    @BindView(R.id.recently_played_items) TextView recently_played_items;
    @BindView(R.id.myPlaylist_items) TextView my_playlist_items;
    @BindView(R.id.recommended_items) TextView recommended_items;
    @BindView(R.id.slider) SliderView sliderView;

    private String id;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        //Dagger
        HomeComponent component = DaggerHomeComponent.builder()
        .recyclerViewModule(new RecyclerViewModule(view))
                .appComponent(((App) Objects.requireNonNull(getActivity()).getApplicationContext()).getAppComponent())
                .viewModelsModule(new ViewModelsModule(this))
                .dialogModule(new DialogModule(this))
                .contextModule(new ContextModule(getActivity()))
                .build();
        component.injectFragment(this);

        //Recently played list
        //getViewLifecycleOwner() is better than 'this' because then observer is removed after fragment view is destroyed
        recyclerView.setAdapter(homeHorizontal);
        homeHorizontal.setListener(this);
        homeViewModel.getRecentlyPlayedLiveData().observe(getViewLifecycleOwner(), recentlyPlayed -> {
            recently_played_items.setText(getString(R.string.items, recentlyPlayed.getMitems().size()));
            homeHorizontal.UpdateData(recentlyPlayed);
            CheckProgressBar.checkprogressBar(recyclerView, progressBar_recently);
        });

        //MyPlaylist List
        MyPlaylistRecyclerView.setAdapter(myPlaylistsAdapter);
        myPlaylistsAdapter.setPlaylistListener(this);
        myPlaylistViewModel.getMyPlaylistLiveData("Bearer " + SharedPreferencesUtil.getPreferences(shared_preferences_auth, getActivity().getApplicationContext()).getString(access_token, null))
                .observe(getViewLifecycleOwner(), myPlaylist -> {
            my_playlist_items.setText(getString(R.string.items, myPlaylist.getMitems().size()));
            myPlaylistsAdapter.UpdateList(myPlaylist);
            CheckProgressBar.checkprogressBar(MyPlaylistRecyclerView, progressBar_playlists);
        });

        //Recommended List
        recommendedRecyclerView.setAdapter(recommendedAdapter);
        recommendedAdapter.setOnItemListener(this);
        homeViewModel.getRecommendations().observe(getViewLifecycleOwner(), recommendations -> {
            recommended_items.setText(getString(R.string.items, recommendations.getMtracks().size()));
            recommendedAdapter.UpdateData(recommendations);
            CheckProgressBar.checkprogressBar(recommendedRecyclerView, progressBar_recommended);
         });

        //Slider
        sliderView.setSliderAdapter(sliderAdapter);
        homeViewModel.getUserTopTracksMutableLiveData(5).observe(getViewLifecycleOwner(), userTopTracks -> {
            sliderAdapter.UpdateData(userTopTracks);
            CheckProgressBar.checkSliderProgress(sliderView, progressBar_slider);
        });

        registerForContextMenu(MyPlaylistRecyclerView);
        return view;
    }

    //Recently played songs on click
    @Override
    public void onClick(int position, String title) {
        CurrentSongState currentSongState = CurrentSongState.getInstance();
        currentSongState.setTitle(title);
        ((BaseActivity) Objects.requireNonNull(getActivity())).startPlayer();
    }

    @Override
    public void onPlaylistItemClick(String id) {
        this.id = id;
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
                if(id != null) {
                    myPlaylistViewModel.deletePlaylist(id);
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
        params.putInt("position", position);
        params.putSerializable("recommendations", recommendations);
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

