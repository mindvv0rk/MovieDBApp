package com.ai.moviedbapp.core.network;

import com.google.gson.GsonBuilder;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkModuleFactory {

    public static final String TOKEN = "c06c61e3bca444f6d2a949d339d75254";
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_VERSION = "3";
    private static final String SLASH = "/";

    private static final int SOCKET_READ_TIMEOUT = 30;
    private static final int SOCKET_CONNECT_TIMEOUT = 30;

    private static final String CONFIGURATION_HTTP_LOG_TAG = "ConfigurationOkHttp";
    private static final String MOVIE_HTTP_LOG_TAG = "MovieOkHttp";
    private static final String IMAGE_HTTP_LOG_TAG = "ImageOkHttp";

    public static IConfigurationApi createConfigurationApi() {
        String url = BASE_URL + API_VERSION + SLASH;
        return createAPI(url, IConfigurationApi.class, CONFIGURATION_HTTP_LOG_TAG);
    }

    public static IMovieApi createMovieApi() {
        String url = BASE_URL + API_VERSION + SLASH;
        return createAPI(url, IMovieApi.class, MOVIE_HTTP_LOG_TAG);
    }

    public static IImageApi createImageApi() {
        OkHttpClient httpClient = createOkHttpClient(IMAGE_HTTP_LOG_TAG);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient);

        return retrofitBuilder.build().create(IImageApi.class);
    }

    private static OkHttpClient createOkHttpClient(final String httpLogTag) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(SOCKET_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(SOCKET_READ_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d(httpLogTag, message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        return builder.build();
    }

    private static <T> T createAPI(String baseUrl, Class<T> apiClass, final String httpLogTag) {

        OkHttpClient httpClient = createOkHttpClient(httpLogTag);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setPrettyPrinting().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient);

        return retrofitBuilder.build().create(apiClass);
    }
}
