package com.ai.moviedbapp;


import com.ai.moviedbapp.core.di.Injector;
import com.ai.moviedbapp.core.di.components.DaggerApplicationComponent;
import com.ai.moviedbapp.core.di.modules.ApplicationModule;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.init(
                DaggerApplicationComponent
                        .builder()
                        .applicationModule(new ApplicationModule(this))
                        .build());
    }
}
