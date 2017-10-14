package com.ai.moviedbapp.core.di.modules;

import com.ai.moviedbapp.core.di.MovieScope;
import com.ai.moviedbapp.core.network.IConfigurationApi;
import com.ai.moviedbapp.interactor.configuration.ConfigurationInteractor;
import com.ai.moviedbapp.interactor.configuration.IConfigurationInteractor;
import com.ai.moviedbapp.repository.IPreferencesRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieModule {

    @Provides
    @MovieScope
    public IConfigurationInteractor provideConfigurationInteractor(
            IPreferencesRepository repository, IConfigurationApi configurationApi
    ) {
        return new ConfigurationInteractor(repository, configurationApi);
    }
}
