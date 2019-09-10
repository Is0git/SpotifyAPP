package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.byId.ArtistTopTracks;
import com.android.spotifyapp.data.network.model.byId.ArtistsAlbum;
import com.android.spotifyapp.data.network.model.byId.RelatedArtists;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArtistService {
    @GET("v1/artists/{id}/top-tracks")
    Observable<ArtistTopTracks> getArtistTopTracks(@Header("Authorization") String access_token, @Path("id") String id, @Query("country") String country);
    @GET("v1/artists/{id}/related-artists")
    Observable<RelatedArtists> getRelatedArtistsObservable(@Header("Authorization") String access_token, @Path("id") String id);
    @GET("v1/artists/{id}/albums")
    Observable<ArtistsAlbum> getArtistAlbum(@Header("Authorization") String access_token, @Path("id") String id);
}
