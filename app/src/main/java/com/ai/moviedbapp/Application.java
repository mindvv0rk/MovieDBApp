package com.ai.moviedbapp;


import com.ai.moviedbapp.core.di.Injector;
import com.ai.moviedbapp.core.di.components.DaggerApplicationComponent;
import com.ai.moviedbapp.core.di.modules.ApplicationModule;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import android.content.Context;

import io.realm.Realm;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger(this);
        initRealm(this);
        initPicasso(this);
    }

    private void initPicasso(Context context) {
        Picasso.Builder builder = new Picasso.Builder(context);
        Picasso picasso = builder
                .loggingEnabled(true)
                .downloader(new OkHttp3Downloader(context, Integer.MAX_VALUE)).build();

        Picasso.setSingletonInstance(picasso);
    }

    private void initDagger(Context context) {
        Injector.init(
                DaggerApplicationComponent
                        .builder()
                        .applicationModule(new ApplicationModule(context))
                        .build());
    }

    private void initRealm(Context context) {
        Realm.init(context);
    }
}
