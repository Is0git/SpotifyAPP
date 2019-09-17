package com.android.spotifyapp.data.network.dataSources;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.android.spotifyapp.data.network.services.ArtistService;
import com.android.spotifyapp.utils.SharedPreferencesUtil;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;

public class AlbumDataSourceFactory extends DataSource.Factory{
    MutableLiveData<AlbumDataSource> albumDataSourceMutableLiveData;
    private AlbumDataSource albumDataSource;
    ArtistService artistService;
    Application application;
    String id;
    public AlbumDataSourceFactory(Application application, ArtistService artistService, String id) {
        albumDataSourceMutableLiveData = new MutableLiveData<>();
        this.artistService = artistService;
        this.application = application;
        this.id = id;
    }

    @NonNull
    @Override
    public DataSource create() {
        albumDataSource = new AlbumDataSource(artistService, SharedPreferencesUtil.getPreferences(shared_preferences_auth, application).getString(access_token, "TEST"), id);
        //application.getApplicationContext().getSharedPreferences("shared_preferences_auth", Context.MODE_PRIVATE).getString("access_token", "TEST")
        albumDataSourceMutableLiveData.postValue(albumDataSource);
        return albumDataSource;
    }

    public MutableLiveData<AlbumDataSource> getAlbumDataSourceMutableLiveData() {
        return albumDataSourceMutableLiveData;
    }
}
