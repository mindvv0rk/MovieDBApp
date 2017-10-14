package com.ai.moviedbapp.repository;

public interface IPreferencesRepository {

    void saveImageBaseUrl(String url);

    String getImageBaseUrl();
}
