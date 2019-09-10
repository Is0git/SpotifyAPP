package com.android.spotifyapp.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.data.network.model.AccessToken;
import com.android.spotifyapp.data.network.services.AuthService;
import com.android.spotifyapp.di.components.DaggerLoginComponent;
import com.android.spotifyapp.di.components.LoginComponent;
import com.android.spotifyapp.di.qualifiers.AuthQualifier;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.android.spotifyapp.utils.Contracts.SpotifyAuthContract.AUTH_CODE;
import static com.android.spotifyapp.utils.Contracts.SpotifyAuthContract.CLIENT_ID;
import static com.android.spotifyapp.utils.Contracts.SpotifyAuthContract.CLIENT_SECRET;
import static com.android.spotifyapp.utils.Contracts.SpotifyAuthContract.REDIRECT_URL;

public class AuthRepository {
    private static AuthRepository authRepository_instance = null;
    @Inject
    @AuthQualifier
    Retrofit retrofit;
    private CompositeDisposable compositeDisposable;

    private AuthRepository() {
        compositeDisposable = new CompositeDisposable();
    }

    public static AuthRepository getInstance() {

        if (authRepository_instance == null) {
            authRepository_instance = new AuthRepository();
        }
        return authRepository_instance;
    }

    public LiveData<AccessToken> getAccess(String code) {
        final MutableLiveData<AccessToken> access_data = new MutableLiveData<>();
        LoginComponent loginComponent = DaggerLoginComponent.create();
        loginComponent.inject(this);
        AuthService authService = retrofit.create(AuthService.class);
        Observable<AccessToken> observable = authService.getAccessToken(CLIENT_ID, CLIENT_SECRET, code, REDIRECT_URL, AUTH_CODE);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccessToken>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AccessToken accessToken) {
                        access_data.setValue(accessToken);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return access_data;
    }

    public CompositeDisposable getDisposables() {
        return compositeDisposable;
    }
}
