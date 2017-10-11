package com.intdb.android.app.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.intdb.android.app.IntDbApp;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {

    @NonNull
    private final IntDbApp mApp;

    public AppModule(@NonNull IntDbApp app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public IntDbApp provideApp() {
        return mApp;
    }

}
