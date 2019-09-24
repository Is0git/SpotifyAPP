package com.android.spotifyapp.data.viewModelPackage.homeViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.android.spotifyapp.data.network.dataSources.dataFactories.MyPlaylistDataSourceFactory;
import com.android.spotifyapp.data.network.model.MyPlaylist;
import com.android.spotifyapp.data.repositories.homeRepositories.MyPlaylistFullRepository;
import com.android.spotifyapp.ui.States.NetworkState;


public class MyPlaylistFullViewModel extends AndroidViewModel {
    private MyPlaylistDataSourceFactory myPlaylistDataSourceFactory;
    private MutableLiveData<NetworkState> state;
    private MyPlaylistFullRepository myPlaylistFullRepository;
    private LiveData<PagedList<MyPlaylist.Items>> liveDataPageList;

    public MyPlaylistFullViewModel(@NonNull Application application) {
        super(application);
        myPlaylistFullRepository = MyPlaylistFullRepository.getInstance(application);
        state = myPlaylistFullRepository.getState();
        myPlaylistDataSourceFactory = myPlaylistFullRepository.getMyPlaylistDataSourceFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setPageSize(3)
                .setEnablePlaceholders(false)
                .build();
        liveDataPageList = (new LivePagedListBuilder(myPlaylistDataSourceFactory, config))
                .build();

    }

    public MutableLiveData<NetworkState> getState() {
        return state;
    }

    public LiveData<PagedList<MyPlaylist.Items>> getLiveDataPageList() {
        return liveDataPageList;
    }
}
