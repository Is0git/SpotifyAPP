package com.android.spotifyapp.di.modules;

import android.content.Context;

import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;
import com.android.spotifyapp.ui.adapters.Artist.AlbumAdapter;
import com.android.spotifyapp.ui.adapters.Artist.RelatedArtistsAdapter;
import com.android.spotifyapp.ui.adapters.Artist.TopSongsAdapter;
import com.android.spotifyapp.ui.adapters.Home.HomeHorizontal;
import com.android.spotifyapp.ui.adapters.Home.MyPlaylistsAdapter;
import com.android.spotifyapp.ui.adapters.Home.RecommendedAdapter;
import com.android.spotifyapp.ui.adapters.Home.SliderAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AdaptersModule {
    @Provides
    @HomeFragmentScope
    public HomeHorizontal homeHorizontal() {
        return new HomeHorizontal();
    }

    @Provides
    @HomeFragmentScope
    public MyPlaylistsAdapter myPlaylistsAdapter() {
        return new MyPlaylistsAdapter();
    }

    @Provides
    @HomeFragmentScope
    public RecommendedAdapter recommendedAdapter() {
        return new RecommendedAdapter();
    }

    @Provides
    @HomeFragmentScope
    public SliderAdapter sliderAdapter() {return new SliderAdapter(); }

    @Provides
    @ArtistFragmentScope
    public TopSongsAdapter topSongsAdapter() {return new TopSongsAdapter();}

    @Provides
    @ArtistFragmentScope
    public RelatedArtistsAdapter relatedArtistsAdapter() {return new RelatedArtistsAdapter();}

    @Provides
    @ArtistFragmentScope
    public AlbumAdapter albumAdapter(@ActivityContext  Context context) {
        return new AlbumAdapter(context);
    }

}
