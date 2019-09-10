package com.android.spotifyapp.utils.Contracts;

public class YoutubeAPIContract {
    private static final String API_KEY = "AIzaSyDfRnOKelQtpbnGtxLyV77o_05aoMmRIBU";
    private static final String BASE_URL = "https://www.googleapis.com/";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public YoutubeAPIContract() {
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
