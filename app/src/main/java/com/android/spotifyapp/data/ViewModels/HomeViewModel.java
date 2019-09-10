package com.android.spotifyapp.data.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.data.network.model.Recommendations;
import com.android.spotifyapp.data.network.model.UserTopTracks;
import com.android.spotifyapp.data.repositories.HomeRepository;

public class HomeViewModel extends AndroidViewModel {
    private HomeRepository homeRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository = HomeRepository.getInstance(application);
    }

    public LiveData<RecentlyPlayed> getRecentlyPlayedLiveData() {
        return homeRepository.getRecentlyPlayed();

    }

    public LiveData<Recommendations>getRecommendations() {
        return homeRepository.getRecommendations();

    }

    public LiveData<UserTopTracks>getUserTopTracksMutableLiveData(int limit) {
        return homeRepository.getUserTopTracks(limit);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        homeRepository.getDisposables().clear();
    }
}
