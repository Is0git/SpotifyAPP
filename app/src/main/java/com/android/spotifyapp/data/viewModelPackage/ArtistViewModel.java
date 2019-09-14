package com.android.spotifyapp.data.viewModelPackage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.byId.ArtistTopSongs;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.network.model.byId.RelatedArtists;
import com.android.spotifyapp.data.repositories.ArtistRepository;

public class ArtistViewModel extends AndroidViewModel {
    private ArtistRepository artistRepository;
    private LiveData<Artistsalbum> artistsAlbumLiveData;
    private LiveData<ArtistTopSongs> artistTopTracksLiveData;
    private LiveData<RelatedArtists> relatedArtistsLiveData;

    public ArtistViewModel(@NonNull Application application) {
        super(application);
        artistRepository = ArtistRepository.getInstance(application);
    }
    public LiveData<Artistsalbum> getArtistsAlbum(String id) {
        artistsAlbumLiveData = artistRepository.getAlbums(id);
        return artistsAlbumLiveData;
    }

    public LiveData<ArtistTopSongs> getTopTracks(String id, String country) {
        artistTopTracksLiveData = artistRepository.getTopTracks(id, country);;
        return artistTopTracksLiveData;
    }

    public LiveData<RelatedArtists> getRelatedArtists(String id) {
        relatedArtistsLiveData = artistRepository.getRelatedArtistsLiveData(id);
        return relatedArtistsLiveData;
    }

    public LiveData<Artistsalbum> getArtistsAlbumLiveData() {
        return artistsAlbumLiveData;
    }

    public LiveData<ArtistTopSongs> getArtistTopTracksLiveData() {
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
