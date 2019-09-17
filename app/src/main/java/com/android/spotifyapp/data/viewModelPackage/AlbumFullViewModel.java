package com.android.spotifyapp.data.viewModelPackage;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.dataSources.AlbumDataSource;
import com.android.spotifyapp.data.network.dataSources.AlbumDataSourceFactory;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.network.services.ArtistService;
import com.android.spotifyapp.di.components.DaggerAlbumFullViewmodelComponent;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;

import javax.inject.Inject;

import retrofit2.Retrofit;


public class AlbumFullViewModel extends AndroidViewModel {
    @Inject @RetrofitQualifier
    Retrofit retrofit;
    ArtistService artistService;
    private AlbumDataSourceFactory albumDataSourceFactory;
    private MutableLiveData<AlbumDataSource> albumDataSourceMutableLiveData;
    private LiveData<PagedList<Artistsalbum.Items>> liveDataPageList;
    public AlbumFullViewModel(@NonNull Application application) {
        super(application);
        DaggerAlbumFullViewmodelComponent.builder().appComponent(((App) application.getApplicationContext()).getAppComponent()).build().inject(this);
        artistService = retrofit.create(ArtistService.class);
        albumDataSourceFactory = new AlbumDataSourceFactory(application, artistService, "0TnOYISbd1XYRBk9myaseg");
        albumDataSourceMutableLiveData = albumDataSourceFactory.getAlbumDataSourceMutableLiveData();
        Log.d("WUTT", "AlbumFullViewModel: " );

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        liveDataPageList = new LivePagedListBuilder(albumDataSourceFactory, config).build();


    }

    public LiveData<PagedList<Artistsalbum.Items>> getLiveDataPageList() {
        return liveDataPageList;
    }


    public MutableLiveData<AlbumDataSource> getAlbumDataSourceMutableLiveData() {
        return albumDataSourceMutableLiveData;
    }
}
