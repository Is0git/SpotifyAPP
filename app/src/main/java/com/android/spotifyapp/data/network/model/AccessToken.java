package com.android.spotifyapp.data.network.model;

public class AccessToken {
    private String token_type;
    private String access_token;
    private String expires_in;

    public String getExpires_in() {
        return expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getAccess_token() {
        return access_token;
    }
}

