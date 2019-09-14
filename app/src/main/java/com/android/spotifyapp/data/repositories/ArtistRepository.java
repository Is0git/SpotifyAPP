package com.android.spotifyapp.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.model.byId.ArtistTopSongs;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.network.model.byId.RelatedArtists;
import com.android.spotifyapp.data.network.services.ArtistService;
import com.android.spotifyapp.di.components.DaggerArtistRepositoryComponent;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;
import com.android.spotifyapp.utils.SharedPreferencesUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;


public class ArtistRepository {
    private static ArtistRepository artistRepository;
    private ArtistService artistService;
    private MutableLiveData<Artistsalbum> artistsAlbumMutableLiveData;
    private MutableLiveData<ArtistTopSongs> artistTopTracksMutableLiveData;
    private MutableLiveData<RelatedArtists> relatedArtistsMutableLiveData;
    private CompositeDisposable disposable;
    private String token;

    @Inject
    @RetrofitQualifier
    Retrofit retrofit;

    private ArtistRepository(Application application) {
        artistsAlbumMutableLiveData = new MutableLiveData<>();
        artistTopTracksMutableLiveData = new MutableLiveData<>();
        relatedArtistsMutableLiveData = new MutableLiveData<>();
        disposable = new CompositeDisposable();
        token = SharedPreferencesUtil.getPreferences(shared_preferences_auth, application).getString(access_token, "NO TOKEN");
        DaggerArtistRepositoryComponent.builder()
                .appComponent(((App) application.getApplicationContext()).getAppComponent())
                .build().inject(this);

    }

    public static ArtistRepository getInstance(Application application) {
        if(artistRepository == null) {
            artistRepository = new ArtistRepository(application);
        }
        return artistRepository;
    }

    public LiveData<Artistsalbum> getAlbums(String id) {
        artistService = retrofit.create(ArtistService.class);
        Observable<Artistsalbum> artistsAlbumObservable = artistService.getArtistAlbum("Bearer " + token, id);
        artistsAlbumObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Artistsalbum>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Artistsalbum artistsAlbum) {
                            artistsAlbumMutableLiveData.setValue(artistsAlbum);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
                return artistsAlbumMutableLiveData;
    }

    public LiveData<ArtistTopSongs> getTopTracks(String id, String country) {
        Observable<ArtistTopSongs> artistTopTracksObservable = artistService.getArtistTopTracks("Bearer " + token, id, country);
        artistTopTracksObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistTopSongs>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ArtistTopSongs artistTopTracks) {
                        artistTopTracksMutableLiveData.setValue(artistTopTracks);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return artistTopTracksMutableLiveData;
    }

    public LiveData<RelatedArtists> getRelatedArtistsLiveData(String id) {
        Observable<RelatedArtists> relatedArtistsObservable = artistService.getRelatedArtistsObservable("Bearer " + token, id);
        relatedArtistsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RelatedArtists>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(RelatedArtists relatedArtists) {
                        relatedArtistsMutableLiveData.setValue(relatedArtists);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return relatedArtistsMutableLiveData;
    }
    public CompositeDisposable getDisposable() {
        return disposable;
    }

}
