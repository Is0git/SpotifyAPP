package com.android.spotifyapp.ui.adapters.homeadapters.HomeFull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.spotifyapp.data.network.model.RecentlyPlayed.Items;
import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.databinding.RecentlySongsListBinding;

public class RecentlyPlayedFull extends PagedListAdapter<RecentlyPlayed.Items, RecentlyPlayedFull.ViewHolder> {
    public RecentlyPlayedFull() {
        super(Items.callback);
    }

    @NonNull
    @Override
    public RecentlyPlayedFull.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecentlySongsListBinding recentlySongsListBinding = RecentlySongsListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(recentlySongsListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyPlayedFull.ViewHolder holder, int position) {
        RecentlyPlayed.Items item = getItem(position);
        holder.recentlySongsListBinding.setRecentlyPlayedItem(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecentlySongsListBinding recentlySongsListBinding;
        public ViewHolder(RecentlySongsListBinding recentlySongsListBinding) {
            super(recentlySongsListBinding.getRoot());
            this.recentlySongsListBinding = recentlySongsListBinding;
        }
    }

}
