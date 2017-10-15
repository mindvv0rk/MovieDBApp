package com.ai.moviedbapp.interactor.movie.details;

import com.ai.moviedbapp.entities.MovieDetails;

import rx.Single;

public interface IMovieDetailsInteractor {

    Single<MovieDetails> getMovieDetails(long id);
}
