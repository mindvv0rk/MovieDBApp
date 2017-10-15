package com.ai.moviedbapp.core.di.components;

import com.ai.moviedbapp.core.di.MovieScope;
import com.ai.moviedbapp.core.di.modules.MovieDetailsModule;
import com.ai.moviedbapp.presenter.MovieDetailsPresenter;
import com.ai.moviedbapp.presenter.MoviePresenter;

import dagger.Subcomponent;

@MovieScope
@Subcomponent(modules = MovieDetailsModule.class)
public interface MovieDetailsComponent {

    void inject(MovieDetailsPresenter presenter);
}
