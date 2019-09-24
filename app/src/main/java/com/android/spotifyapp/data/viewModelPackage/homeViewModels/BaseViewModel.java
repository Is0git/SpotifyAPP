package com.android.spotifyapp.data.viewModelPackage.homeViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.User;
import com.android.spotifyapp.data.repositories.homeRepositories.BaseRepository;

public class BaseViewModel extends AndroidViewModel {
    private BaseRepository baseRepository;
    private LiveData<User> userLiveData;
    public BaseViewModel(@NonNull Application application) {
        super(application);
        baseRepository = BaseRepository.getInstance(application);

    }
    public LiveData<User> getUser() {
        userLiveData = baseRepository.getUser();
        return userLiveData;
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        baseRepository.getDisposables().clear();
    }
}
