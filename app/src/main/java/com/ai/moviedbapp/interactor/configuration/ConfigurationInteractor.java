package com.ai.moviedbapp.interactor.configuration;

import com.ai.moviedbapp.core.network.IConfigurationApi;
import com.ai.moviedbapp.core.network.NetworkModuleFactory;
import com.ai.moviedbapp.entities.responses.ConfigurationResponse;
import com.ai.moviedbapp.repository.IPreferencesRepository;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Single;
import rx.functions.Func1;

public final class ConfigurationInteractor implements IConfigurationInteractor {

    private static final String PREFER_POSTER_SIZE = "w500";

    private IPreferencesRepository mPreferencesRepository;
    private IConfigurationApi mConfigurationApi;

    @Inject
    public ConfigurationInteractor(IPreferencesRepository preferencesRepository,
                                   IConfigurationApi configurationApi) {
        mPreferencesRepository = preferencesRepository;
        mConfigurationApi = configurationApi;
    }

    @Override
    public Single<String> requestConfiguration() {
        return mConfigurationApi
                .getConfiguration(NetworkModuleFactory.TOKEN)
                .map(createBaseImageUrl())
                .map(baseUrl -> {
                    mPreferencesRepository.saveImageBaseUrl(baseUrl);
                    return baseUrl;
                });
    }

    private Func1<ConfigurationResponse, String> createBaseImageUrl() {
        return configurationResponse1 -> {
            String baseImageUrl = configurationResponse1.getImages().getSecureBaseUrl();
            List<String> sizes = configurationResponse1.getImages().getPosterSizes();

            String preferSize = null;
            for (String size : sizes) {
                if (size.equals(PREFER_POSTER_SIZE)) {
                    preferSize = size;
                    break;
                }
            }

            if (preferSize == null) {
                preferSize = sizes.get(sizes.size()-1);
            }
            baseImageUrl = baseImageUrl.concat(preferSize);

            return baseImageUrl;
        };
    }
}
