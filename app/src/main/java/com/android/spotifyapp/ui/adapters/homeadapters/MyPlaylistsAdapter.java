package com.android.spotifyapp.ui.adapters.homeadapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.MyPlaylist;
import com.android.spotifyapp.databinding.MyplaylistHorizontalListBinding;

public class MyPlaylistsAdapter extends RecyclerView.Adapter<MyPlaylistsAdapter.MyViewHolder> {
    private MyPlaylist myPlaylist;
    private PlaylistListener playlistListener;

    public MyPlaylistsAdapter() {
        this.myPlaylist = new MyPlaylist();
    }

    @NonNull
    @Override
    public MyPlaylistsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyplaylistHorizontalListBinding myplaylistHorizontalListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.myplaylist_horizontal_list, parent, false);
        return new MyViewHolder(myplaylistHorizontalListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPlaylistsAdapter.MyViewHolder holder, int position) {
        final MyPlaylist.Items item = myPlaylist.getMitems().get(position);
            holder.myplaylistHorizontalListBinding.setMyPlaylist(item);
            try {
                holder.myplaylistHorizontalListBinding.setImageUrl(myPlaylist.getMitems().get(position).getMimages().get(0).getUrl());
            } catch (IndexOutOfBoundsException exception) {
                Log.d("EXCEPTION", "onBindViewHolder: " + exception.getMessage());
            }

    }

    public void setPlaylistListener(PlaylistListener playlistListener) {
        this.playlistListener = playlistListener;
    }
    @Override
    public int getItemCount() {
        if(myPlaylist.getMitems() != null)
        {
            return myPlaylist.getMitems().size();
        }
        return 0;
    }

    public void UpdateList(MyPlaylist myPlaylist) {
        this.myPlaylist = myPlaylist;
        notifyDataSetChanged();

    }
    public class MyViewHolder extends RecyclerView.ViewHolder  {
        MyplaylistHorizontalListBinding myplaylistHorizontalListBinding;
        public MyViewHolder(@NonNull MyplaylistHorizontalListBinding myplaylistHorizontalListBinding) {
            super(myplaylistHorizontalListBinding.getRoot());
            this.myplaylistHorizontalListBinding = myplaylistHorizontalListBinding;
            myplaylistHorizontalListBinding.getRoot().setOnLongClickListener(view -> {
                if(getAdapterPosition() >= 0) {
                    playlistListener.onPlaylistItemClick(myPlaylist.getMitems().get(getAdapterPosition()).getId());
                }
                return false;
            });

        }


    }
    public interface PlaylistListener {

        void onPlaylistItemClick(String id);
        }
}
