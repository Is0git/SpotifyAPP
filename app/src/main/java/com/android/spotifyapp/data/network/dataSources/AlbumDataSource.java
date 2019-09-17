package com.android.spotifyapp.data.network.dataSources;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.network.services.ArtistService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AlbumDataSource extends PositionalDataSource<Artistsalbum> {
    public static final int page_size = 20;
    public static final int offset = 1;
    private ArtistService artistService;
    private String token;
    private String id;

    public AlbumDataSource(ArtistService artistService, String token, String id) {
        this.artistService = artistService;
        this.token = token;
        this.id = id;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Artistsalbum> callback) {
        Observable<Artistsalbum> observable = artistService.getArtistAlbum("Bearer " + token, id, params.pageSize, params.requestedStartPosition);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Artistsalbum>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("DATASOURCE", "onSub: " + params.pageSize + "t: " + params.requestedStartPosition);
                    }

                    @Override
                    public void onNext(Artistsalbum artistsalbum) {
                        Log.d("DATASOURCE", "onnexts: " + artistsalbum.getItems().get(0).getId());
                                callback.onResult(artistsalbum.getItems(), params.requestedStartPosition, params.pageSize);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DATASOURCE", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("DATASOURCE", "onComplete: ");
                    }
                });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Artistsalbum> callback) {
        Observable<Artistsalbum> observable = artistService.getArtistAlbum("Bearer" + token, id, params.loadSize, params.startPosition);

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Artistsalbum>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("DATASOURCE", "onSub1: ");
                    }

                    @Override
                    public void onNext(Artistsalbum artistsalbum) {
                        Log.d("DATASOURCE", "oNnext1");
                        callback.onResult(artistsalbum.getItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DATASOURCE", "onError1: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public Observable<Artistsalbum> getObsersable(LoadInitialParams params) {
        return artistService.getArtistAlbum("Bearer" + token, id, params.pageSize, params.requestedStartPosition).map(new Function<Artistsalbum, Artistsalbum.Items>() {
            @Override
            public Artistsalbum.Items apply(Artistsalbum artistsalbum) throws Exception {
                return Observable.fromIterable(artistsalbum.getItems());
            }
        })
    }

}
