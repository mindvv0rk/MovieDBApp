package com.ai.moviedbapp.presenter;

import com.ai.moviedbapp.core.AbstractPresenter;
import com.ai.moviedbapp.core.di.Injector;
import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.Sort;
import com.ai.moviedbapp.interactor.configuration.IConfigurationInteractor;
import com.ai.moviedbapp.interactor.movie.IMovieInteractor;
import com.ai.moviedbapp.movies.IMovieView;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MoviePresenter extends AbstractPresenter<IMovieView> {

    private static final String TAG = MoviePresenter.class.getSimpleName();

    @Inject
    IMovieInteractor mMovieInteractor;

    private List<Movie> mMovies;
    private Subscription mLoadingMovies;
    private Sort mSort;

    public MoviePresenter() {
        Injector.getInstance().initMovieComponent().inject(this);
        mMovies = new ArrayList<>();
        mSort = new Sort();
        loadMovies(mSort);
    }

    public void changeSortType() {
        mSort.changeSortType();
        reloadMovies();
    }

    public void reloadMovies() {
        mMovies.clear();
        loadMovies(mSort);
    }

    @Override
    protected void onViewAttached(@NonNull IMovieView view) {
        super.onViewAttached(view);

        if (mLoadingMovies != null && !mLoadingMovies.isUnsubscribed()) {
            getView().showLoading();
        } else if (mMovies.isEmpty()) {
            loadMovies(mSort);
        } else {
            getView().showData(mMovies, mSort);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Injector.getInstance().destroyMovieComponent();
    }

    private void loadMovies(Sort sort) {
        if (getView() != null) getView().showLoading();
        mLoadingMovies = mMovieInteractor.loadMovies(sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movies -> {
                            mMovies.clear();
                            mMovies.addAll(movies);
                            getView().showData(mMovies, mSort);
                        },
                        throwable -> {
                            Log.e(TAG, throwable.getLocalizedMessage(), throwable);
                            getView().showError("Human readable error message");
                        });
    }

}
