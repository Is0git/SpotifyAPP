package com.android.spotifyapp.data.viewModelPackage.libraryViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
        import androidx.lifecycle.LiveData;

        import com.android.spotifyapp.data.network.model.libmodel.Tracks;
import com.android.spotifyapp.data.repositories.libraryRepositories.SongsFragmentRepository;

public class SongsLibraryViewModel extends AndroidViewModel {
    private LiveData<Tracks> tracksLiveData;
    private SongsFragmentRepository songsFragmentRepository;

    public LiveData<Tracks> getTracksLiveData() {
        return tracksLiveData;
    }

    public LiveData<Tracks> getTracksLiveData(int limit) {
        tracksLiveData = songsFragmentRepository.getTracks(limit);
        return tracksLiveData;
    }

    public SongsLibraryViewModel(@NonNull Application application) {
        super(application);
        songsFragmentRepository = SongsFragmentRepository.getInstance(application);

    }
}
