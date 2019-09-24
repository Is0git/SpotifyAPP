package com.android.spotifyapp.ui.adapters.libraryadapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.data.network.model.libmodel.Albums;
import com.android.spotifyapp.databinding.AlbumLibraryListBinding;

public class AlbumLibraryAdapter extends RecyclerView.Adapter<AlbumLibraryAdapter.ViewHolder>{
    private Albums albums;

    public AlbumLibraryAdapter() {
        albums = new Albums();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumLibraryListBinding albumLibraryListBinding = AlbumLibraryListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(albumLibraryListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Albums.Items item = albums.getItems().get(position);
        holder.albumLibraryListBinding.setItems(item);
        if(albums.getItems().get(position).getAlbum().getImages() != null) {
            if(albums.getItems().get(position).getAlbum().getImages().size() >  0) {
                holder.albumLibraryListBinding.setImageUrl(albums.getItems().get(position).getAlbum().getImages().get(0).getUrl());
            }
        }
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(albums.getItems() != null) {
            return albums.getItems().size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AlbumLibraryListBinding albumLibraryListBinding;
        public ViewHolder(@NonNull AlbumLibraryListBinding albumLibraryListBinding) {
            super(albumLibraryListBinding.getRoot());
            this.albumLibraryListBinding = albumLibraryListBinding;
        }
    }
}
