package com.ai.moviedbapp.interactor.movie.details;

import com.ai.moviedbapp.entities.MovieDetails;
import com.ai.moviedbapp.repository.IDbRepository;

import javax.inject.Inject;

import rx.Single;

public class MovieDetailsInteractor implements IMovieDetailsInteractor {

    private IDbRepository mDbRepository;

    @Inject
    public MovieDetailsInteractor(IDbRepository dbRepository) {
        mDbRepository = dbRepository;
    }


    @Override
    public Single<MovieDetails> getMovieDetails(long id) {
        return mDbRepository
                .getMovieById(id);
    }
}
