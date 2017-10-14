package com.ai.moviedbapp.repository;

import android.content.Context;
import android.content.SharedPreferences;

public final class PreferencesRepository implements IPreferencesRepository {

    private static final String PREFERENCES_NAME = "preferencesName";
    private static final String IMAGE_BASE_URL_KEY = "keys:imageBaseUrl";

    private SharedPreferences mPreferences;

    public PreferencesRepository(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveImageBaseUrl(String url) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(IMAGE_BASE_URL_KEY, url);
        editor.apply();
    }

    @Override
    public String getImageBaseUrl() {
        return mPreferences.getString(IMAGE_BASE_URL_KEY, null);
    }
}
