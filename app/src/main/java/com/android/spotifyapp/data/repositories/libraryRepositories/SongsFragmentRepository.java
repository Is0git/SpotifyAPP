package com.android.spotifyapp.data.repositories.libraryRepositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.model.libmodel.Tracks;
import com.android.spotifyapp.data.network.services.LibraryService;
import com.android.spotifyapp.di.components.DaggerSongFragmentRepositoriesComponent;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;
import com.android.spotifyapp.utils.SharedPreferencesUtil;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;
import static com.android.spotifyapp.utils.TAGS.TAG4;

public class SongsFragmentRepository {
    private static SongsFragmentRepository songsFragmentRepository;
    private MutableLiveData<Tracks> tracksMutableLiveData;
    @Inject @RetrofitQualifier
    Retrofit retrofit;
    private LibraryService libraryService;
    private String token;
    private SongsFragmentRepository(Application application) {
        DaggerSongFragmentRepositoriesComponent.builder()
                .appComponent(((App) application.getApplicationContext()).getAppComponent())
                .build().inject(this);
        tracksMutableLiveData = new MutableLiveData<>();
        token = SharedPreferencesUtil.getPreferences(shared_preferences_auth, application).getString(access_token, null);
        Log.d(TAG4, "SongsFragmentRepository: " + token);
        libraryService = retrofit.create(LibraryService.class);
    }

    public static SongsFragmentRepository getInstance(Application application) {
        if(songsFragmentRepository == null) {
            songsFragmentRepository = new SongsFragmentRepository(application);
        }
        return songsFragmentRepository;
    }

    public LiveData<Tracks> getTracks(int limit) {
        Call<Tracks> call = libraryService.getTracks("Bearer " + token, limit);
        call.enqueue(new Callback<Tracks>() {
            @Override
            public void onResponse(Call<Tracks> call, Response<Tracks> response) {
                tracksMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Tracks> call, Throwable t) {
                Log.d(TAG4, "onFailure: " + t.getMessage());
            }
        });
        return tracksMutableLiveData;
    }

}
