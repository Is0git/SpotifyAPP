package com.android.spotifyapp.data.network.model.byId;

import com.android.spotifyapp.data.network.model.Artist;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistsAlbum {
    private int limit;
    private int total;
    @SerializedName("items")
    private List<Items> items;

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public List<Items> getItems() {
        return items;
    }

    public static class Items {
        private String album_type;
        @SerializedName("artists")
        private List<Artist> artists;
        @SerializedName("images")
        private List<Images> images;
        private String id;
        private String name;
        private String release_date;
        private String release_date_precision;
        private int total_tracks;

        public String getAlbum_type() {
            return album_type;
        }

        public List<Artist> getArtists() {
            return artists;
        }

        public List<Images> getImages() {
            return images;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getRelease_date() {
            return release_date;
        }

        public String getRelease_date_precision() {
            return release_date_precision;
        }

        public int getTotal_tracks() {
            return total_tracks;
        }
    }

    public static class Artists {
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
