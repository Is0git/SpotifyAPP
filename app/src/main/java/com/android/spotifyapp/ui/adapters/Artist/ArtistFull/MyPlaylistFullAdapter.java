package com.android.spotifyapp.ui.adapters.Artist.ArtistFull;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.data.network.model.MyPlaylist;
import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.databinding.TestLayoutBinding;
import com.android.spotifyapp.ui.adapters.homeadapters.MyPlaylistsAdapter;

import static com.android.spotifyapp.data.network.model.MyPlaylist.Items.CALLBACK;
import static com.android.spotifyapp.data.network.model.byId.Artistsalbum.Items.DIFF_CALLBACK;

public class MyPlaylistFullAdapter extends PagedListAdapter<MyPlaylist.Items, MyPlaylistFullAdapter.MyViewHolder> {
    private MyPlaylistsAdapter.PlaylistListener listener;

    public void setListener(MyPlaylistsAdapter.PlaylistListener listener) {
        this.listener = listener;
    }

    public MyPlaylistFullAdapter() {
        super(CALLBACK);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TestLayoutBinding testLayoutBinding = TestLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(testLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyPlaylist.Items item = getItem(position);
        holder.testLayoutBinding.setMyPlaylistItem(item);
        if(item.getMimages() != null) {
            if(item.getMimages().size() > 0) {
                holder.testLayoutBinding.setImageUrl(item.getMimages().get(0).getUrl());
            }
        }

        holder.testLayoutBinding.setPlaylistOnClick(listener);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TestLayoutBinding testLayoutBinding;
        public MyViewHolder(@NonNull TestLayoutBinding testLayoutBinding) {
            super(testLayoutBinding.getRoot());
            this.testLayoutBinding = testLayoutBinding;
        }
    }

}
