package com.intdb.android.features.splash;

import com.intdb.android.app.presenter.BasePresenter;

public interface SplashPresenter extends BasePresenter<SplashView> {

    void startLoading();

    void stopLoading();

    void doSplash();

    void checkNetwork();

    void launchNextActivity();
}