package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.RecentlyPlayed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RecentlyPlayedService {

    @GET("v1/me/player/recently-played")
    Call<RecentlyPlayed> getRecentlyPlayed(@Query("limit") int limit, @Query("after") int after, @Header("Authorization") String access_token);
}
