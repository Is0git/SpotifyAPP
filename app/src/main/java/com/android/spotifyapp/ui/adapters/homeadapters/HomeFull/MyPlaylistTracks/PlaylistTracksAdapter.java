package com.android.spotifyapp.ui.adapters.homeadapters.HomeFull.MyPlaylistTracks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.data.network.model.byId.PlaylistTracks;
import com.android.spotifyapp.databinding.PlaylistTracksBinding;
import  com.android.spotifyapp.data.network.model.byId.PlaylistTracks.Items;

public class PlaylistTracksAdapter extends PagedListAdapter<PlaylistTracks.Items, PlaylistTracksAdapter.ViewHolder> {
    public PlaylistTracksAdapter() {
        super(Items.callback);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaylistTracksBinding playlistTracksBinding = PlaylistTracksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(playlistTracksBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistTracks.Items item = getItem(position);
        holder.playlistTracksBinding.setItem(item);

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        PlaylistTracksBinding playlistTracksBinding;
        public ViewHolder(@NonNull PlaylistTracksBinding playlistTracksBinding) {
            super(playlistTracksBinding.getRoot());
            this.playlistTracksBinding = playlistTracksBinding;
        }
    }
}
