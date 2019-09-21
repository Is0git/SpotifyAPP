package com.android.spotifyapp.data.network.dataSources;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PositionalDataSource;

import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.data.network.services.RecentlyPlayedService;
import com.android.spotifyapp.ui.States.NetworkState;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import static com.android.spotifyapp.utils.TAGS.TAG4;

public class RecentlyPlayedDataSource extends PositionalDataSource<RecentlyPlayed.Items> {

    private RecentlyPlayedService recentlyPlayedService;
    private String access_token;
    private MutableLiveData<NetworkState> state;
    public RecentlyPlayedDataSource(RecentlyPlayedService recentlyPlayedService, String access_token, MutableLiveData<NetworkState> state) {
        this.recentlyPlayedService = recentlyPlayedService;
        this.access_token = access_token;
        this.state = state;

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<RecentlyPlayed.Items> callback) {

        Call<RecentlyPlayed> call = recentlyPlayedService.getRecentlyPlayed(params.pageSize, params.requestedStartPosition, "Bearer " + access_token);
        try {
            List<RecentlyPlayed.Items> items = call.execute().body().getMitems();
            callback.onResult(items, params.requestedStartPosition, params.pageSize);
        } catch (NullPointerException e){
            Log.d(TAG4, "loadInitial: " + e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<RecentlyPlayed.Items> callback) {
        Log.d("POSITION", "loadRange: " + params.startPosition + "loadsize: " + params.loadSize);
        state.postValue(NetworkState.START);
            Call<RecentlyPlayed> call = recentlyPlayedService.getRecentlyPlayed(params.loadSize, params.startPosition, "Bearer " + access_token);
        try {
            Thread.sleep(500);
            List<RecentlyPlayed.Items> items = call.execute().body().getMitems();
            callback.onResult(items);
            state.postValue(NetworkState.LOADED);
        } catch (NullPointerException e){
            Log.d(TAG4, "loadInitial: " + e.getMessage());

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
