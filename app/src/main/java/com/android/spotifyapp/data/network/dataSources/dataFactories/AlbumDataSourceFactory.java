package com.android.spotifyapp.data.network.dataSources.dataFactories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.android.spotifyapp.data.network.dataSources.AlbumsDataSource;
import com.android.spotifyapp.data.network.services.ArtistService;
import com.android.spotifyapp.ui.States.NetworkState;

public class AlbumDataSourceFactory extends DataSource.Factory {
    private ArtistService artistService;
    private String token;
    private String id;
    private MutableLiveData<NetworkState> state;
    private AlbumsDataSource albumsDataSource;

    public AlbumDataSourceFactory(ArtistService artistService, String token, String id, MutableLiveData<NetworkState> state) {
        this.artistService = artistService;
        this.token = token;
        this.id = id;
        this.state = state;
    }

    @NonNull
    @Override
    public DataSource create() {
        albumsDataSource = new AlbumsDataSource(artistService, token, id, state);
        return albumsDataSource;
    }

}
