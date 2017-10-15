package com.ai.moviedbapp.core.di.components;

import com.ai.moviedbapp.core.di.modules.ApplicationModule;
import com.ai.moviedbapp.core.di.modules.MovieDetailsModule;
import com.ai.moviedbapp.core.di.modules.MovieModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    MovieComponent plusMovieComponent(MovieModule module);

    MovieDetailsComponent plusMovieDetailsComponent(MovieDetailsModule module);
}
