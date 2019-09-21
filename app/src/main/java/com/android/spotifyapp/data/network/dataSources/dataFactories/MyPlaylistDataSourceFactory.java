package com.android.spotifyapp.data.network.dataSources.dataFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.android.spotifyapp.data.network.dataSources.PlaylistDataSource;
import com.android.spotifyapp.data.network.services.MyPlaylistService;
import com.android.spotifyapp.ui.States.NetworkState;

public class MyPlaylistDataSourceFactory extends DataSource.Factory {

    private PlaylistDataSource playlistDataSource;
    private MyPlaylistService myPlaylistService;
    private MutableLiveData<NetworkState> state;
    private String access_token;

    public MyPlaylistDataSourceFactory(MyPlaylistService myPlaylistService, MutableLiveData<NetworkState> state, String access_token) {
        this.myPlaylistService = myPlaylistService;
        this.state = state;
        this.access_token = access_token;

    }

    @NonNull
    @Override
    public DataSource create() {
        playlistDataSource = new PlaylistDataSource(myPlaylistService, access_token , state);
        return playlistDataSource;
    }


    }

