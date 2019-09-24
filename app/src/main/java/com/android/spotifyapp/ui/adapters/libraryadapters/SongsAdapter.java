package com.android.spotifyapp.ui.adapters.libraryadapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.data.network.model.libmodel.Tracks;
import com.android.spotifyapp.databinding.SongsListBinding;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {
    private Tracks tracks;

    public SongsAdapter() {
        tracks = new Tracks();
    }

    @NonNull
    @Override
    public SongsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SongsListBinding songsListBinding = SongsListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(songsListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsAdapter.ViewHolder holder, int position) {
        Tracks.Items item = tracks.getItems().get(position);
            holder.songsListBinding.setItem(item);
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(tracks.getItems() != null) {
            return tracks.getItems().size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SongsListBinding songsListBinding;
        public ViewHolder(@NonNull SongsListBinding songsListBinding) {
            super(songsListBinding.getRoot());
            this.songsListBinding = songsListBinding;
        }
    }
}
