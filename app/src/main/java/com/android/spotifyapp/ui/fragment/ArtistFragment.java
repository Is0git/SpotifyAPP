package com.android.spotifyapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.spotifyapp.data.viewModelPackage.ArtistViewModel;
import com.android.spotifyapp.databinding.ArtistLayoutBinding;
import com.android.spotifyapp.di.components.DaggerArtistComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.Artist.AlbumAdapter;
import com.android.spotifyapp.ui.adapters.Artist.RelatedArtistsAdapter;
import com.android.spotifyapp.ui.adapters.Artist.TopSongsAdapter;
import com.android.spotifyapp.utils.SharedPreferencesUtil;
import java.util.Objects;
import javax.inject.Inject;
import static com.android.spotifyapp.utils.ArgumentsHelper.getBundleArguments;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_user;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.user_country;


public class ArtistFragment extends Fragment {
    @Inject ArtistViewModel viewModel;

    @Inject TopSongsAdapter topSongsAdapter;
    @Inject RelatedArtistsAdapter relatedArtistsAdapter;

    @Inject AlbumAdapter albumAdapter;

    @Inject ArtistLayoutBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Dagger
        DaggerArtistComponent.builder()
                .viewModelsModule(new ViewModelsModule(this))
                .adaptersModule(new AdaptersModule())
                .contextModule(new ContextModule(this.getActivity()))
                .fragmentBindingModule(new FragmentBindingModule(inflater, container))
                .build().injectFragment(this);

        //data from past fragment
        getBundleArguments(this, binding);

        //Artist's albums UI
        binding.albumsPager.setAdapter(albumAdapter);
        viewModel.getArtistsAlbum(binding.getArtistId()).observe(getViewLifecycleOwner(), artistsAlbum -> albumAdapter.setArtistsAlbum(artistsAlbum));

        //Artist's top songs data
        binding.topTracksRecyclerView.setAdapter(topSongsAdapter);
        viewModel.getTopTracks(binding.getArtistId(), SharedPreferencesUtil.getPreferences(shared_preferences_user, Objects.requireNonNull(getActivity()).getApplicationContext()).getString(user_country, "UK"))
                .observe(getViewLifecycleOwner(), artistTopTracks -> topSongsAdapter.updateData(artistTopTracks));

        //Related artists
        binding.relatedArtistsRecylerView.setAdapter(relatedArtistsAdapter);
        viewModel.getRelatedArtists(binding.getArtistId()).observe(getViewLifecycleOwner(), relatedArtists -> relatedArtistsAdapter.setRelatedArtists(relatedArtists));

        return binding.getRoot();
    }
}
