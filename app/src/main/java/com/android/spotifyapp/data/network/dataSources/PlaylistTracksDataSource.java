package com.android.spotifyapp.data.network.dataSources;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PositionalDataSource;

import com.android.spotifyapp.data.network.model.byId.PlaylistTracks;
import com.android.spotifyapp.data.network.services.MyPlaylistService;
import com.android.spotifyapp.ui.States.NetworkState;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;

public class PlaylistTracksDataSource extends PositionalDataSource<PlaylistTracks.Items> {
    private String access_token;
    private String id;
    private MyPlaylistService myPlaylistService;
    private MutableLiveData<NetworkState> state;

    public PlaylistTracksDataSource(String access_token, String id, MyPlaylistService myPlaylistService, MutableLiveData<NetworkState> state) {
        this.access_token = access_token;
        this.id = id;
        this.myPlaylistService = myPlaylistService;
        this.state = state;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<PlaylistTracks.Items> callback) {
        try{
            Call<PlaylistTracks> playlistTracksCall = myPlaylistService.getPlaylistTracks("Bearer " + access_token, id, params.pageSize, params.requestedStartPosition);
            List<PlaylistTracks.Items> items = Objects.requireNonNull(playlistTracksCall.execute().body()).getItems();
            Thread.sleep(500);
            callback.onResult(items, params.requestedStartPosition, items.size());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<PlaylistTracks.Items> callback) {
        try{
            state.postValue(NetworkState.START);
            Call<PlaylistTracks> playlistTracksCall = myPlaylistService.getPlaylistTracks("Bearer " + access_token, id, params.loadSize, params.startPosition);
            List<PlaylistTracks.Items> items = Objects.requireNonNull(playlistTracksCall.execute().body()).getItems();
            Thread.sleep(500);
            callback.onResult(items);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            state.postValue(NetworkState.LOADED);
        }

    }
}
