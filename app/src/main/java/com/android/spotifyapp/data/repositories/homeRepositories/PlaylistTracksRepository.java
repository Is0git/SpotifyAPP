package com.android.spotifyapp.data.repositories.homeRepositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.dataSources.dataFactories.PlaylistTracksDataSourceFactory;
import com.android.spotifyapp.data.network.services.MyPlaylistService;
import com.android.spotifyapp.di.components.DaggerMyPlaylistTracksComponent;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;
import com.android.spotifyapp.ui.States.NetworkState;
import com.android.spotifyapp.utils.Contracts.SharedPreferencesContract;
import com.android.spotifyapp.utils.SharedPreferencesUtil;

import javax.inject.Inject;

import retrofit2.Retrofit;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;

public class PlaylistTracksRepository {
    private static PlaylistTracksRepository playlistTracksRepository;
    @Inject
    @RetrofitQualifier
    Retrofit retrofit;
    private String token;
    private MyPlaylistService myPlaylistService;
    private MutableLiveData<NetworkState> state;

    private PlaylistTracksRepository(Application application) {
        DaggerMyPlaylistTracksComponent.builder()
                .appComponent(((App) application.getApplicationContext()).getAppComponent())
                .build().inject(this);
        myPlaylistService = retrofit.create(MyPlaylistService.class);
        token = SharedPreferencesUtil.getPreferences(shared_preferences_auth, application).getString(SharedPreferencesContract.access_token, "TEST");
        state = new MutableLiveData<>();

    }

    public static PlaylistTracksRepository getInstance(Application application) {
        if (playlistTracksRepository == null) {
            playlistTracksRepository = new PlaylistTracksRepository(application);
        }
        return playlistTracksRepository;
    }

    public PlaylistTracksDataSourceFactory playlistTracksDataSourceFactory(String id) {
        return new PlaylistTracksDataSourceFactory(id, token, myPlaylistService, state);
    }

    public MutableLiveData<NetworkState> getState() {
        return state;
    }
}
