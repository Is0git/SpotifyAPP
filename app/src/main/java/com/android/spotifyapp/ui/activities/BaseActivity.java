package com.android.spotifyapp.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.android.spotifyapp.R;
import com.android.spotifyapp.data.viewModelPackage.homeViewModels.BaseViewModel;
import com.android.spotifyapp.databinding.ActionbarBinding;
import com.android.spotifyapp.databinding.ActivityBaseActivityBinding;
import com.android.spotifyapp.di.components.DaggerBaseComponent;
import com.android.spotifyapp.di.modules.ActivityBindingModule;
import com.android.spotifyapp.di.modules.ActivityViewModelModule;
import com.android.spotifyapp.di.modules.ContextModule;
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
                .activityBindingModule(new ActivityBindingModule())
                .contextModule(new ContextModule(this))
                .build().injectActivity(this);

        //ActionBar settings
        SetActionBar(this, actionbarBinding.getRoot());

        viewModel.getUser().observe(this, user -> UserUtil.userSave(user, BaseActivity.this));
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupWithNavController(activityBaseActivityBinding.bottomNav, navController);





    }
}
