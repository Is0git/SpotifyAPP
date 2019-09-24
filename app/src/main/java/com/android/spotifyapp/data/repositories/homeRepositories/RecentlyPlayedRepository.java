package com.android.spotifyapp.data.repositories.homeRepositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.dataSources.dataFactories.RecentlyPlayedDataSourceFactory;
import com.android.spotifyapp.data.network.services.RecentlyPlayedService;
import com.android.spotifyapp.di.components.DaggerRecentlyPlayedRepositoryComponent;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;
import com.android.spotifyapp.ui.States.NetworkState;
import com.android.spotifyapp.utils.SharedPreferencesUtil;

import javax.inject.Inject;

import retrofit2.Retrofit;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;

public class RecentlyPlayedRepository {
    private static RecentlyPlayedRepository recentlyPlayedRepository;
    private RecentlyPlayedService recentlyPlayedService;
    private MutableLiveData<NetworkState> state;

    @Inject
    @RetrofitQualifier
    Retrofit retrofit;
    private String token;
    private RecentlyPlayedRepository(Application application) {
        DaggerRecentlyPlayedRepositoryComponent.builder()
                .appComponent(((App) application.getApplicationContext()).getAppComponent())
                .build().inject(this);
        recentlyPlayedService = retrofit.create(RecentlyPlayedService.class);
        state = new MutableLiveData<>();
        token = SharedPreferencesUtil.getPreferences(shared_preferences_auth, application.getApplicationContext()).getString(access_token, "no token");
    }

    public static RecentlyPlayedRepository getInstance(Application application) {
        if (recentlyPlayedRepository == null) {
            recentlyPlayedRepository = new RecentlyPlayedRepository(application);
        }
        return recentlyPlayedRepository;
    }

    public RecentlyPlayedDataSourceFactory recentlyPlayedDataSourceFactory() {
        return new RecentlyPlayedDataSourceFactory(recentlyPlayedService, token, state);
    }


    public MutableLiveData<NetworkState> getState() {
        return state;
    }

}
