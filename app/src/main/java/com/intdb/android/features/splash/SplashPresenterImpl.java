package com.intdb.android.features.splash;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.intdb.android.app.presenter.impl.BasePresenterImpl;
import com.intdb.android.constants.AppConstants;

import javax.inject.Inject;

final class SplashPresenterImpl extends BasePresenterImpl<SplashView> implements SplashPresenter, Runnable {

    @NonNull
    private final SplashInteractor mInteractor;
    private Handler mHandler = new Handler();

    @Inject
    SplashPresenterImpl(@NonNull SplashInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if (viewCreated) {
            startLoading();
            doSplash();
        }
    }

    @Override
    public void onStop() {
        mHandler.removeCallbacks(this);
        super.onStop();
    }

    @Override
    public void startLoading() {
        assert mView != null;
        mView.showLoading();
    }

    @Override
    public void stopLoading(){
        assert mView != null;
        mView.hideLoading();
        mView.finishActivity();
    }

    @Override
    public void doSplash() {
        mHandler.postDelayed(this, AppConstants.SPLASH_TIME_MILLI_SECONDS);
    }

    @Override
    public void checkNetwork() {
        if(!mInteractor.isNetworkConnected()){
            assert mView != null;
            mView.showErrorWithMessage(mInteractor.getNoNetworkErrorText());
        }
    }

    @Override
    public void launchNextActivity() {
        assert mView != null;
        mView.launchNextActivity();
        stopLoading();
    }

    @Override
    public void run() {
        launchNextActivity();
    }
}