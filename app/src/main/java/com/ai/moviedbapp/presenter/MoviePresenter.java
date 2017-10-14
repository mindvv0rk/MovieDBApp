package com.ai.moviedbapp.presenter;

import com.ai.moviedbapp.core.AbstractPresenter;
import com.ai.moviedbapp.core.di.Injector;
import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.interactor.configuration.IConfigurationInteractor;
import com.ai.moviedbapp.interactor.movie.IMovieInteractor;
import com.ai.moviedbapp.movies.IMovieView;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MoviePresenter extends AbstractPresenter<IMovieView> {

    private static final String TAG = MoviePresenter.class.getSimpleName();

    @Inject
    IConfigurationInteractor mConfigurationInteractor;

    @Inject
    IMovieInteractor mMovieInteractor;

    public MoviePresenter() {
        Injector.getInstance().initMovieComponent().inject(this);
    }

    @Override
    protected void onViewAttached(@NonNull IMovieView view) {
        super.onViewAttached(view);
        loadMovies();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Injector.getInstance().destroyMovieComponent();
    }

    private void loadMovies() {
        getView().showLoading();
        mConfigurationInteractor
                .requestConfiguration()
                .flatMap(s -> mMovieInteractor.loadMovies())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movies -> {
                            getView().showData();
                            Log.i(TAG, "Size = " + movies.size());
                        },
                        throwable -> {
                            Log.e(TAG, throwable.getLocalizedMessage(), throwable);
                        });
    }

}
