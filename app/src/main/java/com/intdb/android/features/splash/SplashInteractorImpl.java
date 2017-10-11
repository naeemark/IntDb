package com.intdb.android.features.splash;

import android.content.Context;

import com.intdb.android.R;
import com.intdb.android.app.interactor.impl.BaseInteractorImpl;
import com.intdb.android.utils.NetworkUtils;
import com.intdb.android.utils.PreferencesUtils;

import javax.inject.Inject;

public final class SplashInteractorImpl extends BaseInteractorImpl implements SplashInteractor {

    private final Context mContext;

    private final PreferencesUtils mPreferencesUtils;

    @Inject
    public SplashInteractorImpl(Context context, PreferencesUtils preferencesUtils) {
        this.mContext = context;
        this.mPreferencesUtils = preferencesUtils;
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetAvailable(mContext);
    }

    @Override
    public String getNoNetworkErrorText() {
        return mContext.getString(R.string.error_no_network);
    }

}