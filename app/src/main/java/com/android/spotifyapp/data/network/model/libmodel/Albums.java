package com.android.spotifyapp.data.network.model.libmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Albums {
   private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public static class Items {
        private Album album;

       public Album getAlbum() {
           return album;
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
