package com.ai.moviedbapp.core.di.modules;

import com.ai.moviedbapp.repository.IApiRepository;

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
    public IApiRepository provideApiRepository() {
        return null;
    }


}
