package com.android.spotifyapp.data.viewModelPackage.libraryViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.libmodel.Albums;
import com.android.spotifyapp.data.repositories.libraryRepositories.AlbumRepository;

public class AlbumsLibraryViewModel extends AndroidViewModel {
    private LiveData<Albums> albumsLiveData;
    private AlbumRepository albumRepository;
    public AlbumsLibraryViewModel(@NonNull Application application) {
        super(application);
        albumRepository = AlbumRepository.getInstance(application);
    }
    public LiveData<Albums> getAlbums(int limit) {
        albumsLiveData = albumRepository.getAlbums(limit);
        return albumsLiveData;
    }
    public LiveData<Albums> getAlbumsLiveData() {
        return albumsLiveData;
    }
}
