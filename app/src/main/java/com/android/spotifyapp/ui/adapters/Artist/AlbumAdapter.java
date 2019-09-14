package com.android.spotifyapp.ui.adapters.Artist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.android.spotifyapp.data.network.model.byId.Artistsalbum;
import com.android.spotifyapp.databinding.ArtistAlbumLayoutBinding;

import static com.android.spotifyapp.utils.TAGS.OutOfBoundsTag;

public class AlbumAdapter extends PagerAdapter {
    private Artistsalbum artistsAlbum;
    private Context context;
    private ArtistAlbumLayoutBinding artistAlbumLayoutBinding;

    public AlbumAdapter(Context context) {
        this.context = context;
        artistsAlbum = new Artistsalbum();
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setArtistsAlbum(Artistsalbum artistsAlbum) {
        this.artistsAlbum = artistsAlbum;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        artistAlbumLayoutBinding = ArtistAlbumLayoutBinding.inflate(LayoutInflater.from(context), container, false);
        final Artistsalbum.Items item = artistsAlbum.getItems().get(position);
        artistAlbumLayoutBinding.setArtistAlbum(item);

        try {
            artistAlbumLayoutBinding.setImageUrl(artistsAlbum.getItems().get(position).getImages().get(0).getUrl());
        } catch (IndexOutOfBoundsException exception) {
            Log.d(OutOfBoundsTag, "instantiateItem: " + exception.getMessage());
        }



                container.addView(artistAlbumLayoutBinding.getRoot());
                return artistAlbumLayoutBinding.getRoot();

    }

    @Override
    public int getCount() {
                if(artistsAlbum.getItems() != null) {
            return artistsAlbum.getItems().size();
        }
        return 0;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}
