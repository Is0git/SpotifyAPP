package com.android.spotifyapp.data.network.dataSources.dataFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.android.spotifyapp.data.network.dataSources.PlaylistTracksDataSource;
import com.android.spotifyapp.data.network.services.MyPlaylistService;
import com.android.spotifyapp.ui.States.NetworkState;

public class PlaylistTracksDataSourceFactory extends DataSource.Factory {
    private String id;
    private String access_token;
    private MyPlaylistService myPlaylistService;
    private MutableLiveData<NetworkState> state;
    private PlaylistTracksDataSource playlistTracksDataSource;

    public PlaylistTracksDataSourceFactory(String id, String access_token, MyPlaylistService myPlaylistService, MutableLiveData<NetworkState> state) {
        this.id = id;
        this.access_token = access_token;
        this.myPlaylistService = myPlaylistService;
        this.state = state;
    }

    @NonNull
    @Override
    public DataSource create() {
        playlistTracksDataSource = new PlaylistTracksDataSource(access_token, id, myPlaylistService, state);
        return playlistTracksDataSource;
    }
}
