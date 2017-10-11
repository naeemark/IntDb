package com.intdb.android.features.catalog.view;

import android.support.annotation.UiThread;

import com.intdb.android.app.view.BaseView;
import com.intdb.android.model.Movie;

import java.util.List;

@UiThread
public interface CatalogView extends BaseView {

    void loadList();

    void loadList(List<Movie> list);
}