package com.intdb.android.features.catalog.presenter;

import android.support.annotation.NonNull;

import com.intdb.android.app.presenter.BasePresenter;
import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.view.CarousalModule;
import com.intdb.android.features.catalog.view.CatalogView;

public interface CatalogPresenter extends BasePresenter<CatalogView> {

    @NonNull
    CatalogInteractor getInteractor();

    void loadCarousalModules(CarousalModule ... carousalModules);

    void reloadCarousalModules(CarousalModule... carousalModules);
}