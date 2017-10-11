package com.intdb.android.app.injection;

import android.content.Context;

import com.intdb.android.app.IntDbApp;
import com.intdb.android.utils.PreferencesUtils;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Component(modules = {AppModule.class, NetworkApiModule.class})
public interface AppComponent {

    Context getAppContext();

    IntDbApp getApp();

    PreferencesUtils exposePreferencesUtils();

    OkHttpClient exposeOkHttp();

    GsonConverterFactory exposeConverterFactory();

    RxJavaCallAdapterFactory exposeAdapterFactory();

    Retrofit exposeRetrofit();

}