package com.android.spotifyapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserTopTracks {
    @SerializedName("items")
   private List<Items>items;
    private int total;
    private int limit;
    private String previous;
    private String next;

    public List<Items> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    public int getLimit() {
        return limit;
    }

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public static class Items {
        @SerializedName("artists")
        private List<Artist>artist;
        @SerializedName("album")
        private Album album;

        public Album getAlbum() {
            return album;
        }

        private int duration_ms;
        private boolean explicit;
        private String id;
        private String name;
        private int popularity;
        private String preview_url;
        private int track_number;
        private String type;

        public List<Artist> getArtist() {
            return artist;
        }

        public int getDuration_ms() {
            return duration_ms;
        }

        public boolean isExplicit() {
            return explicit;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPopularity() {
            return popularity;
        }

        public String getPreview_url() {
            return preview_url;
        }

        public int getTrack_number() {
            return track_number;
        }

        public String getType() {
            return type;
        }
    }
    public static class Artist {
        private String id;
        private String name;
        private String type;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
    public static class Album {
        @SerializedName("images")
        List<Images>images;

        public List<Images> getImages() {
            return images;
        }
    }
    public static class Images {
        private String url;

        public String getUrl() {
            return url;
        }
    }
}
