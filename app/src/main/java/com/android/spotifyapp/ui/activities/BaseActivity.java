package com.android.spotifyapp.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.android.spotifyapp.R;
import com.android.spotifyapp.data.viewModelPackage.BaseViewModel;
import com.android.spotifyapp.databinding.ActionbarBinding;
import com.android.spotifyapp.databinding.ActivityBaseActivityBinding;
import com.android.spotifyapp.di.components.DaggerBaseComponent;
import com.android.spotifyapp.di.modules.ActivityViewModelModule;
import com.android.spotifyapp.di.modules.ContextModule;
import com.android.spotifyapp.ui.fragment.HomeFragment;
import com.android.spotifyapp.ui.fragment.PlaylistFragment;
import com.android.spotifyapp.utils.UserUtil;
import javax.inject.Inject;

import static com.android.spotifyapp.utils.ActionBarSettings.SetActionBar;

public class BaseActivity extends AppCompatActivity {
    @Inject ActivityBaseActivityBinding activityBaseActivityBinding;
    @Inject BaseViewModel viewModel;
    @Inject ActionbarBinding actionbarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerBaseComponent.builder()
                .activityViewModelModule(new ActivityViewModelModule(this))
                .contextModule(new ContextModule(this))
                .build().injectActivity(this);

        //ActionBar settings
        SetActionBar(this, actionbarBinding.getRoot());

        viewModel.getUser().observe(this, user -> UserUtil.userSave(user, BaseActivity.this));

        //Fragments
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//        activityBaseActivityBinding.bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
//            Fragment current = null;
//            switch(menuItem.getItemId()) {
//                case R.id.home:
//                    current = new HomeFragment();
//                    break;
//                case R.id.my_playlists:
//                    current = new PlaylistFragment();
//
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, current).commitNowAllowingStateLoss();
//            return true;
//        });
    }

}
