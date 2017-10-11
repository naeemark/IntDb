package com.intdb.android.features.catalog.interactor.impl;

import android.content.Context;

import com.intdb.android.app.interactor.impl.BaseInteractorImpl;
import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.model.Movie;
import com.intdb.android.model.response.MoviesListResponse;
import com.intdb.android.utils.NetworkUtils;
import com.intdb.android.utils.parser.ModelParser;
import com.intdb.android.webapi.MoviesApiService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

public final class CatalogInteractorImpl extends BaseInteractorImpl implements CatalogInteractor {

    private final Context mContext;
    private final MoviesApiService mApiService;
    private final ModelParser mParser;

    @Inject
    public CatalogInteractorImpl(Context context, MoviesApiService apiService, ModelParser parser) {

        mContext = context;
        mApiService = apiService;
        mParser = parser;
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetAvailable(mContext);
    }

    @Override
    public void fetchMoviesPage(int pageNumber, final OnFetchDataListener listener) {
        listener.onStart();

        Observable<MoviesListResponse> observable = mApiService.getMovies(String.valueOf(pageNumber));

        subscribe(observable, new Observer<MoviesListResponse>() {

            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e.getMessage());
                listener.onComplete();
            }

            @Override
            public void onNext(MoviesListResponse response) {

                List<Movie> movies = mParser.mapMovies(response);
                listener.onDataResponse(movies);
            }
        });
    }
}