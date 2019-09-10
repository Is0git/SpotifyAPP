package com.android.spotifyapp.data.network.model.byId;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RelatedArtists {
    private List<Artists> artists;

    public List<Artists> getArtists() {
        return artists;
    }

    public static class Artists {
        @SerializedName("followers")
        private Followers followers;
        private List<String>genres;
        private String id;
        @SerializedName("images")
        private List<Images>imnages;
        private String name;
        private int popularity;

        public Followers getFollowers() {
            return followers;
        }

        public List<String> getGenres() {
            return genres;
        }

        public String getId() {
            return id;
        }

        public List<Images> getImnages() {
            return imnages;
        }

        public String getName() {
            return name;
        }

        public int getPopularity() {
            return popularity;
        }
    }

    public static class Followers {
        private int total;

        public int getTotal() {
            return total;
        }
    }

    public static class Images {
        private Integer height;
        private String url;
        private Integer width;

        public Integer getHeight() {
            return height;
        }

        public String getUrl() {
            return url;
        }

        public Integer getWidth() {
            return width;
        }
    }
}
