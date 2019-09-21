package com.android.spotifyapp.data.network.dataSources;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PositionalDataSource;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.data.network.services.ArtistService;
import com.android.spotifyapp.ui.States.NetworkState;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;

public class AlbumsDataSource extends PositionalDataSource<Artistsalbum.Items> {
    private ArtistService artistService;
    private String access_token;
    private String id;
    private MutableLiveData<NetworkState> state;

    public AlbumsDataSource(ArtistService artistService, String access_token, String id, MutableLiveData<NetworkState> state) {
        this.artistService = artistService;
        this.access_token = access_token;
        this.id = id;
        this.state = state;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Artistsalbum.Items> callback) {

        try {
            Call<Artistsalbum> call = artistService.getArtistAlbums("Bearer " + access_token, id, params.pageSize, params.requestedStartPosition);
            List<Artistsalbum.Items> items = Objects.requireNonNull(call.execute().body()).getItems();
            callback.onResult(items, params.requestedStartPosition, params.pageSize);


        } catch (IOException | NullPointerException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Artistsalbum.Items> callback) {
        try {
            state.postValue(NetworkState.START);
            Call<Artistsalbum> call = artistService.getArtistAlbums("Bearer " + access_token, id, params.loadSize, params.startPosition);
            List<Artistsalbum.Items> items = Objects.requireNonNull(call.execute().body()).getItems();
            callback.onResult(items);
            Thread.sleep(500);
        } catch (IOException | NullPointerException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            state.postValue(NetworkState.LOADED);
        }
    }
}
