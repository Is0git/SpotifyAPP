package com.android.spotifyapp.utils;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.android.spotifyapp.databinding.ArtistLayoutBinding;

import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_followers;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_id;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_image_url;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_name;

public class ArgumentsHelper {
    public static void getBundleArguments(Fragment fragment, ViewDataBinding binding) {
        ((ArtistLayoutBinding) binding).setArtistName(fragment.getArguments() != null ? fragment.getArguments().getString(artist_name) : null);
        ((ArtistLayoutBinding)binding).setArtistFollowers(fragment.getArguments().getInt(artist_followers));
        ((ArtistLayoutBinding)binding).setArtistImageUrl(fragment.getArguments().getString(artist_image_url));
        ((ArtistLayoutBinding) binding).setArtistId(fragment.getArguments().getString(artist_id));

    }
}
