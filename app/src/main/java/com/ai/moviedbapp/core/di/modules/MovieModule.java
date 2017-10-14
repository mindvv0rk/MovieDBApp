package com.ai.moviedbapp.core.di.modules;

import com.ai.moviedbapp.core.di.MovieScope;
import com.ai.moviedbapp.core.network.IConfigurationApi;
import com.ai.moviedbapp.core.network.IImageApi;
import com.ai.moviedbapp.core.network.IMovieApi;
import com.ai.moviedbapp.interactor.configuration.ConfigurationInteractor;
import com.ai.moviedbapp.interactor.configuration.IConfigurationInteractor;
import com.ai.moviedbapp.interactor.movie.IMovieInteractor;
import com.ai.moviedbapp.interactor.movie.MovieInteractor;
import com.ai.moviedbapp.repository.IDbRepository;
import com.ai.moviedbapp.repository.IPreferencesRepository;

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
    public IMovieInteractor provideMovieInteractor(
            IMovieApi movieApi,
            IImageApi imageApi,
            IPreferencesRepository preferencesRepository,
            IDbRepository dbRepository) {
        return new MovieInteractor(movieApi, imageApi, preferencesRepository, dbRepository);
    }


}
