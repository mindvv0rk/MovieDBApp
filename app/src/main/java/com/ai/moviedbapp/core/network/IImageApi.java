package com.ai.moviedbapp.core.network;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Single;

public interface IImageApi {


    @GET
    Single<ResponseBody> getImage(@Url String imageUrl);
}
