package com.android.spotifyapp.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import com.android.spotifyapp.R;
import com.android.spotifyapp.data.ViewModels.YoutubePlayerViewmodel;
import com.android.spotifyapp.data.network.model.YoutubeVideos;
import com.android.spotifyapp.di.components.DaggerYoutubeComponent;
import com.android.spotifyapp.di.components.YoutubeComponent;
import com.android.spotifyapp.di.modules.ViewModelsModule;
import com.android.spotifyapp.di.modules.YoutubeModule;
import com.android.spotifyapp.ui.GlobalState.CurrentSongState;
import com.android.spotifyapp.ui.listeners.OnSwipeListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import org.jetbrains.annotations.NotNull;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class YoutubeFragment extends Fragment  {
    View view;
    @BindView(R.id.play_button) CardView play_button;
    @BindView(R.id.youtube_player_view) YouTubePlayerView youTubePlayerView;
    @BindView(R.id.song_progress_bar) ProgressBar progressBar;
    @BindView(R.id.player_icon) ImageView player_icon;
    @BindView(R.id.song_loading) RelativeLayout relativeLayout;
    private FragmentManager fragmentManager;
    @Inject YoutubePlayerViewmodel youtubePlayerViewmodel;
    private CurrentSongState currentSongState;
    private YouTubePlayer youtubePlayerInstance;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.youtube_fragment, container, false);
        ButterKnife.bind(this, view);
        YoutubeComponent component = DaggerYoutubeComponent.builder().youtubeModule(new YoutubeModule()).viewModelsModule(new ViewModelsModule(this)).build();
        component.injectFragment(this);
        fragmentManager = getFragmentManager();
        currentSongState = CurrentSongState.getInstance();
        youtubePlayerViewmodel.getVideos("id", 5, currentSongState.getTitle(), "video").observe(getViewLifecycleOwner(), new Observer<YoutubeVideos>() {
            @Override
            public void onChanged(YoutubeVideos youtubeVideos) {
                Log.d("YoutubeMessage", "onChanged: " + youtubeVideos.getItems().get(0).getId().getVideoId());
                currentSongState.setId(youtubeVideos.getItems().get(0).getId().getVideoId());
            }
        });

        container.setOnTouchListener((view, motionEvent) -> OnSwipeListener.onSwipe(view, motionEvent, fragmentManager, YoutubeFragment.this));
        boolean b = youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {

            @Override
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youtubePlayerInstance = youTubePlayer;
                if(currentSongState.getId() != null) {
                    Log.d("Clickeded", "onReady: " + currentSongState.getId());
                    youTubePlayer.loadVideo(currentSongState.getId(), 0f);
                    Log.d("Clickeded", "onReady: ");
                }

            }


            @Override
            public void onCurrentSecond(@NotNull YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);
                progressBar.setProgress((int) (100 * second / currentSongState.getSong_duration()));
            }

            @Override
            public void onVideoDuration(@NotNull YouTubePlayer youTubePlayer, float duration) {
                super.onVideoDuration(youTubePlayer, duration);
                currentSongState.setSong_duration((int) duration);
            }

            @Override
            public void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState state) {
                super.onStateChange(youTubePlayer, state);
                currentSongState.setState(state);
                switch(state) {
                    case PAUSED:
                    case ENDED:
                        player_icon.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        break;
                    case BUFFERING:
                        player_icon.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        relativeLayout.setVisibility(View.VISIBLE);
                        break;
                    case PLAYING:
                        player_icon.setImageResource(R.drawable.ic_pause_black_24dp);
                        relativeLayout.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPlaybackQualityChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackQuality playbackQuality) {
                super.onPlaybackQualityChange(youTubePlayer, playbackQuality);
            }
        });
        return view;
    }

    @OnClick(R.id.play_button) void play() {
        if(youtubePlayerInstance != null && currentSongState.getState() != null) {
            if (currentSongState.getState() == PlayerConstants.PlayerState.PLAYING)
                youtubePlayerInstance.pause();
            else {
                youtubePlayerInstance.play();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        youTubePlayerView.release();
    }
}
