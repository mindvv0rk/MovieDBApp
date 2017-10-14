package com.ai.moviedbapp.interactor.configuration;

import rx.Completable;
import rx.Single;

public interface IConfigurationInteractor {

    Single<String> requestConfiguration();
}
