package com.android.spotifyapp.data.viewModelPackage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.spotifyapp.data.network.dataSources.dataFactories.PlaylistTracksDataSourceFactory;
import com.android.spotifyapp.data.network.model.byId.PlaylistTracks;
import com.android.spotifyapp.data.repositories.PlaylistTracksRepository;
import com.android.spotifyapp.ui.States.NetworkState;

public class PlaylistTracksViewModel extends AndroidViewModel {
    private PlaylistTracksRepository playlistTracksRepository;
    private PlaylistTracksDataSourceFactory playlistTracksDataSourceFactory;
    private  PagedList.Config config;
    private LiveData<PagedList<PlaylistTracks.Items>> pagedListLiveData;
    private MutableLiveData<NetworkState> state;
    public PlaylistTracksViewModel(@NonNull Application application) {
        super(application);
        playlistTracksRepository = PlaylistTracksRepository.getInstance(application);
        config = new PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(15)
                .build();
        state = playlistTracksRepository.getState();

    }

    public LiveData<PagedList<PlaylistTracks.Items>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public void init(String id) {
        playlistTracksDataSourceFactory = playlistTracksRepository.playlistTracksDataSourceFactory(id);
        pagedListLiveData = new LivePagedListBuilder(playlistTracksDataSourceFactory, config).build();
    }

    public MutableLiveData<NetworkState> getState() {
        return state;
    }
}
