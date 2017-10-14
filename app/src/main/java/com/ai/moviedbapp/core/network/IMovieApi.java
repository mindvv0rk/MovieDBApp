package com.ai.moviedbapp.core.network;

import android.database.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMovieApi {

    @GET("/discover/movie/")
    Observable<?> getMovies(
            @Query("api_key") String key,
            @Query("sort_by") String criteria,
            @Query("page") int page);
}
