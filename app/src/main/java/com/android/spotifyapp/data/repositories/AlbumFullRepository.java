package com.android.spotifyapp.data.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.dataSources.dataFactories.AlbumDataSourceFactory;
import com.android.spotifyapp.data.network.services.ArtistService;
import com.android.spotifyapp.di.components.DaggerAlbumFullRepositoryComponent;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;
import com.android.spotifyapp.ui.States.NetworkState;
import com.android.spotifyapp.utils.Contracts.SharedPreferencesContract;
import com.android.spotifyapp.utils.SharedPreferencesUtil;

import javax.inject.Inject;

import retrofit2.Retrofit;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;

public class AlbumFullRepository {
    private static AlbumFullRepository albumFullRepository;
    private ArtistService artistService;
    @Inject
    @RetrofitQualifier
    Retrofit retrofit;
    private MutableLiveData<NetworkState> state;
    private String access_token;
    private AlbumDataSourceFactory albumDataSourceFactory;
    private AlbumFullRepository(Application application) {
        DaggerAlbumFullRepositoryComponent.builder()
                .appComponent(((App) application.getApplicationContext()).getAppComponent())
                .build().inject(this);
        artistService = retrofit.create(ArtistService.class);
        access_token = SharedPreferencesUtil.getPreferences(shared_preferences_auth, application).getString(SharedPreferencesContract.access_token, "TEST");
        state = new MutableLiveData<>();
    }

    public static AlbumFullRepository getInstance(Application application) {

        if (albumFullRepository == null) {
            albumFullRepository = new AlbumFullRepository(application);
        }
        return albumFullRepository;
    }
    public void init(String id) {
        albumDataSourceFactory = new AlbumDataSourceFactory(artistService, access_token, id, state);
    }

    public AlbumDataSourceFactory getAlbumDataSourceFactory() {
        return albumDataSourceFactory;
    }

    public MutableLiveData<NetworkState> getState() {
        return state;
    }
}
