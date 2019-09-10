package com.android.spotifyapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.android.spotifyapp.App;
import com.android.spotifyapp.R;
import com.android.spotifyapp.data.ViewModels.ArtistViewModel;
import com.android.spotifyapp.data.network.model.Recommendations;
import com.android.spotifyapp.di.components.ArtistComponent;
import com.android.spotifyapp.di.components.DaggerArtistComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.RecyclerViewModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.qualifiers.RelatedArtistsRecyclerViewQualifier;
import com.android.spotifyapp.di.qualifiers.TopSongRecyclerViewQualifier;
import com.android.spotifyapp.ui.adapters.Artist.AlbumAdapter;
import com.android.spotifyapp.ui.adapters.Artist.RelatedArtistsAdapter;
import com.android.spotifyapp.ui.adapters.Artist.TopSongsAdapter;
import com.android.spotifyapp.utils.CheckProgressBar;
import com.android.spotifyapp.utils.Piccaso;
import com.android.spotifyapp.utils.SharedPreferencesUtil;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.access_token;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_auth;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_user;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.user_country;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ArtistFragment extends Fragment {
    @BindView(R.id.artist_photo) ImageView artist_image;
    @BindView(R.id.artist_name_artist) TextView artist_name;
    @BindView(R.id.followers_artist) TextView followers;
    @BindView(R.id.album_items) TextView album_items;
    @BindView(R.id.artist_small_image) ImageView artist_small;
    @BindView(R.id.top_tracks_items) TextView top_track_items;
    @BindView(R.id.related_artists_items) TextView related_artists_item;
    @BindView(R.id.progress_bar_header) ProgressBar header_progress_bar;
    @BindView(R.id.progress_bar_viewPager) ProgressBar parogress_bar_viewPager;
    @BindView(R.id.progress_bar_small_image) ProgressBar progress_bar_small_image;
    @BindView(R.id.progress_bar_top_tracks) ProgressBar progress_bar_top_tracks;
    @BindView(R.id.progress_bar_related_artists) ProgressBar progress_bar_related_artists;



    @Inject ArtistViewModel viewModel;

    @Inject @TopSongRecyclerViewQualifier RecyclerView songs_recyclerView;
    @Inject @RelatedArtistsRecyclerViewQualifier RecyclerView related_artists_recyclerView;

    @Inject
    ViewPager album_pager;

    @Inject TopSongsAdapter topSongsAdapter;
    @Inject RelatedArtistsAdapter relatedArtistsAdapter;

    @Inject
    AlbumAdapter albumAdapter;
    private Recommendations recommendations;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artist_layout, container, false);
        ButterKnife.bind(this, view);
        //Dagger
        ArtistComponent component = DaggerArtistComponent.builder()
                .appComponent(((App) Objects.requireNonNull(getActivity()).getApplicationContext()).getAppComponent())
                .viewModelsModule(new ViewModelsModule(this))
                .adaptersModule(new AdaptersModule())
                .contextModule(new ContextModule(this.getActivity()))
                .recyclerViewModule(new RecyclerViewModule(view))
                .build();
        component.injectFragment(this);

        //data from past fragment
        assert getArguments() != null;
        int position = getArguments().getInt("position");
        recommendations = (Recommendations) getArguments().getSerializable("recommendations");
        assert recommendations != null;
        artist_name.setText(recommendations.getMtracks().get(position).getMartists().get(0).getName());
        followers.setText(getString(R.string.followers, recommendations.getMtracks().get(position).getArtist().getFollowers().getTotal()));
        Piccaso.loadImageWithCallBack(recommendations.getMtracks().get(position).getArtist().getImages().get(0).getUrl(), position, getContext(), artist_image, header_progress_bar);

        //Artist's albums UI
        album_pager.setAdapter(albumAdapter);
        viewModel.getArtistsAlbum(recommendations.getMtracks().get(position).getMartists().get(0).getId()).observe(getViewLifecycleOwner(), artistsAlbum -> {
            album_items.setText(getString(R.string.items, artistsAlbum.getItems().size()));
            albumAdapter.setArtistsAlbum(artistsAlbum);
            Piccaso.loadImageWithCallBack(recommendations.getMtracks().get(position).getArtist().getImages().get(2).getUrl(), position, getContext(), artist_small, progress_bar_small_image);
            CheckProgressBar.checkViewPagerProgress(album_pager, parogress_bar_viewPager);
        });

        //Artist's top songs data
        songs_recyclerView.setAdapter(topSongsAdapter);
        viewModel.getTopTracks(recommendations.getMtracks().get(position).getMartists().get(0).getId(), SharedPreferencesUtil.getPreferences(shared_preferences_user, getActivity().getApplicationContext()).getString(user_country, "UK")).observe(getViewLifecycleOwner(), artistTopTracks -> {
            top_track_items.setText(getString(R.string.items, artistTopTracks.getTracks().size()));
            topSongsAdapter.updateData(artistTopTracks);
            CheckProgressBar.checkprogressBar(songs_recyclerView, progress_bar_top_tracks);
        });

        //Related artists
        related_artists_recyclerView.setAdapter(relatedArtistsAdapter);
        viewModel.getRelatedArtists(recommendations.getMtracks().get(position).getMartists().get(0).getId()).observe(getViewLifecycleOwner(), relatedArtists -> {
            related_artists_item.setText(getString(R.string.items, relatedArtists.getArtists().size()));
            relatedArtistsAdapter.setRelatedArtists(relatedArtists);
            CheckProgressBar.checkprogressBar(related_artists_recyclerView, progress_bar_related_artists);
        });
        return view;
    }
}
