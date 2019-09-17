package com.android.spotifyapp.ui.lifecycleObservers;

import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ArtistLifeCycle implements LifecycleObserver {
        private NavController navController;
        private View view;

    public ArtistLifeCycle(NavController navController, View view) {
        this.navController = navController;
        this.view = view;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onCreated() {

        navController = Navigation.findNavController(view);

    }
}
