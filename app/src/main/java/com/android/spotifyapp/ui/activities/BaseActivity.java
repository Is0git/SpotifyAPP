package com.android.spotifyapp.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.spotifyapp.App;
import com.android.spotifyapp.R;
import com.android.spotifyapp.data.network.model.User;
import com.android.spotifyapp.data.viewModelPackage.BaseViewModel;
import com.android.spotifyapp.di.components.BaseComponent;
import com.android.spotifyapp.di.components.DaggerBaseComponent;
import com.android.spotifyapp.di.modules.ActivityViewModelModule;
import com.android.spotifyapp.ui.fragment.HomeFragment;
import com.android.spotifyapp.ui.fragment.PlaylistFragment;
import com.android.spotifyapp.ui.fragment.YoutubeFragment;
import com.android.spotifyapp.utils.SharedPreferencesUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.spotifyapp.utils.ActionBarSettings.SetActionBar;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.shared_preferences_user;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.user_country;
import static com.android.spotifyapp.utils.Contracts.SharedPreferencesContract.user_id;

public class BaseActivity extends AppCompatActivity {
    @BindView(R.id.bottom_nav) BottomNavigationView bottomNavigationView;
    ImageView user_image;
    @Inject BaseViewModel viewModel;
    View actionbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_activity);
        ButterKnife.bind(this);


        //ActionBar
        actionbar  = LayoutInflater.from(this).inflate(R.layout.actionbar, null);
        user_image = actionbar.findViewById(R.id.action_user);
        SetActionBar(this, actionbar);

        //Dagger
        BaseComponent component = DaggerBaseComponent.builder().appComponent(((App) getApplicationContext()).getAppComponent())
                .activityViewModelModule(new ActivityViewModelModule(this))
                .build();
        component.injectActivity(this);




        viewModel.getUser().observe(this, user -> {
            Picasso.with(getApplicationContext())
                    .load(user.getMimages().get(0).getUrl())
                    .fit().into(user_image);
            saveUser(user);
        });

        //Fragments
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment current = null;
            switch(menuItem.getItemId()) {
                case R.id.home:
                    current = new HomeFragment();
                    break;
                case R.id.my_playlists:
                    current = new PlaylistFragment();

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, current).commitNowAllowingStateLoss();
            return true;
        });
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = SharedPreferencesUtil.getPreferences(shared_preferences_user, getApplicationContext()).edit();
        editor.putString(user_id, user.getId());
        editor.putString(user_country, user.getCountry());
        editor.apply();
    }

    public void startPlayer() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.youtube_fragment, new YoutubeFragment()).commit();
    }

}
