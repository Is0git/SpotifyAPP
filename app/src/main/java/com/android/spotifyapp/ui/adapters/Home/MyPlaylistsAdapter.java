package com.android.spotifyapp.ui.adapters.Home;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.MyPlaylist;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPlaylistsAdapter extends RecyclerView.Adapter<MyPlaylistsAdapter.MyViewHolder> {
    private MyPlaylist myPlaylist;
    private View view;
    private PlaylistListener playlistListener;

    public MyPlaylistsAdapter() {
        this.myPlaylist = new MyPlaylist();
    }

    @NonNull
    @Override
    public MyPlaylistsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myplaylist_horizontal_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPlaylistsAdapter.MyViewHolder holder, int position) {
        holder.itemView.setLongClickable(true);
        if(myPlaylist.getMitems().get(position).getMimages().size() > 0) {
            com.android.spotifyapp.utils.ProgressBar.progressBarVisible(holder.progressBar);
            Picasso.with(view.getContext())
                    .load(myPlaylist.getMitems().get(position).getMimages().get(0).getUrl())
                    .fit()
                    .into(holder.playlist_image, new Callback() {
                        @Override
                        public void onSuccess() {
                            com.android.spotifyapp.utils.ProgressBar.progressBarUnvisible(holder.progressBar);
                        }

                        @Override
                        public void onError() {
                            com.android.spotifyapp.utils.ProgressBar.progressBarUnvisible(holder.progressBar);
                        }
                    });
        }
        holder.playlist_title.setText(myPlaylist.getMitems().get(position).getName());
        holder.playlist_items.setText(view.getContext().getString(R.string.playlist_items, myPlaylist.getMitems().get(position).getMtracks().getTotal()));
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
        @BindView(R.id.MyPlaylist_image) ImageView playlist_image;
        @BindView(R.id.progressBarMyPlaylist) ProgressBar progressBar;
        @BindView(R.id.playlist_items) TextView playlist_items;
        @BindView(R.id.playlist_title) TextView playlist_title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(view -> {
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
