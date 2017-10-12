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
import com.intdb.android.features.catalog.view.CatalogView;

import javax.inject.Inject;

import butterknife.BindView;

public final class CatalogActivity extends BaseActivity<CatalogPresenter, CatalogView> implements CatalogView {

    @Inject
    PresenterFactory<CatalogPresenter> mPresenterFactory;

    @BindView(R.id.popular_include_layout_carousel)
    protected View popularCarousal;
    @BindView(R.id.top_rated_include_layout_carousel)
    protected View topRatedCarousal;
    @BindView(R.id.revenue_include_layout_carousel)
    protected View revenueCarousal;
    @BindView(R.id.new_include_layout_carousel)
    protected View newReleaseCarousal;
    @BindView(R.id.all_include_layout_carousel)
    protected View allItemsCarousal;

    private CarousalModuleImpl mPopularCarousalModuleImpl;
    private CarousalModuleImpl mTopRatedCarousalModuleImpl;
    private CarousalModuleImpl mRevenueCarousalModuleImpl;
    private CarousalModuleImpl mNewReleaseCarousalModuleImpl;
    private CarousalModuleImpl mAllItemsCarousalModuleImpl;

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

        mPopularCarousalModuleImpl = new CarousalModuleImpl(this, popularCarousal, getString(R.string.carousel_title_popular), SortBy.POPULARITY);
        mTopRatedCarousalModuleImpl = new CarousalModuleImpl(this, topRatedCarousal, getString(R.string.carousel_title_top_rated), SortBy.RATING);
        mRevenueCarousalModuleImpl = new CarousalModuleImpl(this, revenueCarousal, getString(R.string.carousel_title_top_revenue), SortBy.REVENUE);
        mNewReleaseCarousalModuleImpl = new CarousalModuleImpl(this, newReleaseCarousal, getString(R.string.carousel_title_top_new), SortBy.NEW_RELEASE);
        mAllItemsCarousalModuleImpl = new CarousalModuleImpl(this, allItemsCarousal, getString(R.string.carousel_title_top_all), SortBy.All);

    }

    @Override
    public void loadCarousals() {
        assert mPresenter != null;
        mPresenter.loadCarousalModules(mPopularCarousalModuleImpl,
                mTopRatedCarousalModuleImpl,
                mRevenueCarousalModuleImpl,
                mNewReleaseCarousalModuleImpl,
                mAllItemsCarousalModuleImpl
        );
    }


}
