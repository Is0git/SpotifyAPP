package com.android.spotifyapp.ui.adapters.Artist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.byId.ArtistTopTracks;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.MyViewHolder> {
    ArtistTopTracks artistTopTracks;
    View view;

    public TopSongsAdapter() {
       artistTopTracks = new ArtistTopTracks();
    }

    @NonNull
    @Override
    public TopSongsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_songs_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSongsAdapter.MyViewHolder holder, int position) {
        if(artistTopTracks.getTracks() != null) {
            if(artistTopTracks.getTracks().get(position).getAlbum().getImages() != null) {
                Picasso.with(view.getContext())
                        .load(artistTopTracks.getTracks().get(position).getAlbum().getImages().get(0).getUrl())
                        .fit()
                        .into(holder.song_image);
            }
            holder.artist_name.setText(artistTopTracks.getTracks().get(position).getArtists().get(0).getName());
            holder.song_name.setText(artistTopTracks.getTracks().get(position).getName());
        }
    }
    public void updateData(ArtistTopTracks artistTopTracks) {
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
        @BindView(R.id.song_image) ImageView song_image;
        @BindView(R.id.top_songList_song_name) TextView song_name;
        @BindView(R.id.top_song_list_artist_name) TextView artist_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
