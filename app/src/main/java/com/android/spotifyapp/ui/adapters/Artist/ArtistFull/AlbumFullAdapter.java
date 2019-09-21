package com.android.spotifyapp.ui.adapters.Artist.ArtistFull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.databinding.AlbumFullAdapterBinding;

import static com.android.spotifyapp.data.network.model.byId.Artistsalbum.Items.DIFF_CALLBACK;

public class AlbumFullAdapter extends PagedListAdapter<Artistsalbum.Items, AlbumFullAdapter.ViewHolder> {
    public AlbumFullAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumFullAdapterBinding albumFullAdapterBinding = AlbumFullAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(albumFullAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artistsalbum.Items item = getItem(position);
        holder.albumFullAdapterBinding.setItems(item);
        if (item != null && item.getImages() != null) {
            if (item.getImages().size() > 0) {
                holder.albumFullAdapterBinding.setImageUrl(item.getImages().get(0).getUrl());
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AlbumFullAdapterBinding albumFullAdapterBinding;
        public ViewHolder(@NonNull  AlbumFullAdapterBinding albumFullAdapterBinding) {
            super(albumFullAdapterBinding.getRoot());
            this.albumFullAdapterBinding = albumFullAdapterBinding;
        }
    }
}
