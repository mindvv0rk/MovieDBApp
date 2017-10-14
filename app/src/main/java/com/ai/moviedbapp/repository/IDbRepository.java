package com.ai.moviedbapp.repository;

import com.ai.moviedbapp.entities.db.MovieDb;

import java.util.List;

public interface IDbRepository {

    MovieDb insertOrUpdateMovie(MovieDb movie);

    List<MovieDb> getMovies(int page);
}
