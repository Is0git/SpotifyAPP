package com.android.spotifyapp.ui.adapters.Home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.utils.ProgressBar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeHorizontal extends RecyclerView.Adapter<HomeHorizontal.MyViewHolder> {
    private RecentlyPlayed recentlyPlayed;
    private View view;
    private android.widget.ProgressBar progressBar;
    private OnItemListener onItemListener;
    public HomeHorizontal() {
        recentlyPlayed = new RecentlyPlayed();
    }

    @NonNull
    @Override
    public HomeHorizontal.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeHorizontal.MyViewHolder holder, int position) {
            holder.song_name.setText(recentlyPlayed.getMitems().get(position).getTrack().getName());
            holder.artist_name.setText(recentlyPlayed.getMitems().get(position).getTrack().getArtists().get(0).getName());
            ProgressBar.progressBarVisible(holder.progressBar);

        Picasso.with(view.getContext())
                .load(recentlyPlayed.getMitems().get(position).getTrack().getAlbums().getImages().get(0).getUrl())
                .fit()
                .into(holder.album_image, new Callback() {
                    @Override
                    public void onSuccess() {
//                            ProgressBar.progressBarUnvisible(holder.progressBar);
                        Log.d("IMAGE", "onBindViewHolder: END");
                    }

                    @Override
                    public void onError() {
                        ProgressBar.progressBarUnvisible(holder.progressBar);
                    }
                });

    }

    @Override
    public int getItemCount() {
        if(recentlyPlayed.getMitems() != null)
        {
            return recentlyPlayed.getMitems().size();
        }
        return 0;

    }
    public void setListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public void UpdateData(RecentlyPlayed recentlyPlayed) {
        this.recentlyPlayed = recentlyPlayed;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.album_image) ImageView album_image;
        @BindView(R.id.song_name)TextView song_name;
        @BindView(R.id.artist_name) TextView artist_name;
        @BindView(R.id.progressBar) android.widget.ProgressBar progressBar;
        @BindView(R.id.recently_play_button) ImageView play_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                        play_image.setVisibility(View.VISIBLE);
            }
        });
            itemView.setOnClickListener(view -> onItemListener.onClick(getAdapterPosition(), recentlyPlayed.getMitems().get(getAdapterPosition()).getTrack().getArtists().get(0).getName() +recentlyPlayed.getMitems().get(getAdapterPosition()).getTrack().getName()));
        }

    }

    public interface  OnItemListener{
        void onClick(int position, String title);
    }
}


