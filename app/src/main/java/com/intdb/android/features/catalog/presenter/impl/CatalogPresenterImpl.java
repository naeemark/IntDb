package com.intdb.android.features.catalog.presenter.impl;

import android.support.annotation.NonNull;

import com.intdb.android.app.presenter.impl.BasePresenterImpl;
import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.view.CatalogView;

import javax.inject.Inject;

public final class CatalogPresenterImpl extends BasePresenterImpl<CatalogView> implements CatalogPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final CatalogInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public CatalogPresenterImpl(@NonNull CatalogInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}