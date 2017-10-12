package com.intdb.android.features.catalog;

import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.view.CarousalModule;
import com.intdb.android.model.Movie;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public class CarousalPresenter implements CatalogInteractor.OnFetchDataListener {

    private final CarousalModule mCarousalModule;
    private final CatalogInteractor mInteractor;

    public CarousalPresenter(CarousalModule carousalModule, CatalogInteractor interactor) {

        mCarousalModule = carousalModule;
        mInteractor = interactor;

        interactor.fetchCarousalPage(carousalModule.getSortBy(), 1, this);
    }

    @Override
    public void onStart() {
        mCarousalModule.showLoading();
    }

    @Override
    public void onDataResponse(List<Movie> list) {
        mCarousalModule.loadList(list);
    }

    @Override
    public void onFailure(String message) {
        mCarousalModule.hideLoading();
    }

    @Override
    public void onComplete() {
        mCarousalModule.hideLoading();
    }
}
