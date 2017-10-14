package com.ai.moviedbapp.core.di.modules;

import com.ai.moviedbapp.core.network.IConfigurationApi;
import com.ai.moviedbapp.core.network.IImageApi;
import com.ai.moviedbapp.core.network.IMovieApi;
import com.ai.moviedbapp.core.network.NetworkModuleFactory;
import com.ai.moviedbapp.repository.DbRepository;
import com.ai.moviedbapp.repository.IDbRepository;
import com.ai.moviedbapp.repository.IPreferencesRepository;
import com.ai.moviedbapp.repository.PreferencesRepository;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public IConfigurationApi provideConfigurationApi() {
        return NetworkModuleFactory.createConfigurationApi();
    }

    @Provides
    @Singleton
    public IMovieApi provideMovieApi() {
        return NetworkModuleFactory.createMovieApi();
    }

    @Provides
    @Singleton
    public IImageApi provideImageApi() {
        return NetworkModuleFactory.createImageApi();
    }

    @Provides
    @Singleton
    public IPreferencesRepository providePreferences(Context context) {
        return new PreferencesRepository(context);
    }

    @Provides
    @Singleton
    public IDbRepository provideRealmRepository() {
        return new DbRepository();
    }
}
