package com.ai.moviedbapp.core.di;

import com.ai.moviedbapp.core.di.components.ApplicationComponent;
import com.ai.moviedbapp.core.di.components.MovieComponent;
import com.ai.moviedbapp.core.di.modules.MovieModule;

public class Injector {

    private final ApplicationComponent mAppComponent;
    private MovieComponent mMovieComponent;
    private static Injector sInstance;

    private Injector(ApplicationComponent applicationComponent) {
        mAppComponent = applicationComponent;
    }

    public static void init(ApplicationComponent applicationComponent) {
        sInstance = new Injector(applicationComponent);
    }

    public static Injector getInstance() {
        return sInstance;
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    public MovieComponent initMovieComponent() {
        mMovieComponent = mAppComponent.plusMovieComponent(new MovieModule());
        return mMovieComponent;
    }

    public MovieComponent getMovieComponent() {
        return mMovieComponent;
    }

    public void destroyMovieComponent() {
        mMovieComponent = null;
    }
}
