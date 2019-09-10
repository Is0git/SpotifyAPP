package com.android.spotifyapp.ui.GlobalState;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;

public class CurrentSongState {
    private static CurrentSongState currentSongState;

    private CurrentSongState() {}

    public static CurrentSongState getInstance() {
        if(currentSongState == null) {
            currentSongState = new CurrentSongState();
        }
        return currentSongState;
    }
    private PlayerConstants.PlayerState state;
    private String title;
    private String id;
    private int song_duration;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getSong_duration() {
        return song_duration;
    }

    public void setSong_duration(int song_duration) {
        this.song_duration = song_duration;
    }

    public PlayerConstants.PlayerState getState() {
        return state;
    }

    public void setState(PlayerConstants.PlayerState state) {
        this.state = state;
    }
}


