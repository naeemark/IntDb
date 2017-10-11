package com.intdb.android.features.catalog.interactor;

import com.intdb.android.app.interactor.BaseInteractor;
import com.intdb.android.model.Movie;

import java.util.List;

public interface CatalogInteractor extends BaseInteractor {

    boolean isNetworkConnected();

    void fetchMoviesPage(int pageNumber, OnFetchDataListener onFetchDataListener);

    interface OnFetchDataListener {

        void onStart();

        void onDataResponse(List<Movie> list);

        void onFailure(String message);

        void onComplete();
    }
}