package com.ai.moviedbapp.interactor.movie;

import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.Sort;

import java.util.List;

import rx.Single;

public interface IMovieInteractor {

    Single<List<Movie>> loadMovies(Sort sort);
}
