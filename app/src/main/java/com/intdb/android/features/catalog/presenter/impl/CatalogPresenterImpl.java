package com.intdb.android.features.catalog.presenter.impl;

import android.support.annotation.NonNull;

import com.intdb.android.app.presenter.impl.BasePresenterImpl;
import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.view.impl.CarousalModuleImpl;
import com.intdb.android.features.catalog.view.CatalogView;

import javax.inject.Inject;

public final class CatalogPresenterImpl extends BasePresenterImpl<CatalogView> implements CatalogPresenter {
    @NonNull
    private final CatalogInteractor mInteractor;

    @Inject
    public CatalogPresenterImpl(@NonNull CatalogInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    @NonNull
    public CatalogInteractor getInteractor() {
        return mInteractor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if (viewCreated) {
            assert mView != null;
            mView.loadCarousals();
        }
    }

    @Override
    public void loadCarousalModules(CarousalModuleImpl... carousalModuleImpls) {
        for (CarousalModuleImpl carousalModuleImpl : carousalModuleImpls) {
            new CarousalPresenterImpl(carousalModuleImpl, mInteractor).fetchCarousalInitialPage();
        }
    }
}