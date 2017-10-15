package com.ai.moviedbapp.movies.details;

import com.ai.moviedbapp.core.IView;
import com.ai.moviedbapp.entities.MovieDetails;


public interface IMovieDetailsView extends IView {

    void showLoading();
    void showError(String message);
    void showMovie(MovieDetails movie);
}
