package com.android.spotifyapp.ui.adapters.homeadapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.RecentlyPlayed;
import com.android.spotifyapp.databinding.HomeHorizontalListBinding;


public class HomeHorizontal extends RecyclerView.Adapter<HomeHorizontal.MyViewHolder> {
    private RecentlyPlayed recentlyPlayed;
    private android.widget.ProgressBar progressBar;
    private OnItemListener onItemListener;
    public HomeHorizontal() {
        recentlyPlayed = new RecentlyPlayed();
    }

    @NonNull
    @Override
    public HomeHorizontal.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeHorizontalListBinding homeHorizontalListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.home_horizontal_list, parent, false);
        return new MyViewHolder(homeHorizontalListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeHorizontal.MyViewHolder holder, int position) {
        holder.homeHorizontalListBinding.setPosition(position);
        holder.homeHorizontalListBinding.setRecentlyPlayed(recentlyPlayed);
        holder.homeHorizontalListBinding.setOnClickInterface(onItemListener);
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
        HomeHorizontalListBinding homeHorizontalListBinding;

        public MyViewHolder(@NonNull HomeHorizontalListBinding homeHorizontalListBinding) {
            super(homeHorizontalListBinding.getRoot());
            this.homeHorizontalListBinding = homeHorizontalListBinding;



        }

    }

    public interface  OnItemListener{
        void onClick(int position, String title);
    }
}


