package com.android.spotifyapp.data.network.dataSources.dataFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.android.spotifyapp.data.network.dataSources.RecentlyPlayedDataSource;
import com.android.spotifyapp.data.network.services.RecentlyPlayedService;
import com.android.spotifyapp.ui.States.NetworkState;

public class RecentlyPlayedDataSourceFactory extends DataSource.Factory {
    private RecentlyPlayedDataSource recentlyPlayedDataSource;
    private RecentlyPlayedService recentlyPlayedService;
    private String access_token;
    private MutableLiveData<NetworkState> state;

    public RecentlyPlayedDataSourceFactory(RecentlyPlayedService recentlyPlayedService, String access_token, MutableLiveData<NetworkState> state) {
        this.recentlyPlayedService = recentlyPlayedService;
        this.access_token = access_token;
        this.state = state;
    }

    @NonNull
    @Override
    public DataSource create() {
        recentlyPlayedDataSource = new RecentlyPlayedDataSource(recentlyPlayedService, access_token, state);
        return recentlyPlayedDataSource;
    }
}
