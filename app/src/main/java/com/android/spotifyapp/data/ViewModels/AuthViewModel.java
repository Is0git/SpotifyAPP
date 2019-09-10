package com.android.spotifyapp.data.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.AccessToken;
import com.android.spotifyapp.data.repositories.AuthRepository;

public class AuthViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance();

    }
    public LiveData<AccessToken>getTokenData(String code) {

        return authRepository.getAccess(code);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        authRepository.getDisposables().clear();
    }
}
