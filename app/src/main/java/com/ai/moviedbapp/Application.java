package com.ai.moviedbapp;


import com.ai.moviedbapp.core.di.Injector;
import com.ai.moviedbapp.core.di.components.DaggerApplicationComponent;
import com.ai.moviedbapp.core.di.modules.ApplicationModule;

import android.content.Context;

import io.realm.Realm;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger(this);
        initRealm(this);
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
