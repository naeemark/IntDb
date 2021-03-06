package com.intdb.android.features.catalog.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.intdb.android.app.injection.ActivityScope;
import com.intdb.android.app.presenter.loader.PresenterFactory;
import com.intdb.android.features.catalog.interactor.CatalogInteractor;
import com.intdb.android.features.catalog.interactor.impl.CatalogInteractorImpl;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.presenter.impl.CatalogPresenterImpl;
import com.intdb.android.utils.parser.ModelParser;
import com.intdb.android.webapi.MoviesApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class CatalogViewModule {
    @Provides
    public CatalogInteractor provideInteractor(Context context, MoviesApiService apiService, ModelParser parser) {
        return new CatalogInteractorImpl(context, apiService, parser);
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

    @ActivityScope
    @Provides
    MoviesApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(MoviesApiService.class);
    }
}
