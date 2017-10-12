package com.intdb.android.features.catalog.presenter.impl;

import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.presenter.CarousalPresenter;
import com.intdb.android.features.catalog.view.CarousalModule;
import com.intdb.android.model.Movie;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public class CarousalPresenterImpl implements CarousalPresenter {

    private static final int FIRST_PAGE = 1;
    private final CarousalModule mCarousalModule;
    private final CatalogInteractor mInteractor;
    private boolean isLoading;

    public CarousalPresenterImpl(CarousalModule carousalModule, CatalogInteractor interactor) {

        mCarousalModule = carousalModule;
        mInteractor = interactor;
        mCarousalModule.setPresenter(this);

    }

    /**
     * is called when API starts
     */
    @Override
    public void onStart() {
        isLoading = true;
        mCarousalModule.showLoading();
    }

    /**
     * called by listener when API get data successfully
     * @param list
     */
    @Override
    public void onDataResponse(List<Movie> list) {
        mCarousalModule.loadList(list);
    }

    /**
     * Handles API failure
     * @param message
     */
    @Override
    public void onFailure(String message) {
        isLoading = false;
        mCarousalModule.hideLoading();
    }

    /**
     * Rx-Android onComplete implemetation
     */
    @Override
    public void onComplete() {
        isLoading = false;
        mCarousalModule.hideLoading();
    }

    /**
     * Without pageNumber, takes it for intialPage
     */
    @Override
    public void fetchCarousalInitialPage() {
        fetchCarousalPage(FIRST_PAGE);
    }

    /**
     * Fetches a specific page according to the provided parameter
     * @param pageNumber
     */
    @Override
    public void fetchCarousalPage(int pageNumber) {
        if (!mInteractor.isNetworkConnected()) {
            mCarousalModule.showNetworkError();
        } else {
            mInteractor.fetchCarousalPage(mCarousalModule.getSortBy(), pageNumber, this);
        }
    }

    /**
     * Exposes status of loading
     * @return
     */
    @Override
    public boolean isLoading() {
        return isLoading;
    }
}
