package com.android.spotifyapp.data.viewModelPackage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.spotifyapp.data.network.dataSources.dataFactories.AlbumDataSourceFactory;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.repositories.AlbumFullRepository;
import com.android.spotifyapp.ui.States.NetworkState;

public class AlbumFullViewModel extends AndroidViewModel {
    private AlbumFullRepository albumFullRepository;
    private AlbumDataSourceFactory albumDataSourceFactory;
    private MutableLiveData<NetworkState> state;
    private PagedList.Config config;
    private LiveData<PagedList<Artistsalbum.Items>> pagedListLiveData;
    public AlbumFullViewModel(@NonNull Application application) {
        super(application);
        albumFullRepository = AlbumFullRepository.getInstance(application);
        state = albumFullRepository.getState();
        config = new PagedList.Config.Builder()
                .setPageSize(5)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(8)
                .build();
    }

    public void init(String id) {
        albumFullRepository.init(id);
        albumDataSourceFactory = albumFullRepository.getAlbumDataSourceFactory();
        pagedListLiveData = new LivePagedListBuilder(albumDataSourceFactory, config).build();
    }
    public LiveData<PagedList<Artistsalbum.Items>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public MutableLiveData<NetworkState> getState() {
        return state;
    }
}
