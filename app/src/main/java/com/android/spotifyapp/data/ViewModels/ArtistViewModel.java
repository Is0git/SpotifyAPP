package com.android.spotifyapp.data.ViewModels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.byId.ArtistTopTracks;
import com.android.spotifyapp.data.network.model.byId.ArtistsAlbum;
import com.android.spotifyapp.data.network.model.byId.RelatedArtists;
import com.android.spotifyapp.data.repositories.ArtistRepository;

public class ArtistViewModel extends AndroidViewModel {
    private ArtistRepository artistRepository;

    public ArtistViewModel(@NonNull Application application) {
        super(application);
        artistRepository = ArtistRepository.getInstance(application);
    }
    public LiveData<ArtistsAlbum> getArtistsAlbum(String id) {
        return artistRepository.getAlbums(id);
    }

    public LiveData<ArtistTopTracks> getTopTracks(String id, String country) {
        return artistRepository.getTopTracks(id, country);
    }

    public LiveData<RelatedArtists> getRelatedArtists(String id) {
        return artistRepository.getRelatedArtistsLiveData(id);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        artistRepository.getDisposable().clear();
    }
}
