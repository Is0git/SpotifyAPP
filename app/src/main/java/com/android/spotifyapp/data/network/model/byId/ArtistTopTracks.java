package com.android.spotifyapp.data.network.model.byId;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistTopTracks {
    private List<Tracks> tracks;

    public List<Tracks> getTracks() {
        return tracks;
    }

    public static class Tracks {
        @SerializedName("album")
        private Album album;
        @SerializedName("artists")
        private List<Artists> artists;
        private String name;

        public String getName() {
            return name;
        }

        private int duration_ms;
        private int eplicit;
        private String id;
        private Boolean is_local;
        private int popularity;

        public Album getAlbum() {
            return album;
        }

        public List<Artists> getArtists() {
            return artists;
        }

        public int getDuration_ms() {
            return duration_ms;
        }

        public int getEplicit() {
            return eplicit;
        }

        public String getId() {
            return id;
        }

        public Boolean getIs_local() {
            return is_local;
        }

        public int getPopularity() {
            return popularity;
        }
    }

    public static class Album {
        private String id;
        @SerializedName("images")
        private List<Images> images;
        private String name;
        private String release_date;
        private int total_tracks;

        public String getId() {
            return id;
        }

        public List<Images> getImages() {
            return images;
        }

        public String getName() {
            return name;
        }

        public String getRelease_date() {
            return release_date;
        }

        public int getTotal_tracks() {
            return total_tracks;
        }
    }

    public static class Artists {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
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
