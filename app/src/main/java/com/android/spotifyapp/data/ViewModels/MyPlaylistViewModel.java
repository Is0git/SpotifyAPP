package com.android.spotifyapp.data.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.MyPlaylist;
import com.android.spotifyapp.data.network.model.Post.MyPlaylistPost;
import com.android.spotifyapp.data.repositories.MyPlaylistRepository;

public class MyPlaylistViewModel extends AndroidViewModel {
    private LiveData<MyPlaylist> myPlaylistLiveData;
    private MyPlaylistRepository myPlaylistRepository;
    public MyPlaylistViewModel(@NonNull Application application) {
        super(application);
        myPlaylistRepository = MyPlaylistRepository.getInstance(application);
    }
    public LiveData<MyPlaylist> getMyPlaylistLiveData(String access_token) {
        this.myPlaylistLiveData = myPlaylistRepository.getMyPlaylist(access_token);
        return myPlaylistLiveData;
    }

    public void deletePlaylist(String playlist_id) {
        myPlaylistRepository.delete_playlist(playlist_id);
    }

    public void createPlaylist(MyPlaylistPost myPlaylistPost) {
        myPlaylistRepository.createNewPlaylist(myPlaylistPost);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        myPlaylistRepository.getDisposables().clear();
    }
}
