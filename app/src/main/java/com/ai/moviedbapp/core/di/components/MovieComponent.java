package com.ai.moviedbapp.core.di.components;

import com.ai.moviedbapp.core.di.MovieScope;
import com.ai.moviedbapp.core.di.modules.MovieModule;
import com.ai.moviedbapp.movies.MovieActivity;
import com.ai.moviedbapp.presenter.MoviePresenter;

import dagger.Component;
import dagger.Subcomponent;

@MovieScope
@Subcomponent(modules = MovieModule.class)
public interface MovieComponent {

    void inject(MoviePresenter presenter);
}
