package com.android.spotifyapp.di.modules;

import android.content.Context;

import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.AlbumFullFragmentScope;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.di.scopes.FullPlaylistScope;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;
import com.android.spotifyapp.di.scopes.MyPlaylistFullScope;
import com.android.spotifyapp.di.scopes.PlaylistTracksScope;
import com.android.spotifyapp.di.scopes.RecentlyPlayedScope;
import com.android.spotifyapp.ui.adapters.Artist.AlbumAdapter;
import com.android.spotifyapp.ui.adapters.Artist.ArtistFull.AlbumFullAdapter;
import com.android.spotifyapp.ui.adapters.Artist.ArtistFull.MyPlaylistFullAdapter;
import com.android.spotifyapp.ui.adapters.Artist.RelatedArtistsAdapter;
import com.android.spotifyapp.ui.adapters.Artist.TopSongsAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.HomeFull.MyPlaylistTracks.PlaylistTracksAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.HomeFull.RecentlyPlayedFull;
import com.android.spotifyapp.ui.adapters.homeadapters.HomeHorizontal;
import com.android.spotifyapp.ui.adapters.homeadapters.MyPlaylistsAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.RecommendedAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.SliderAdapter;

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
    @Provides
    @RecentlyPlayedScope
    public RecentlyPlayedFull recentlyPlayedFull() {
        return new RecentlyPlayedFull();
    }

    @Provides
    @FullPlaylistScope
    public MyPlaylistFullAdapter myPlaylistFullAdapter() {
        return new MyPlaylistFullAdapter();
    }

    @Provides
    @AlbumFullFragmentScope
    public AlbumFullAdapter albumFullAdapter() {return new AlbumFullAdapter();}

    @Provides
    @PlaylistTracksScope
    public PlaylistTracksAdapter playlistTracksAdapter() {return new PlaylistTracksAdapter();}

}
