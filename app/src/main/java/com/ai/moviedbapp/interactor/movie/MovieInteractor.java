package com.ai.moviedbapp.interactor.movie;

import com.ai.moviedbapp.core.network.IImageApi;
import com.ai.moviedbapp.core.network.IMovieApi;
import com.ai.moviedbapp.core.network.NetworkModuleFactory;
import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.db.MovieDb;
import com.ai.moviedbapp.entities.responses.MovieResponse;
import com.ai.moviedbapp.interactor.INetworkState;
import com.ai.moviedbapp.repository.IDbRepository;
import com.ai.moviedbapp.repository.IPreferencesRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Single;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MovieInteractor implements IMovieInteractor {

    private static final String POPULAR_SORT = "popularity.desc";
    private static final String TOP_RATED_SORT = "vote_average.desc";

    private IMovieApi mMovieApi;
    private IImageApi mImageApi;
    private IPreferencesRepository mPreferencesRepository;
    private IDbRepository mDbRepository;
    private INetworkState mNetworkState;

    @Inject
    public MovieInteractor(IMovieApi movieApi, IImageApi imageApi,
                           IPreferencesRepository preferencesRepository, IDbRepository dbRepository,
                           INetworkState networkState) {
        mMovieApi = movieApi;
        mImageApi = imageApi;
        mPreferencesRepository = preferencesRepository;
        mDbRepository = dbRepository;
        mNetworkState = networkState;
    }

    @Override
    public Single<List<Movie>> loadMovies() {
        return mMovieApi
                .getMovies(NetworkModuleFactory.TOKEN, POPULAR_SORT, 1)
                .toObservable()
                .flatMapIterable(MovieResponse::getMovies)
                .observeOn(Schedulers.computation())
                .map(saveMovieToDb())
                .observeOn(Schedulers.io())
                .flatMap(loadImage())
                .observeOn(Schedulers.computation())
                .map(movieDb -> mDbRepository.insertOrUpdateMovie(movieDb))
                .map(Movie::createFromDbo)
                .toList()
                .toSingle();
    }

    private Func1<MovieResponse.Movie, MovieDb> saveMovieToDb() {
        return movie -> {
            MovieDb movieDb = MovieDb.createFromServerMovie(movie);
            return mDbRepository.insertOrUpdateMovie(movieDb);
        };
    }

    private Func1<MovieDb, Observable<MovieDb>> loadImage() {
        return movieDb -> {
            String posterUrl = mPreferencesRepository.getImageBaseUrl() + movieDb.getPosterPath();
            return mImageApi
                    .getImage(posterUrl)
                    .toObservable()
                    .map(convertImageToByteArray(movieDb));
        };
    }

    private Func1<ResponseBody, MovieDb> convertImageToByteArray(MovieDb movieDb) {
        return responseBody -> {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = responseBody.byteStream();

            try {
                int current;
                while ((current = inputStream.read()) != -1) {
                    outputStream.write((byte) current);
                }
                outputStream.close();
            } catch (IOException e) {
                throw Exceptions.propagate(e);
            }

            movieDb.setPoster(outputStream.toByteArray());

            try {
                outputStream.close();
            } catch (IOException e) {
                throw Exceptions.propagate(e);
            }

            return movieDb;

        };
    }
}
