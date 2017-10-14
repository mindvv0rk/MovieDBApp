package com.ai.moviedbapp.interactor.movie;

import com.ai.moviedbapp.core.network.IImageApi;
import com.ai.moviedbapp.core.network.IMovieApi;
import com.ai.moviedbapp.core.network.NetworkModuleFactory;
import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.db.MovieDb;
import com.ai.moviedbapp.entities.responses.MovieResponse;
import com.ai.moviedbapp.repository.IPreferencesRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Single;
import rx.exceptions.Exceptions;

public class MovieInteractor implements IMovieInteractor {

    private static final String POPULAR_SORT = "popularity.desc";
    private static final String TOP_RATED_SORT = "vote_average.desc";

    private IMovieApi mMovieApi;
    private IImageApi mImageApi;
    private IPreferencesRepository mPreferencesRepository;

    @Inject
    public MovieInteractor(IMovieApi movieApi, IImageApi imageApi, IPreferencesRepository preferencesRepository) {
        mMovieApi = movieApi;
        mImageApi = imageApi;
        mPreferencesRepository = preferencesRepository;
    }

    @Override
    public Single<List<Movie>> loadMovies() {
        return mMovieApi
                .getMovies(NetworkModuleFactory.TOKEN, POPULAR_SORT, 1)
                .toObservable()
                .flatMapIterable(MovieResponse::getMovies)
                .map(movie -> {
                    MovieDb movieDb = MovieDb.createFromServerMovie(movie);
//                    save to db movie
                    return movieDb;
                })
                .flatMap(movieDb -> {
                    String posterUrl = mPreferencesRepository.getImageBaseUrl() + movieDb.getPosterPath();
                    return mImageApi
                            .getImage(posterUrl)
                            .toObservable()
                            .map(responseBody -> {
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

                            });
                })
                .map(movieDb -> {
                    //update moviedb
                    return movieDb;
                })
                .map(Movie::createFromDbo)
                .toList()
                .toSingle();
    }
}
