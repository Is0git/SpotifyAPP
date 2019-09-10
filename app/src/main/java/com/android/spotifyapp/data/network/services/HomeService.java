package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.Artist;
import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.data.network.model.Recommendations;
import com.android.spotifyapp.data.network.model.UserTopTracks;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HomeService {
    @GET("v1/me/player/recently-played")
    Observable<RecentlyPlayed>getRecentlyPlayed(@Header("Authorization") String access_token);
    @GET("v1/recommendations")
    Observable<Recommendations>getRecommendations(@Header("Authorization") String access_token, @Query("seed_genres") String genre);
    @GET("v1/artists/{id}")
    Observable<Artist>getArtist(@Header("Authorization") String access_token, @Path("id") String id);
    @GET("v1/me/top/{type}")
    Observable<UserTopTracks> getUserTopTracks(@Header("Authorization") String access_token, @Path("type") String type, @Query("limit") int limit);

}
