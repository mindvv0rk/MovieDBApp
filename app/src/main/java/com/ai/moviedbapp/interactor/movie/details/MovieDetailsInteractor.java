package com.ai.moviedbapp.interactor.movie.details;

import com.ai.moviedbapp.repository.IDbRepository;

import javax.inject.Inject;

class MovieDetailsInteractor implements IMovieDetailsInteractor {

    private IDbRepository mDbRepository;

    @Inject
    public MovieDetailsInteractor(IDbRepository dbRepository) {
        mDbRepository = dbRepository;
    }


}
