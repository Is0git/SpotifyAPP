package com.android.spotifyapp.ui.adapters.libraryadapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.android.spotifyapp.ui.fragment.libraryfragments.AlbumsFragment;
import com.android.spotifyapp.ui.fragment.libraryfragments.SongsFragment;

public class LibraryViewPager extends FragmentPagerAdapter {
    public LibraryViewPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = new SongsFragment();
                break;
            case 1:
                fragment = new AlbumsFragment();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {

            case 0:
                return "SONGS";
            case 1:
                return "ALBUMS";

        }
        return null;
    }
}
