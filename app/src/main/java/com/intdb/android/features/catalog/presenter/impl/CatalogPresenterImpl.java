package com.intdb.android.features.catalog.presenter.impl;

import android.support.annotation.NonNull;

import com.intdb.android.app.presenter.impl.BasePresenterImpl;
import com.intdb.android.constants.SortBy;
import com.intdb.android.features.catalog.CarousalPresenter;
import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.view.CarousalModule;
import com.intdb.android.features.catalog.view.CatalogView;
import com.intdb.android.model.Movie;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public final class CatalogPresenterImpl extends BasePresenterImpl<CatalogView> implements CatalogPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final CatalogInteractor mInteractor;

    @Inject
    public CatalogPresenterImpl(@NonNull CatalogInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if (viewCreated) {
            assert mView != null;
            mView.loadList();
        }

    }

    @Override
    public void loadMoviesPage(int pageNumber) {
        if (!mInteractor.isNetworkConnected()) {
            assert mView != null;
            mView.showNetworkError();
        } else {
            mInteractor.fetchCarousalPage(SortBy.POPULARITY, pageNumber, this);
        }
    }

    @Override
    public void loadCarousalModules(CarousalModule... carousalModules) {
        for (CarousalModule carousalModule : carousalModules) {
            new CarousalPresenter(carousalModule, mInteractor);
        }
    }

    @Override
    public void onStart() {
        assert mView != null;
        mView.showLoading();
    }

    @Override
    public void onDataResponse(List<Movie> list) {
        assert mView != null;
        mView.loadList(list);
    }

    @Override
    public void onFailure(String message) {
        Timber.d(message);
        assert mView != null;
        mView.hideLoading();
        mView.showErrorLoading();
    }

    @Override
    public void onComplete() {
        assert mView != null;
        mView.hideLoading();
    }
}