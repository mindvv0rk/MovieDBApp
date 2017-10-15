package com.ai.moviedbapp.movies;

import android.view.View;

public interface IMovieAdapterClickHandler {

    void onMovieClick(View view, long movieId);
}
