package com.android.spotifyapp.data.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.YoutubeVideos;
import com.android.spotifyapp.data.repositories.YoutubePlayerRepository;
import com.android.spotifyapp.ui.GlobalState.CurrentSongState;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;

public class YoutubePlayerViewmodel extends AndroidViewModel {
        private YoutubePlayerRepository youtubePlayerRepository;
        private LiveData<YoutubeVideos> youtubeVideosLIvedata;
    public YoutubePlayerViewmodel(@NonNull Application application) {
        super(application);
        youtubePlayerRepository = YoutubePlayerRepository.getInstance();
    }


    public LiveData<YoutubeVideos> getVideos(String part, int maxResults, String q, String type) {
        youtubeVideosLIvedata = youtubePlayerRepository.getVideos(part, maxResults, q, type);
        return youtubeVideosLIvedata;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        youtubePlayerRepository.getDisposable().clear();
    }
}
