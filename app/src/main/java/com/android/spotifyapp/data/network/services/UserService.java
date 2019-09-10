package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserService {

    @GET("v1/me")
    Observable<User> getUser(@Header("Authorization") String access_token);
}
