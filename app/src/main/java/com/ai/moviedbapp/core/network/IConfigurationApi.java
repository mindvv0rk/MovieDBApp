package com.ai.moviedbapp.core.network;

import com.ai.moviedbapp.entities.responses.ConfigurationResponse;

import android.database.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

public interface IConfigurationApi {

    @GET("configuration")
    Single<ConfigurationResponse> getConfiguration(@Query("api_key") String key);
}
