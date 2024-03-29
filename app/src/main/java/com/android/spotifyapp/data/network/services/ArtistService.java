package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.byId.ArtistTopSongs;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.network.model.byId.RelatedArtists;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArtistService {
    @GET("v1/artists/{id}/top-tracks")
    Observable<ArtistTopSongs> getArtistTopTracks(@Header("Authorization") String access_token, @Path("id") String id, @Query("country") String country);
    @GET("v1/artists/{id}/related-artists")
    Observable<RelatedArtists> getRelatedArtistsObservable(@Header("Authorization") String access_token, @Path("id") String id);
    @GET("v1/artists/{id}/albums")
    Call<Artistsalbum> getArtistAlbums(@Header("Authorization") String access_token, @Path("id") String id, @Query("limit") int limit, @Query("offset") int offset);
    @GET("v1/artists/{id}/albums")
    Observable<Artistsalbum> getArtistAlbum(@Header("Authorization") String access_token, @Path("id") String id, @Query("limit") int limit, @Query("offset") int offset);
}
