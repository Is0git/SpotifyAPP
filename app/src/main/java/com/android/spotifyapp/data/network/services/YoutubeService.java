package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.YoutubeVideos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YoutubeService {
//?part=id&maxResults=2&q=game%20of%20thrones&type=video&key=AIzaSyApgYVsXPT2eRnaUdxl2F7fEHIZkaakNQc
    @GET("youtube/v3/search")
    Observable<YoutubeVideos> getVideos(@Query("part") String part, @Query("maxResults") int maxResults, @Query("q") String q, @Query("type") String type, @Query("key") String key);
}
