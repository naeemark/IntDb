package com.intdb.android.features.catalog.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.intdb.android.R;
import com.intdb.android.app.injection.AppComponent;
import com.intdb.android.app.presenter.loader.PresenterFactory;
import com.intdb.android.app.view.impl.BaseActivity;
import com.intdb.android.constants.SortBy;
import com.intdb.android.features.catalog.injection.CatalogViewModule;
import com.intdb.android.features.catalog.injection.DaggerCatalogViewComponent;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.view.CarousalModule;
import com.intdb.android.features.catalog.view.CatalogView;
import com.intdb.android.model.Movie;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public final class CatalogActivity extends BaseActivity<CatalogPresenter, CatalogView> implements CatalogView {

    @Inject
    PresenterFactory<CatalogPresenter> mPresenterFactory;

    @BindView(R.id.popular_include_layout_carousel)
    protected View popularCarousal;
    @BindView(R.id.top_rated_include_layout_carousel)
    protected View topRatedCarousal;
    @BindView(R.id.revenue_include_layout_carousel)
    protected View revenueCarousal;

    private CarousalModule popularCarousalModule;
    private CarousalModule topRatedCarousalModule;
    private CarousalModule revenueCarousalModule;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerCatalogViewComponent.builder()
                .appComponent(parentComponent)
                .catalogViewModule(new CatalogViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CatalogPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_catalog;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        popularCarousalModule = new CarousalModule(this, popularCarousal, getString(R.string.carousel_title_popular), SortBy.POPULARITY);
        topRatedCarousalModule = new CarousalModule(this, topRatedCarousal, getString(R.string.carousel_title_top_rated), SortBy.RATING);
        revenueCarousalModule = new CarousalModule(this, revenueCarousal, getString(R.string.carousel_title_top_revenue), SortBy.REVENUE);

    }

    @Override
    public void loadList() {
        assert mPresenter != null;
        mPresenter.loadCarousalModules(popularCarousalModule, topRatedCarousalModule, revenueCarousalModule);
    }

    @Override
    public void loadList(int pageNumber) {
        assert mPresenter != null;
        mPresenter.loadMoviesPage(pageNumber);
    }

    @Override
    public void loadList(List<Movie> list) {
        popularCarousalModule.loadList(list);

    }

    public void loadMoreItems(int pageNumber) {
        Timber.e("Load More...");
        loadList(pageNumber);
    }

}
