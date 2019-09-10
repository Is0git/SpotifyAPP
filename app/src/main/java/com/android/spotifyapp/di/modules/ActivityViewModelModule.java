package com.android.spotifyapp.di.modules;

import androidx.lifecycle.ViewModelProviders;

import com.android.spotifyapp.data.ViewModels.BaseViewModel;
import com.android.spotifyapp.ui.activities.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityViewModelModule {

    private BaseActivity baseActivity;

    public ActivityViewModelModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    public BaseViewModel baseViewModel() {
        return ViewModelProviders.of(this.baseActivity).get(BaseViewModel.class);
    }
}
