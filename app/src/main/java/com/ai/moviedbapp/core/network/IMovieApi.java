package com.ai.moviedbapp.core.network;

import com.ai.moviedbapp.entities.responses.MovieResponse;

import android.database.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

public interface IMovieApi {

    @GET("discover/movie/")
    Single<MovieResponse> getMovies(
            @Query("api_key") String key,
            @Query("sort_by") String criteria,
            @Query("page") int page);
}
