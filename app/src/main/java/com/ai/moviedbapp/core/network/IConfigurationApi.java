package com.ai.moviedbapp.core.network;

import android.database.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IConfigurationApi {

    @GET("/configuration/")
    Observable<?> getConfiguration(@Query("api_key") String key);
}
