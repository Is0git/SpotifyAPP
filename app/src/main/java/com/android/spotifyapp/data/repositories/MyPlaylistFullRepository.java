package com.android.spotifyapp.data.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.dataSources.dataFactories.MyPlaylistDataSourceFactory;
import com.android.spotifyapp.data.network.services.MyPlaylistService;
import com.android.spotifyapp.di.components.DaggerMyPlaylistFullComponent;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;
import com.android.spotifyapp.ui.States.NetworkState;
import com.android.spotifyapp.utils.SharedPreferencesUtil;

import javax.inject.Inject;

import retrofit2.Retrofit;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;

public class MyPlaylistFullRepository {
    private static MyPlaylistFullRepository myPlaylistFullRepository;
    @Inject
    @RetrofitQualifier
    Retrofit retrofit;
    private MyPlaylistService myPlaylistService;
    private MutableLiveData<NetworkState> state;
    private MyPlaylistDataSourceFactory myPlaylistDataSourceFactory;
    private String accessToken;

    private MyPlaylistFullRepository(Application application) {
        DaggerMyPlaylistFullComponent.builder()
                .appComponent(((App) application.getApplicationContext()).getAppComponent())
                .build().inject(this);
        state = new MutableLiveData<>();
        myPlaylistService = retrofit.create(MyPlaylistService.class);
        accessToken = SharedPreferencesUtil.getPreferences(shared_preferences_auth, application).getString(access_token, "TEST");
        myPlaylistDataSourceFactory = new MyPlaylistDataSourceFactory(myPlaylistService, state, accessToken);
    }

    public static MyPlaylistFullRepository getInstance(Application application) {
        if(myPlaylistFullRepository == null) {
            myPlaylistFullRepository = new MyPlaylistFullRepository(application);
        }
        return myPlaylistFullRepository;
    }

    public MutableLiveData<NetworkState> getState() {
        return state;
    }

    public MyPlaylistDataSourceFactory getMyPlaylistDataSourceFactory() {
        return myPlaylistDataSourceFactory;
    }


}
