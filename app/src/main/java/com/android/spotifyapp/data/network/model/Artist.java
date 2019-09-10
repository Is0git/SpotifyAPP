package com.android.spotifyapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {
    @SerializedName("followers")
    private Followers followers;
    @SerializedName("genres")
    private List<String> genres;
    @SerializedName("images")
    private List<Images> images;
    private String name;
    private int popularity;

    public Followers getFollowers() {
        return followers;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<Images> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public int getPopularity() {
        return popularity;
    }

    public static class Followers {
        private int total;

        public int getTotal() {
            return total;
        }
    }
    public static class Images {
        private String url;

        public String getUrl() {
            return url;
        }
    }
}
