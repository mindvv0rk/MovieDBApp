package com.ai.moviedbapp.core.network;

import android.database.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IImageApi {


    @GET
    Observable<ResponseBody> getImage(@Url String imageUrl);
}
