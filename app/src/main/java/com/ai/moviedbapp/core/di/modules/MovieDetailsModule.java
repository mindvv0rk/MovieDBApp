package com.ai.moviedbapp.core.di.modules;

import com.ai.moviedbapp.core.di.MovieScope;
import com.ai.moviedbapp.interactor.movie.details.IMovieDetailsInteractor;
import com.ai.moviedbapp.interactor.movie.details.MovieDetailsInteractor;
import com.ai.moviedbapp.repository.IDbRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsModule {

    @Provides
    @MovieScope
    public IMovieDetailsInteractor provideMovieDetailInteractor(IDbRepository dbRepository) {
        return new MovieDetailsInteractor(dbRepository);
    }
}
