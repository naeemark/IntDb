package com.intdb.android.features.catalog.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.intdb.android.R;
import com.intdb.android.app.injection.AppComponent;
import com.intdb.android.app.presenter.loader.PresenterFactory;
import com.intdb.android.app.view.impl.BaseActivity;
import com.intdb.android.features.catalog.injection.CatalogViewModule;
import com.intdb.android.features.catalog.injection.DaggerCatalogViewComponent;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.view.CatalogView;

import javax.inject.Inject;

public final class CatalogActivity extends BaseActivity<CatalogPresenter, CatalogView> implements CatalogView {
    @Inject
    PresenterFactory<CatalogPresenter> mPresenterFactory;

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

    }
}
