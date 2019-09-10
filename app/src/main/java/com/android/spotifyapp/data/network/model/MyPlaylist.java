package com.android.spotifyapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPlaylist {
    private String href;
    @SerializedName("items")
    private List<items> mitems;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;

    public String getHref() {
        return href;
    }

    public List<items> getMitems() {
        return mitems;
    }

    public int getLimit() {
        return limit;
    }

    public String getNext() {
        return next;
    }

    public int getOffset() {
        return offset;
    }

    public String getPrevious() {
        return previous;
    }

    public int getTotal() {
        return total;
    }

    public static class items {
        private boolean collaborative;
        @SerializedName("external_urls")
        private External_urls mexternal_urls;
        private String href;
        private String id;
        @SerializedName("images")
        private List<Images> mimages;
        private String name;
        @SerializedName("owner")
        private Owner mowner;
        private String primary_color;
        @SerializedName("public")
        private boolean mpublic;
        private String snapshot_id;
        @SerializedName("tracks")
        private Tracks mtracks;
        private String type;
        private String uri;

        public boolean isCollaborative() {
            return collaborative;
        }

        public External_urls getMexternal_urls() {
            return mexternal_urls;
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

        public Owner getMowner() {
            return mowner;
        }

        public String getPrimary_color() {
            return primary_color;
        }

        public boolean isMpublic() {
            return mpublic;
        }

        public String getSnapshot_id() {
            return snapshot_id;
        }

        public Tracks getMtracks() {
            return mtracks;
        }

        public String getType() {
            return type;
        }

        public String getUri() {
            return uri;
        }
    }

    public static class External_urls{
        private String spotify;

        public String getSpotify() {
            return spotify;
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
    public static class Owner{
        private String display_name;
        @SerializedName("external_urls")
        private External_urls external_urls;
        private String href;
        private String id;
        private String type;
        private String uri;

        public String getDisplay_name() {
            return display_name;
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

        public String getType() {
            return type;
        }

        public String getUri() {
            return uri;
        }
    }
    public static class Tracks {
        private String href;
        private int total;

        public String getHref() {
            return href;
        }

        public int getTotal() {
            return total;
        }
    }
}
