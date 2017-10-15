package com.ai.moviedbapp.repository;

import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.MovieDetails;
import com.ai.moviedbapp.entities.db.MovieDb;

import java.util.List;

import io.realm.Realm;
import rx.Single;
import rx.SingleSubscriber;

public class DbRepository implements IDbRepository {

    @Override
    public MovieDb insertOrUpdateMovie(MovieDb movie) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MovieDb realmMovie = realm.copyToRealmOrUpdate(movie);
        realm.commitTransaction();

        movie = MovieDb.createFormRealmObject(realmMovie);

        realm.close();

        return movie;
    }

    @Override
    public List<MovieDb> getMovies(int page) {
        return null;
    }

    @Override
    public Single<MovieDetails> getMovieById(long id) {
        return Single.create(singleSubscriber -> {
            Realm realm = Realm.getDefaultInstance();
            MovieDb realmMovieDb = realm.where(MovieDb.class)
                    .equalTo("mId", id)
                    .findFirst();

            if (realmMovieDb == null) {
                singleSubscriber.onError(new RuntimeException("Movie with id=" + id + " not found!"));
            }

            MovieDetails movieDetails = MovieDetails.createFromMovieDb(realmMovieDb);
            singleSubscriber.onSuccess(movieDetails);
        });
    }
}
