package com.android.spotifyapp.ui.adapters.Artist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.data.network.model.byId.ArtistTopSongs;
import com.android.spotifyapp.databinding.TopSongsListBinding;

public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.MyViewHolder> {
    ArtistTopSongs artistTopTracks;
    View view;

    public TopSongsAdapter() {
       artistTopTracks = new ArtistTopSongs();
    }

    @NonNull
    @Override
    public TopSongsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TopSongsListBinding topSongsListBinding = TopSongsListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(topSongsListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSongsAdapter.MyViewHolder holder, int position) {
        final ArtistTopSongs.Tracks track = artistTopTracks.getTracks().get(position);
        holder.topSongsListBinding.setTopTracks(track);
    }
    public void updateData(ArtistTopSongs artistTopTracks) {
        this.artistTopTracks = artistTopTracks;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(artistTopTracks.getTracks() != null) {
            return artistTopTracks.getTracks().size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TopSongsListBinding topSongsListBinding;
        public MyViewHolder(@NonNull TopSongsListBinding topSongsListBinding) {
            super(topSongsListBinding.getRoot());
            this.topSongsListBinding = topSongsListBinding;

        }
    }
}
