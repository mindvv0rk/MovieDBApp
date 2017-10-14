package com.ai.moviedbapp.movies;

import com.ai.moviedbapp.core.IView;


public interface IMovieView extends IView {

    void showLoading();

    void showError(String message);
//    void showData();
}
