package com.android.spotifyapp.di.modules;

import android.content.Context;
import android.view.LayoutInflater;

import com.android.spotifyapp.data.viewModelPackage.homeViewModels.BaseViewModel;
import com.android.spotifyapp.databinding.ActionbarBinding;
import com.android.spotifyapp.di.qualifiers.ActivityContext;
import com.android.spotifyapp.di.scopes.BaseActivityScope;
import com.android.spotifyapp.ui.activities.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class CustomViewBindingMoudle {
    @Provides
    @BaseActivityScope
    public ActionbarBinding actionbarBinding(BaseViewModel baseViewModel, @ActivityContext Context context) {
        ActionbarBinding actionbarBinding = ActionbarBinding.inflate(LayoutInflater.from(context));
        actionbarBinding.setBaseViewmodel(baseViewModel);
        actionbarBinding.setLifecycleOwner((BaseActivity) context);
        return actionbarBinding;
    }
}
