package com.android.spotifyapp.di.modules;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.spotifyapp.R;
import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.qualifiers.ArtistsAlbumsRecyclerViewQualifier;
import com.android.spotifyapp.di.qualifiers.GridLayoutQualifier;
import com.android.spotifyapp.di.qualifiers.MyPlaylistListQualifier;
import com.android.spotifyapp.di.qualifiers.RecentlyPlayedQualifier;
import com.android.spotifyapp.di.qualifiers.RecommendedListQualifier;
import com.android.spotifyapp.di.qualifiers.RelatedArtistsRecyclerViewQualifier;
import com.android.spotifyapp.di.qualifiers.TopSongRecyclerViewQualifier;
import com.android.spotifyapp.di.qualifiers.VerticalLayoutQualifier;
import com.android.spotifyapp.di.scopes.ArtistFragmentScope;
import com.android.spotifyapp.di.scopes.HomeFragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RecyclerViewModule {
    private View view;

    public RecyclerViewModule(View view) {
        this.view = view;
    }

    @Provides
    RecyclerView.LayoutManager layoutManager() {
        return new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
    }

    @Provides
    @VerticalLayoutQualifier
    RecyclerView.LayoutManager verticalLayoutManager() {
        return new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
    }

    @Provides
    @GridLayoutQualifier
    RecyclerView.LayoutManager gridLayout(@ActivityContext Context context) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        gridLayoutManager.setReverseLayout(false);
        return gridLayoutManager;
    }


    @Provides
    @RecentlyPlayedQualifier
    @HomeFragmentScope
    RecyclerView recyclerView(RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.recently_played_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return recyclerView;
    }
    @Provides
    @MyPlaylistListQualifier
    @HomeFragmentScope
    RecyclerView MyPlaylistRecyclerView(RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.MyPlaylists_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return recyclerView;
    }

    @Provides
    @RecommendedListQualifier
    @HomeFragmentScope
    RecyclerView RecommendedRecyclerView(RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.recommended_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return recyclerView;
    }

    @Provides
    @TopSongRecyclerViewQualifier
    @ArtistFragmentScope
    RecyclerView TopSongRecyclerView(@VerticalLayoutQualifier RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.top_tracks_recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return recyclerView;
    }

    @Provides
    @RelatedArtistsRecyclerViewQualifier
    @ArtistFragmentScope
    RecyclerView RelatedArtistRecyclerView(RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.related_artists_recylerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return recyclerView;
    }

    @Provides
    @ArtistFragmentScope
    ViewPager viewPager() {
        return view.findViewById(R.id.albums_pager);
    }

}
