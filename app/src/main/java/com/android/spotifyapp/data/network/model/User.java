package com.android.spotifyapp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    private String country;
    private String display_name;
    private String email;
    @SerializedName("followers")
    private Followers followers;
    private String id;
    @SerializedName("images")
    private List<Mimages> mimages;
    private String product;
    private String user;

    public List<Mimages> getMimages() {
        return mimages;
    }

    public String getCountry() {
        return country;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getEmail() {
        return email;
    }

    public Followers getFollowers() {
        return followers;
    }

    public String getId() {
        return id;
    }



    public String getProduct() {
        return product;
    }

    public String getUser() {
        return user;
    }

    public static class Followers {
        private String total;

        public String getTotal() {
            return total;
        }
    }
    public static class Mimages {
        private String url;

        public String getUrl() {
            return url;
        }
    }
}
