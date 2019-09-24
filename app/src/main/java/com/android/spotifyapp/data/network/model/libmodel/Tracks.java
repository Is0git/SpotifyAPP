package com.android.spotifyapp.data.network.model.libmodel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tracks {
        private List<Items> items;

        public List<Items> getItems() {
            return items;
        }

        public static class Items {

            @SerializedName("track")
            private Track track;

            public Track getTrack() {
                return track;
            }

            public static final DiffUtil.ItemCallback<Tracks.Items> callback = new DiffUtil.ItemCallback<Tracks.Items>() {
                @Override
                public boolean areItemsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
                    return oldItem.getTrack().getId() == newItem.getTrack().getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
                    return oldItem.equals(newItem);
                }
            };

            @Override
            public boolean equals(Object obj) {
                return this.getTrack().name.equals(((Items) obj).getTrack().name) && this.getTrack().track_number == ((Items) obj).getTrack().getTrack_number();
            }
        }

        public static class Track {
            @SerializedName("album")
            private Album album;
            @SerializedName("artists")
            private List<Artists> artists;
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
            private List<Artists> martists;
            @SerializedName("images")
            private List<Images> images;
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
