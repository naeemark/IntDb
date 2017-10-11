package com.intdb.android.features.catalog.presenter;

import com.intdb.android.app.presenter.BasePresenter;
import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.view.CatalogView;

public interface CatalogPresenter extends BasePresenter<CatalogView>, CatalogInteractor.OnFetchDataListener {

    void loadMoviesPage(int pageNumber);
}