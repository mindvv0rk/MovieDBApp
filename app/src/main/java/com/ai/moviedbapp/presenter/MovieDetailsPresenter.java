package com.ai.moviedbapp.presenter;

import com.ai.moviedbapp.core.AbstractPresenter;
import com.ai.moviedbapp.movies.details.IMovieDetailsView;

import android.support.annotation.NonNull;


public final class MovieDetailsPresenter extends AbstractPresenter<IMovieDetailsView> {

    public MovieDetailsPresenter() {
        //inject
    }

    @Override
    protected void onViewAttached(@NonNull IMovieDetailsView view) {
        super.onViewAttached(view);
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
