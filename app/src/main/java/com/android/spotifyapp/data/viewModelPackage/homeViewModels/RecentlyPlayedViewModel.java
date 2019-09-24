package com.android.spotifyapp.data.viewModelPackage.homeViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.spotifyapp.data.network.dataSources.dataFactories.RecentlyPlayedDataSourceFactory;
import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.data.repositories.homeRepositories.RecentlyPlayedRepository;
import com.android.spotifyapp.ui.States.NetworkState;

public class RecentlyPlayedViewModel extends AndroidViewModel {
    private RecentlyPlayedRepository recentlyPlayedRepository;
    private RecentlyPlayedDataSourceFactory recentlyPlayedDataSourceFactory;
    private LiveData<PagedList<RecentlyPlayed.Items>> pagedListLiveData;
    private MutableLiveData<NetworkState> state;

    public RecentlyPlayedViewModel(@NonNull Application application) {
        super(application);
        recentlyPlayedRepository = RecentlyPlayedRepository.getInstance(application);
        recentlyPlayedDataSourceFactory = recentlyPlayedRepository.recentlyPlayedDataSourceFactory();
        PagedList.Config config = (new PagedList.Config.Builder())
                .setPageSize(15)
                .setInitialLoadSizeHint(20)
                .setEnablePlaceholders(false)
                .build();
        pagedListLiveData = new LivePagedListBuilder(recentlyPlayedDataSourceFactory, config).build();
        state = recentlyPlayedRepository.getState();
    }

    public LiveData<PagedList<RecentlyPlayed.Items>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public MutableLiveData<NetworkState> getState() {
        return state;
    }
}
