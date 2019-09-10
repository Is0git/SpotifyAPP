package com.android.spotifyapp.di.modules;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class RXModule {
    @Provides
    public CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }
}
