package com.android.spotifyapp.data.network.services;

import com.android.spotifyapp.data.network.model.AccessToken;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {

        @Headers("Accept: application/json")
        @POST("https://accounts.spotify.com/api/token")
        @FormUrlEncoded
        Observable<AccessToken> getAccessToken(
                @Field("client_id") String id,
                @Field("client_secret") String secret,
                @Field("code") String code,
                @Field("redirect_uri") String uri,
                @Field("grant_type") String auth_code
        );

}
