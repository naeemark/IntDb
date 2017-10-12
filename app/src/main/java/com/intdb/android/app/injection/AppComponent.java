package com.intdb.android.app.injection;

import android.content.Context;

import com.intdb.android.app.IntDbApp;
import com.intdb.android.utils.PreferencesUtils;
import com.intdb.android.utils.parser.ModelParser;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkApiModule.class})
public interface AppComponent {

    Context getAppContext();

    IntDbApp getApp();

    Retrofit exposeRetrofit();

    ModelParser exposeParser();

    PreferencesUtils exposePreferencesUtils();

}