package com.android.spotifyapp.data.viewModelPackage.homeViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.data.network.model.Recommendations;
import com.android.spotifyapp.data.network.model.UserTopTracks;
import com.android.spotifyapp.data.repositories.homeRepositories.HomeRepository;

public class HomeViewModel extends AndroidViewModel {
    private HomeRepository homeRepository;
    private LiveData<RecentlyPlayed> recentlyPlayedLiveData;
    private LiveData<Recommendations> recommendationsLiveData;
    private LiveData<UserTopTracks> userTopTracksLiveData;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository = HomeRepository.getInstance(application);
    }

    public LiveData<RecentlyPlayed> getRecentlyPlayedLiveData() {
        recentlyPlayedLiveData = homeRepository.getRecentlyPlayed();
        return recentlyPlayedLiveData;

    }

    public LiveData<Recommendations>getRecommendationsLiveData() {
        recommendationsLiveData = homeRepository.getRecommendations();
        return recommendationsLiveData;
    }

    public LiveData<RecentlyPlayed> getRecentlyPlayed() {
        return recentlyPlayedLiveData;
    }

    public LiveData<UserTopTracks>getUserTopTracksLiveData(int limit) {
        userTopTracksLiveData = homeRepository.getUserTopTracks(limit);
        return userTopTracksLiveData;

    }

    public LiveData<Recommendations> getRecommendations() {
        return recommendationsLiveData;
    }

    public LiveData<UserTopTracks> getUserTopTracks() {
        return userTopTracksLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        homeRepository.getDisposables().clear();
    }
}
