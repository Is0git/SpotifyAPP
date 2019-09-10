package com.android.spotifyapp.ui.adapters.Artist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.byId.ArtistsAlbum;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends PagerAdapter {
    private ArtistsAlbum artistsAlbum;
    private View view;
    private Context context;

    public AlbumAdapter(Context context) {
        this.context = context;
        artistsAlbum = new ArtistsAlbum();
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setArtistsAlbum(ArtistsAlbum artistsAlbum) {
        this.artistsAlbum = artistsAlbum;
        notifyDataSetChanged();
    }
    @BindView(R.id.release_date_precision) TextView release_date_precision;
    @BindView(R.id.artist_album_image) ImageView artist_album_image;
    @BindView(R.id.songs_number_text) TextView songs_number_text;
    @BindView(R.id.release_date_text) TextView release_date_text;
    @BindView(R.id.artist_number_text) TextView artist_number_text;
    @BindView(R.id.album_image_type) TextView album_image_type_text;
    @BindView(R.id.artist_album_name_text) TextView album_name;
    @NonNull
    @Override

    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup layout = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.artist_album_layout, container, false);
        ButterKnife.bind(this, layout);

                if(artistsAlbum.getItems() != null) {
            if(artistsAlbum.getItems().get(position).getArtists() != null) {
                Picasso.with(context)
                        .load(artistsAlbum.getItems().get(position).getImages().get(0).getUrl())
                        .fit()
                        .into(artist_album_image);
            }
            songs_number_text.setText(String.valueOf(artistsAlbum.getItems().get(position).getTotal_tracks()));
            release_date_text.setText(artistsAlbum.getItems().get(position).getRelease_date());
            artist_number_text.setText(String.valueOf(artistsAlbum.getItems().get(position).getArtists().size()));
            album_image_type_text.setText(artistsAlbum.getItems().get(position).getAlbum_type());
            album_name.setText(artistsAlbum.getItems().get(position).getName());
            release_date_precision.setText(artistsAlbum.getItems().get(position).getRelease_date_precision().toUpperCase());
        }
                container.addView(layout);
                return layout;

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
