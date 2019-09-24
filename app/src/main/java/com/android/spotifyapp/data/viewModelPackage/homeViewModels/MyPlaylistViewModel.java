package com.android.spotifyapp.data.viewModelPackage.homeViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.spotifyapp.data.network.model.MyPlaylist;
import com.android.spotifyapp.data.network.model.Post.MyPlaylistPost;
import com.android.spotifyapp.data.repositories.homeRepositories.MyPlaylistRepository;

public class MyPlaylistViewModel extends AndroidViewModel {
    private LiveData<MyPlaylist> myPlaylistLiveData;
    private MyPlaylistRepository myPlaylistRepository;
    private String playlist_id;
    public MyPlaylistViewModel(@NonNull Application application) {
        super(application);
        myPlaylistRepository = MyPlaylistRepository.getInstance(application);
    }
    public LiveData<MyPlaylist> getMyPlaylistLiveData() {
        this.myPlaylistLiveData = myPlaylistRepository.getMyPlaylist();
        return myPlaylistLiveData;
    }

    public LiveData<MyPlaylist> getMyPlaylist() {
        return myPlaylistLiveData;
    }
    public void deletePlaylist(String playlist_id) {
        myPlaylistRepository.delete_playlist(playlist_id);
    }

    public void createPlaylist(MyPlaylistPost myPlaylistPost) {
        myPlaylistRepository.createNewPlaylist(myPlaylistPost);
    }

    public String getPlaylist_id() {
        return myPlaylistRepository.getPlaylist_id();
    }

    public void setPlaylist_id(String playlist_id) {
        myPlaylistRepository.setPlaylist_id(playlist_id);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        myPlaylistRepository.getDisposables().clear();
    }
}
