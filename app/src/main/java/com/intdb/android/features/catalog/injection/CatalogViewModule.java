package com.intdb.android.features.catalog.injection;

import android.support.annotation.NonNull;

import com.intdb.android.app.presenter.loader.PresenterFactory;
import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.interactor.impl.CatalogInteractorImpl;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.presenter.impl.CatalogPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class CatalogViewModule {
    @Provides
    public CatalogInteractor provideInteractor() {
        return new CatalogInteractorImpl();
    }

    @Provides
    public PresenterFactory<CatalogPresenter> providePresenterFactory(@NonNull final CatalogInteractor interactor) {
        return new PresenterFactory<CatalogPresenter>() {
            @NonNull
            @Override
            public CatalogPresenter create() {
                return new CatalogPresenterImpl(interactor);
            }
        };
    }
}
