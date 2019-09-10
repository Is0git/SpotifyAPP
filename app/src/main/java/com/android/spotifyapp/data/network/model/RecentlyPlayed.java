package com.android.spotifyapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecentlyPlayed {
    @SerializedName("items")
    private List<Items> mitems;

    public List<Items> getMitems() {
        return mitems;
    }

    public static class Items {
        @SerializedName("track")
        private Track track;
        private String played_at;

        public Track getTrack() {
            return track;
        }

        public String getPlayed_at() {
            return played_at;
        }
    }
    public static class Track {
        @SerializedName("album")
        private Album album;
        @SerializedName("artists")
        private List<Artists>artists;
        private String id;
        private String name;
        private int popularity;
        private String preview_url;
        private int track_number;
        private String type;

        public Album getAlbums() {
            return album;
        }

        public List<Artists> getArtists() {
            return artists;
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
    public static class Album {
        private String album_type;
        @SerializedName("artists")
        private List<Artists>martists;
        @SerializedName("images")
        private List<Images>images;
        private String name;
        private String release_date;
        private String total_tracks;
        private String type;

        public String getAlbum_type() {
            return album_type;
        }

        public List<Artists> getMartists() {
            return martists;
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

        public String getTotal_tracks() {
            return total_tracks;
        }

        public String getType() {
            return type;
        }
    }
    public static class Artists {
        String name;
        String type;

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
    public static class Images {
        private int height;
        private String url;
        private int widrh;

        public int getHeight() {
            return height;
        }

        public String getUrl() {
            return url;
        }

        public int getWidrh() {
            return widrh;
        }
    }
}
