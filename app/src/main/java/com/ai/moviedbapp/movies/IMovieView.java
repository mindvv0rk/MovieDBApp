package com.ai.moviedbapp.movies;

import com.ai.moviedbapp.core.IView;
import com.ai.moviedbapp.entities.Movie;

import java.util.List;


public interface IMovieView extends IView {

    void showLoading();

    void showError(String message);
    void showData(List<Movie> movies);
}
