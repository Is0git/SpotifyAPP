package com.android.spotifyapp.data.network.dataSources;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PositionalDataSource;

import com.android.spotifyapp.data.network.model.MyPlaylist;
import com.android.spotifyapp.data.network.services.MyPlaylistService;
import com.android.spotifyapp.ui.States.NetworkState;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class PlaylistDataSource extends PositionalDataSource<MyPlaylist.Items> {
    private MyPlaylistService myPlaylistService;
    private String token;
    private MutableLiveData<NetworkState> state;

    public PlaylistDataSource(MyPlaylistService myPlaylistService, String token, MutableLiveData<NetworkState> state) {
        this.myPlaylistService = myPlaylistService;
        this.token = token;
        this.state = state;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<MyPlaylist.Items> callback) {
        Call<MyPlaylist> call = myPlaylistService.getPagedPlaylist("Bearer " + token, params.pageSize, params.requestedStartPosition);
        Log.d("INITITAL", "loadInitial: " + params.pageSize + " SL: " + params.requestedStartPosition);
        try {
            List<MyPlaylist.Items> items = call.execute().body().getMitems();
            callback.onResult(items, params.requestedStartPosition, params.pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<MyPlaylist.Items> callback) {

            Log.d("INITITAL", "loadRANGE: ");
            state.postValue(NetworkState.START);
            Call<MyPlaylist> call = myPlaylistService.getPagedPlaylist("Bearer " + token, params.loadSize, params.startPosition);
            try {
                List<MyPlaylist.Items> items = call.execute().body().getMitems();
                state.postValue(NetworkState.LOADED);
                Thread.sleep(500);
                callback.onResult(items);


            } catch (IOException | NullPointerException | InterruptedException e) {
                e.printStackTrace();
            }
    }
}


