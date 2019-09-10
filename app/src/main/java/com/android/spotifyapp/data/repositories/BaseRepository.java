package com.android.spotifyapp.data.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.model.User;
import com.android.spotifyapp.data.network.services.UserService;
import com.android.spotifyapp.di.components.BaseComponent;
import com.android.spotifyapp.di.components.DaggerBaseComponent;
import com.android.spotifyapp.di.modules.ActivityViewModelModule;
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

public class BaseRepository {
    @Inject
    @RetrofitQualifier
    Retrofit retrofit;
    private UserService userService;
    private MutableLiveData<User> mutableLiveData;
    private CompositeDisposable compositeDisposable;
    private static BaseRepository baseRepository_instance;
    private String token;
    private BaseRepository(Application application) {
        mutableLiveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        token = SharedPreferencesUtil.getPreferences(shared_preferences_auth, application).getString(access_token, "NO TOKEN");
        BaseComponent component = DaggerBaseComponent.builder()
                .appComponent(((App) application.getApplicationContext()).getAppComponent())
                .activityViewModelModule(new ActivityViewModelModule(null))
                .build();
        component.inject(this);
    }

    public static BaseRepository getInstance(Application application) {
        if(baseRepository_instance == null) {
            baseRepository_instance = new BaseRepository(application);
        }
        return baseRepository_instance;
    }

    public MutableLiveData<User> getUser() {


        userService = retrofit.create(UserService.class);
        Observable<User> observable = userService.getUser("Bearer " + token);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("SUBSCRIBED", "start" );
                    }

                    @Override
                    public void onNext(User user) {

                        mutableLiveData.setValue(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("SUBSCRIBED", "onSubscribess: " + e.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.d("SUBSCRIBED", "onSasda");
                    }
                });
        return this.mutableLiveData;
    }
    public CompositeDisposable getDisposables() {
        return compositeDisposable;
    }
}
