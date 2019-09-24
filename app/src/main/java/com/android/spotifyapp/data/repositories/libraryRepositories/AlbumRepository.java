package com.android.spotifyapp.data.repositories.libraryRepositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.spotifyapp.App;
import com.android.spotifyapp.data.network.model.libmodel.Albums;
import com.android.spotifyapp.data.network.services.LibraryService;
import com.android.spotifyapp.di.components.DaggerAlbumRepositoryComponent;
import com.android.spotifyapp.di.qualifiers.RetrofitQualifier;
import com.android.spotifyapp.utils.SharedPreferencesUtil;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;

public class AlbumRepository {
    private static AlbumRepository albumRepository;
    private MutableLiveData<Albums> albumsMutableLiveData;
    private LibraryService libraryService;
    private String token;
    @Inject @RetrofitQualifier
    Retrofit retrofit;
    private AlbumRepository(Application application) {
        albumsMutableLiveData = new MutableLiveData<>();
        DaggerAlbumRepositoryComponent.builder()
                .appComponent(((App)application.getApplicationContext()).getAppComponent())
                .build().inject(this);
        libraryService = retrofit.create(LibraryService.class);
        token = SharedPreferencesUtil.getPreferences(shared_preferences_auth, application).getString(access_token, null);
    }

    public static AlbumRepository getInstance(Application application) {
        if(albumRepository == null) {
            albumRepository = new AlbumRepository(application);
        }
        return albumRepository;
    }

    public LiveData<Albums> getAlbums(int limit) {
        Call<Albums> call = libraryService.getAlbums("Bearer " + token, limit);
        call.enqueue(new Callback<Albums>() {
            @Override
            public void onResponse(Call<Albums> call, Response<Albums> response) {
                albumsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Albums> call, Throwable t) {

            }
        });
        return albumsMutableLiveData;
    }
}
