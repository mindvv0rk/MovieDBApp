package com.ai.moviedbapp.core.di.modules;

import com.ai.moviedbapp.core.di.MovieScope;
import com.ai.moviedbapp.core.network.IConfigurationApi;
import com.ai.moviedbapp.core.network.IImageApi;
import com.ai.moviedbapp.core.network.IMovieApi;
import com.ai.moviedbapp.interactor.INetworkState;
import com.ai.moviedbapp.interactor.NetworkState;
import com.ai.moviedbapp.interactor.configuration.ConfigurationInteractor;
import com.ai.moviedbapp.interactor.configuration.IConfigurationInteractor;
import com.ai.moviedbapp.interactor.movie.IMovieInteractor;
import com.ai.moviedbapp.interactor.movie.MovieInteractor;
import com.ai.moviedbapp.repository.IDbRepository;
import com.ai.moviedbapp.repository.IPreferencesRepository;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieModule {

    @Provides
    @MovieScope
    public IConfigurationInteractor provideConfigurationInteractor(
            IPreferencesRepository repository, IConfigurationApi configurationApi) {
        return new ConfigurationInteractor(repository, configurationApi);
    }

    @Provides
    @MovieScope
    public INetworkState provideNetworkState(Context context) {
        return new NetworkState(context);
    }


    @Provides
    @MovieScope
    public IMovieInteractor provideMovieInteractor(
            IMovieApi movieApi,
            IImageApi imageApi,
            IPreferencesRepository preferencesRepository,
            IDbRepository dbRepository,
            INetworkState networkState) {
        return new MovieInteractor(movieApi, imageApi, preferencesRepository, dbRepository,
                networkState);
    }


}
