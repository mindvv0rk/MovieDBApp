package com.ai.moviedbapp.core.di.components;

import com.ai.moviedbapp.core.di.MovieScope;
import com.ai.moviedbapp.core.di.modules.MovieModule;
import com.ai.moviedbapp.movies.MovieActivity;

import dagger.Component;
import dagger.Subcomponent;

@MovieScope
@Subcomponent(modules = MovieModule.class)
public interface MovieComponent {

    void inject(MovieActivity activity);
}
