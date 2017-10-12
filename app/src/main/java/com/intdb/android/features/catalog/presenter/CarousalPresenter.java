package com.intdb.android.features.catalog.presenter;

import com.intdb.android.features.catalog.interactor.CatalogInteractor;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public interface CarousalPresenter extends CatalogInteractor.OnFetchDataListener {

    void fetchCarousalInitialPage();

    void fetchCarousalPage(int pageNumber);

    boolean isLoading();
}
