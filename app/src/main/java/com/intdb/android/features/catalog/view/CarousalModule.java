package com.intdb.android.features.catalog.view;

import com.intdb.android.features.catalog.presenter.CarousalPresenter;
import com.intdb.android.model.Movie;

import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public interface CarousalModule {
    void setPresenter(CarousalPresenter presenter);

    void loadList(List<Movie> list);

    String getSortBy();

    void showLoading();

    void hideLoading();

    void showNetworkError();
}
