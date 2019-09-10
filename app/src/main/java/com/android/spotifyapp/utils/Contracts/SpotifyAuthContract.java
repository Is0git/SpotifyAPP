package com.android.spotifyapp.utils.Contracts;

public class SpotifyAuthContract {
    final public static String CLIENT_ID = "c9e75133ed174d49ad53b611041aad80";
    final public static String CLIENT_SECRET = "d9e4a948ab5243a691676a2d8fc83a2b";
    final public static String AUTH_CODE = "authorization_code";
    final private static String SCOPES = "playlist-read-private " + "user-read-private " + "user-follow-read " + "user-read-recently-played " + "user-library-read " + "user-read-playback-state "
            + "user-library-modify " + "user-read-currently-playing " + "user-modify-playback-state " + "user-follow-modify " + "playlist-read-collaborative " + "streaming " + "playlist-modify-private "
            + "playlist-modify-public " + "user-read-email " + "playlist-read-private " + "user-top-read " + "app-remote-control";
    final public static String URI = "https://accounts.spotify.com/authorize?response_type=code&client_id=c9e75133ed174d49ad53b611041aad80&scope=" + SCOPES + "&redirect_uri=";
    final public static String REDIRECT_URL = "spotifyapp://callback";
    final public static String POST_BASE_URL = "https://accounts.spotify.com/api/token/";

}
