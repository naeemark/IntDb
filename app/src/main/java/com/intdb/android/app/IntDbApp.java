package com.intdb.android.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.intdb.android.BuildConfig;
import com.intdb.android.app.injection.AppComponent;
import com.intdb.android.app.injection.AppModule;
import com.intdb.android.app.injection.DaggerAppComponent;
import com.intdb.android.app.injection.NetworkApiModule;

import timber.log.Timber;

public final class IntDbApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {

        super.onCreate();

        // Shows logs only in Debug Build type
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkApiModule(new NetworkApiModule(BuildConfig.BASE_URL))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}