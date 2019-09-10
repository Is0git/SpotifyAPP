package com.android.spotifyapp.ui.adapters.Artist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.byId.RelatedArtists;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RelatedArtistsAdapter extends RecyclerView.Adapter<RelatedArtistsAdapter.MyViewHolder> {
    private View view;
    private RelatedArtists relatedArtists;

    public RelatedArtistsAdapter() {
        relatedArtists = new RelatedArtists();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(relatedArtists.getArtists() != null) {
            if(relatedArtists.getArtists().get(position).getImnages() != null) {
                Picasso.with(view.getContext())
                        .load(relatedArtists.getArtists().get(position).getImnages().get(0).getUrl())
                        .fit()
                        .into(holder.artist_image);
            }
            holder.artist_name.setText(relatedArtists.getArtists().get(position).getName());
            holder.followers.setText(view.getContext().getString(R.string.followers, relatedArtists.getArtists().get(position).getFollowers().getTotal()));
        }
    }

    public void setRelatedArtists(RelatedArtists relatedArtists) {
        this.relatedArtists = relatedArtists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(relatedArtists.getArtists() != null) {
            return relatedArtists.getArtists().size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.artist_image) ImageView artist_image;
        @BindView(R.id.artist_name2) TextView artist_name;
        @BindView(R.id.followers) TextView followers;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
