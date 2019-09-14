package com.android.spotifyapp.ui.adapters.homeadapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.spotifyapp.data.network.model.UserTopTracks;
import com.android.spotifyapp.databinding.SliderLayoutBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

import static com.android.spotifyapp.utils.TAGS.OutOfBoundsTag;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {
    private UserTopTracks userTopTracks;

    public SliderAdapter() {
        userTopTracks = new UserTopTracks();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        SliderLayoutBinding sliderLayoutBinding = SliderLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SliderAdapterVH(sliderLayoutBinding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
                final UserTopTracks.Items item = userTopTracks.getItems().get(position);
                viewHolder.sliderLayoutBinding.setUserTopTrack(item);
                    try {
                        viewHolder.sliderLayoutBinding.setImageUrl(userTopTracks.getItems().get(position).getAlbum().getImages().get(0).getUrl());

                    } catch (IndexOutOfBoundsException exception) {
                        Log.d(OutOfBoundsTag, "onBindViewHolder: " + exception.getMessage());
                    }

        }


    @Override
    public int getCount() {
        if(userTopTracks.getItems() != null) {
            return userTopTracks.getItems().size();
        }
        return 0;
    }
    public void UpdateData(UserTopTracks userTopTracks) {
        this.userTopTracks = userTopTracks;
        notifyDataSetChanged();
    }
    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        SliderLayoutBinding sliderLayoutBinding;

        public SliderAdapterVH(SliderLayoutBinding sliderLayoutBinding) {
            super(sliderLayoutBinding.getRoot());
            this.sliderLayoutBinding = sliderLayoutBinding;


        }
    }
}
