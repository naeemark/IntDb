package com.intdb.android.features.catalog.injection;

import com.intdb.android.app.injection.ActivityScope;
import com.intdb.android.app.injection.AppComponent;
import com.intdb.android.features.catalog.view.impl.CatalogActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = CatalogViewModule.class)
public interface CatalogViewComponent {
    void inject(CatalogActivity activity);
}