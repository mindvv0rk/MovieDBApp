package com.ai.moviedbapp.repository;

import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.MovieDetails;
import com.ai.moviedbapp.entities.Sort;
import com.ai.moviedbapp.entities.db.MovieDb;

import java.util.List;

import rx.Single;

public interface IDbRepository {

    Movie insertOrUpdateMovie(MovieDb movie);

    Single<List<Movie>> getMovies(Sort sort, int page, int pageSize);

    Single<MovieDetails> getMovieById(long id);
}
