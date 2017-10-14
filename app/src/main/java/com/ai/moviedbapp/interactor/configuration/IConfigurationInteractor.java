package com.ai.moviedbapp.interactor.configuration;

import rx.Completable;

public interface IConfigurationInteractor {

    Completable requestConfiguration();
}
