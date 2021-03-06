package com.intdb.android.utils;

import android.content.Context;
import android.content.SharedPreferences;


import javax.inject.Inject;
import javax.inject.Singleton;

import static com.intdb.android.constants.AppConstants.SHARED_PREFS_NAME;

/**
 * Provides Utility to play with shared prefs
 */
@Singleton
public class PreferencesUtils {


    public enum PrefKeys {
        IS_SPLASH_DONE,
    }

    private final SharedPreferences mPref;

    @Inject
    public PreferencesUtils(Context context) {
        mPref = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void putData(String key, String data) {
        mPref.edit().putString(key, data).apply();
    }

    public void putBoolean(String key, boolean flag) {
        mPref.edit().putBoolean(key, flag).apply();
    }

    public String getData(String key) {
        return mPref.getString(key, null);
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key, defaultValue);
    }

}
