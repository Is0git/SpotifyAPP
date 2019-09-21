package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.MyPlaylist;
import com.android.spotifyapp.data.network.model.Post.MyPlaylistPost;
import com.android.spotifyapp.data.network.model.byId.PlaylistTracks;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;


public interface MyPlaylistService {
    @GET("v1/me/playlists")
    @Streaming
    Observable<MyPlaylist> getMyPlaylist(@Header("Authorization") String access_token);

    @GET("v1/me/playlists")
    Call<MyPlaylist> getPagedPlaylist(@Header("Authorization") String access_token, @Query("limit") int pagesize, @Query("offset") int offset);
    @DELETE("v1/playlists/{playlist_id}/followers")
    Call<Void> deletePlaylist(@Header("Authorization") String access_token, @Path("playlist_id") String playlist_id);
    @POST("v1/users/{user_id}/playlists")
    Call<Void> addPlaylist(@Header("Authorization") String access_token, @Body MyPlaylistPost myPlaylistPost, @Path("user_id") String id);
    @GET("v1/playlists/{playlist_id}/tracks")
    Call<PlaylistTracks> getPlaylistTracks(@Header("Authorization") String access_token, @Path("playlist_id") String id, @Query("limit") int limit, @Query("offset") int offset);
}
