package com.intdb.android.features.splash;

import android.support.annotation.UiThread;

import com.intdb.android.app.view.BaseView;

@UiThread
public interface SplashView extends BaseView {

    void showLoading();

    void hideLoading();

    void finishActivity();

    void launchNextActivity();

    void showNetworkError();

}