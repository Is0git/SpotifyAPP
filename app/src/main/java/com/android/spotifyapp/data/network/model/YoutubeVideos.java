package com.android.spotifyapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YoutubeVideos {
    @SerializedName("pageIfno")
    private PageInfo pageInfo;
    @SerializedName("items")
    private List<Items> items;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<Items> getItems() {
        return items;
    }

    public static class PageInfo{
        private int totalResults;
        private int resultsPerPage;

        public int getTotalResults() {
            return totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }
    }

    public static class Items {
        @SerializedName("id")
        Id id;

        public Id getId() {
            return id;
        }
    }

    public static class Id {
        private String kind;
        private String videoId;

        public String getKind() {
            return kind;
        }

        public String getVideoId() {
            return videoId;
        }
    }
}
