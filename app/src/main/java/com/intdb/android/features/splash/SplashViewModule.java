package com.intdb.android.features.splash;

import android.content.Context;
import android.support.annotation.NonNull;

import com.intdb.android.app.presenter.loader.PresenterFactory;
import com.intdb.android.utils.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

@Module
public final class SplashViewModule {

    @Provides
    public SplashInteractor provideInteractor(Context context, PreferencesUtils preferencesUtils) {
        return new SplashInteractorImpl(context, preferencesUtils);
    }

    @Provides
    public PresenterFactory<SplashPresenter> providePresenterFactory(@NonNull final SplashInteractor interactor) {
        return new PresenterFactory<SplashPresenter>() {
            @NonNull
            @Override
            public SplashPresenter create() {
                return new SplashPresenterImpl(interactor);
            }
        };
    }
}
