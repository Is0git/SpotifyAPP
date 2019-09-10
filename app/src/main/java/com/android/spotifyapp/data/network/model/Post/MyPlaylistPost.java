package com.android.spotifyapp.data.network.model.Post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPlaylistPost {

    private String name;
    private String description;
    @SerializedName("public")
    private Boolean status;
    private Boolean collaborative;

    public Boolean getCollaborative() {
        return collaborative;
    }

    public void setCollaborative(Boolean collaborative) {
        this.collaborative = collaborative;
    }

    public MyPlaylistPost(String name, String description, Boolean status, Boolean collaborative) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.collaborative = collaborative;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public static class Images {
        public Images(Integer height, String url, Integer width) {
            this.height = height;
            this.url = url;
            this.width = width;
        }

        private Integer height;
        private String url;
        private Integer width;

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }
    }
}
