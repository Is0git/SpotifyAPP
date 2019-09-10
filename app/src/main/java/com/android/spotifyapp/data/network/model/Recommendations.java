package com.android.spotifyapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Recommendations implements Serializable {
    @SerializedName("tracks")
    private List<Tracks> mtracks;
    @SerializedName("seeds")
    private List<Seeds> mseeds;


    public List<Tracks> getMtracks() {
        return mtracks;
    }

    public List<Seeds> getMseeds() {
        return mseeds;
    }

    public static class Tracks {
        @SerializedName("album")
        private Album album;
        @SerializedName("artists")
        private List<Artists> martists;
        private int disc_number;
        private Artist artist;
        private int duration_ms;
        private boolean explicit;
        @SerializedName("external_ids")
        private External_ids external_ids;
        @SerializedName("external_urls")
        private External_urls external_urls;
        private String href;
        private String id;
        private boolean is_local;
        private boolean is_playable;
        private String name;
        private int popularity;
        private String preview_url;
        private int track_number;
        private String type;
        private String uri;

        public void setArtist(Artist artist) {
            this.artist = artist;
        }

        public Artist getArtist() {
            return artist;
        }

        public Album getAlbum() {
            return album;
        }

        public List<Artists> getMartists() {
            return martists;
        }

        public int getDisc_number() {
            return disc_number;
        }

        public int getDuration_ms() {
            return duration_ms;
        }

        public boolean isExplicit() {
            return explicit;
        }

        public External_ids getExternal_ids() {
            return external_ids;
        }

        public External_urls getExternal_urls() {
            return external_urls;
        }

        public String getHref() {
            return href;
        }

        public String getId() {
            return id;
        }

        public boolean isIs_local() {
            return is_local;
        }

        public boolean isIs_playable() {
            return is_playable;
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

        public String getUri() {
            return uri;
        }
    }
    public static class Album {
        private String album_type;
        @SerializedName("artists")
        private List<Artists> martists;
        @SerializedName("external_urls")
        private External_urls external_urls;
        private String href;
        private String id;
        @SerializedName("images")
        private List<Images>mimages;
        private String name;
        private String release_date;
        private String release_date_precision;
        private int total_tracks;
        private String type;
        private String uri;

        public String getAlbum_type() {
            return album_type;
        }

        public List<Artists> getMartists() {
            return martists;
        }

        public External_urls getExternal_urls() {
            return external_urls;
        }

        public String getHref() {
            return href;
        }

        public String getId() {
            return id;
        }

        public List<Images> getMimages() {
            return mimages;
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

        public String getType() {
            return type;
        }

        public String getUri() {
            return uri;
        }
    }
    public static class External_urls {
        private String spotify;

        public String getSpotify() {
            return spotify;
        }
    }
    public static class External_ids {
        private String isrc;

        public String getIsrc() {
            return isrc;
        }
    }
    public static class Images {
        private int height;
        private String url;
        private int width;

        public int getHeight() {
            return height;
        }

        public String getUrl() {
            return url;
        }

        public int getWidth() {
            return width;
        }
    }
    public static class Artists {
        private External_urls external_urls;
        private String href;
        private String id;
        private String name;
        private String type;
        private String uri;

        public External_urls getExternal_urls() {
            return external_urls;
        }

        public String getHref() {
            return href;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getUri() {
            return uri;
        }
    }
    public static class Seeds {
        private int initialPoolSize;
        private int afterFilteringSize;
        private int afterRelinkingSize;
        private String id;
        private String type;
        private String href;

        public int getInitialPoolSize() {
            return initialPoolSize;
        }

        public int getAfterFilteringSize() {
            return afterFilteringSize;
        }

        public int getAfterRelinkingSize() {
            return afterRelinkingSize;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getHref() {
            return href;
        }
    }
}
