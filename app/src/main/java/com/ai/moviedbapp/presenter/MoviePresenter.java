package com.ai.moviedbapp.presenter;

import com.ai.moviedbapp.core.AbstractPresenter;
import com.ai.moviedbapp.core.IView;
import com.ai.moviedbapp.core.di.Injector;
import com.ai.moviedbapp.interactor.configuration.IConfigurationInteractor;
import com.ai.moviedbapp.movies.IMovieView;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MoviePresenter extends AbstractPresenter<IMovieView> {

    @Inject
    IConfigurationInteractor mConfigurationInteractor;

    public MoviePresenter() {
        Injector.getInstance().initMovieComponent().inject(this);
        getConfiguration();
    }

    @Override
    protected void onViewAttached(@NonNull IMovieView view) {
        super.onViewAttached(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Injector.getInstance().destroyMovieComponent();
    }

    private void getConfiguration() {
        mConfigurationInteractor
                .requestConfiguration()
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
