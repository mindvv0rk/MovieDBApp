package com.ai.moviedbapp.presenter;

import com.ai.moviedbapp.core.AbstractPresenter;
import com.ai.moviedbapp.core.di.Injector;
import com.ai.moviedbapp.entities.MovieDetails;
import com.ai.moviedbapp.interactor.movie.details.IMovieDetailsInteractor;
import com.ai.moviedbapp.movies.details.IMovieDetailsView;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public final class MovieDetailsPresenter extends AbstractPresenter<IMovieDetailsView> {

    private static final String TAG = MovieDetailsPresenter.class.getSimpleName();

    @Inject
    IMovieDetailsInteractor mMovieDetailsInteractor;

    private MovieDetails mMovieDetails;
    private Subscription mMovieSubscription;
    private long mMovieId;

    public MovieDetailsPresenter(long id) {
        Injector.getInstance().initMovieDetailsComponent().inject(this);
        mMovieId = id;
        loadMovie(mMovieId);
    }

    @Override
    protected void onViewAttached(@NonNull IMovieDetailsView view) {
        super.onViewAttached(view);

        if (mMovieSubscription != null && !mMovieSubscription.isUnsubscribed()) {
            getView().showLoading();
        } else if (mMovieDetails != null) {
            getView().showMovie(mMovieDetails);
        } else {
            loadMovie(mMovieId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Injector.getInstance().destroyMovieDetailsComponent();
    }

    private void loadMovie(long movieId) {
        if (getView() != null) getView().showLoading();

        mMovieSubscription = mMovieDetailsInteractor
                .getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieDetails -> {
                            mMovieDetails = movieDetails;
                            getView().showMovie(movieDetails);
                        },
                        throwable -> {
                            Log.e(TAG, throwable.getLocalizedMessage(), throwable);
                            getView().showError("Human readable error message!");
                        });
    }




}
