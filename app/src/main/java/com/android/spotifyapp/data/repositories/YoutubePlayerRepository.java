package com.android.spotifyapp.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.data.network.model.YoutubeVideos;
import com.android.spotifyapp.data.network.services.YoutubeService;
import com.android.spotifyapp.di.components.DaggerYoutubeComponent;
import com.android.spotifyapp.di.components.YoutubeComponent;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.qualifiers.YoutubeQualifier;
import com.android.spotifyapp.utils.Contracts.YoutubeAPIContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class YoutubePlayerRepository {
    private static YoutubePlayerRepository playerRepository_instance;
    @YoutubeQualifier
    @Inject
    Retrofit retrofit;
    private YoutubeService youtubeService;
    private CompositeDisposable disposable;
    private MutableLiveData<YoutubeVideos> youtubeVideosMutableLiveData;

    private YoutubePlayerRepository() {
        youtubeVideosMutableLiveData = new MutableLiveData<>();
        disposable = new CompositeDisposable();
        YoutubeComponent youtubeComponent = DaggerYoutubeComponent.builder().viewModelsModule(new ViewModelsModule(null)).build();
        youtubeComponent.inject(this);
    }

    public static YoutubePlayerRepository getInstance() {
        if (playerRepository_instance == null) {
            playerRepository_instance = new YoutubePlayerRepository();
        }
        return playerRepository_instance;
    }


    public LiveData<YoutubeVideos> getVideos(String part, int maxResults, String q, String type) {
        youtubeService = retrofit.create(YoutubeService.class);
        Observable<YoutubeVideos> observable = youtubeService.getVideos(part, maxResults, q, type, YoutubeAPIContract.getApiKey());
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YoutubeVideos>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(YoutubeVideos youtubeVideos) {
                        Log.d("YoutubeMessage", "onNext " + youtubeVideos.getItems().size());
                        youtubeVideosMutableLiveData.setValue(youtubeVideos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("YoutubeMessage", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return youtubeVideosMutableLiveData;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }
}
