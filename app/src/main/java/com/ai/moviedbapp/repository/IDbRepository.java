package com.ai.moviedbapp.repository;

import com.ai.moviedbapp.entities.MovieDetails;
import com.ai.moviedbapp.entities.db.MovieDb;

import java.util.List;

import rx.Single;

public interface IDbRepository {

    MovieDb insertOrUpdateMovie(MovieDb movie);

    List<MovieDb> getMovies(int page);

    Single<MovieDetails> getMovieById(long id);
}
