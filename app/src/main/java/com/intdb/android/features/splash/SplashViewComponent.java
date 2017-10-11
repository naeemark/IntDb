package com.intdb.android.features.splash;

import com.intdb.android.app.injection.ActivityScope;
import com.intdb.android.app.injection.AppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashViewModule.class)
public interface SplashViewComponent {
    void inject(SplashActivity activity);
}