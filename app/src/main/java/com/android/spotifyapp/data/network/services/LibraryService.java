package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.libmodel.Albums;
import com.android.spotifyapp.data.network.model.libmodel.Tracks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface LibraryService {
    @GET("v1/me/tracks")
    Call<Tracks> getTracks(@Header("Authorization") String access_token, @Query("limit") int limit);
    @GET("v1/me/albums")
    Call<Albums> getAlbums(@Header("Authorization") String access_token, @Query("limit") int limit);
}
