package com.android.spotifyapp.data.repositories;

public class FullPlaylistRepository {
    private static FullPlaylistRepository fullPlaylistRepository;

    private FullPlaylistRepository() {}

    public static FullPlaylistRepository getInstance() {
        if(fullPlaylistRepository == null) {
            fullPlaylistRepository = new FullPlaylistRepository();
        }
        return fullPlaylistRepository;
    }


}
