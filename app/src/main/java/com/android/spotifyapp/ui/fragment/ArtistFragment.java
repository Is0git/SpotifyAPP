package com.android.spotifyapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.spotifyapp.R;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.ArtistViewModel;
import com.android.spotifyapp.databinding.ArtistLayoutBinding;
import com.android.spotifyapp.di.components.DaggerArtistComponent;
import com.android.spotifyapp.di.modules.AdaptersModule;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.di.modules.FragmentBindingModule;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.ui.adapters.Artist.AlbumAdapter;
import com.android.spotifyapp.ui.adapters.Artist.RelatedArtistsAdapter;
import com.android.spotifyapp.ui.adapters.Artist.TopSongsAdapter;
import com.android.spotifyapp.ui.adapters.homeadapters.RecommendedAdapter;
import com.android.spotifyapp.ui.listeners.ListenersInterface;
import com.android.spotifyapp.utils.SharedPreferencesUtil;
import com.android.spotifyapp.utils.onClickHandler;

import java.util.Objects;

import javax.inject.Inject;

import static com.android.spotifyapp.utils.ArgumentsHelper.getBundleArguments;
import static com.android.spotifyapp.utils.Contracts.BundleKeys.artist_id;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_user;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.user_country;


public class ArtistFragment extends Fragment implements RecommendedAdapter.onItemListener, ListenersInterface {
    @Inject ArtistViewModel viewModel;

    @Inject TopSongsAdapter topSongsAdapter;
    @Inject RelatedArtistsAdapter relatedArtistsAdapter;

    @Inject AlbumAdapter albumAdapter;

    @Inject ArtistLayoutBinding binding;
    private NavController navController;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Dagger
        DaggerArtistComponent.builder()
                .viewModelsModule(new ViewModelsModule(this))
                .adaptersModule(new AdaptersModule())
                .contextModule(new ContextModule(this.getActivity()))
                .fragmentBindingModule(new FragmentBindingModule(inflater, ArtistFragment.this))
                .build().injectFragment(this);
        binding.setListener(this);
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
        relatedArtistsAdapter.setOnItemListener(this);
        viewModel.getRelatedArtists(binding.getArtistId()).observe(getViewLifecycleOwner(), relatedArtists -> relatedArtistsAdapter.setRelatedArtists(relatedArtists));

        binding.invalidateAll();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

    }

    @Override
    public void onClick(String name, int followers, String image_url, String id) {
        onClickHandler.albumClickNavigate(name, followers, image_url, id, navController);
    }

    @Override
    public void recentlyPlayedViewAllClick(View view) {
        Bundle bundle = new Bundle();
        if (getArguments() != null) {
            bundle.putString("id", getArguments().getString(artist_id));
        }
        navController.navigate(R.id.action_artistFragment_to_albumFullFragment, bundle);
    }
}
