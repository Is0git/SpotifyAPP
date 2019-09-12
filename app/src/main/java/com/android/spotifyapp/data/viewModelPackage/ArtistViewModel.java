package com.android.spotifyapp.data.viewModelPackage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.byId.ArtistTopTracks;
import com.android.spotifyapp.data.network.model.byId.ArtistsAlbum;
import com.android.spotifyapp.data.network.model.byId.RelatedArtists;
import com.android.spotifyapp.data.repositories.ArtistRepository;

public class ArtistViewModel extends AndroidViewModel {
    private ArtistRepository artistRepository;
    private LiveData<ArtistsAlbum> artistsAlbumLiveData;
    private LiveData<ArtistTopTracks> artistTopTracksLiveData;
    private LiveData<RelatedArtists> relatedArtistsLiveData;

    public ArtistViewModel(@NonNull Application application) {
        super(application);
        artistRepository = ArtistRepository.getInstance(application);
    }
    public LiveData<ArtistsAlbum> getArtistsAlbum(String id) {
        artistsAlbumLiveData = artistRepository.getAlbums(id);
        return artistsAlbumLiveData;
    }

    public LiveData<ArtistTopTracks> getTopTracks(String id, String country) {
        artistTopTracksLiveData = artistRepository.getTopTracks(id, country);;
        return artistTopTracksLiveData;
    }

    public LiveData<RelatedArtists> getRelatedArtists(String id) {
        relatedArtistsLiveData = artistRepository.getRelatedArtistsLiveData(id);
        return relatedArtistsLiveData;
    }

    public LiveData<ArtistsAlbum> getArtistsAlbumLiveData() {
        return artistsAlbumLiveData;
    }

    public LiveData<ArtistTopTracks> getArtistTopTracksLiveData() {
        return artistTopTracksLiveData;
    }

    public LiveData<RelatedArtists> getRelatedArtistsLiveData() {
        return relatedArtistsLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        artistRepository.getDisposable().clear();
    }
}
