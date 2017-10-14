package com.ai.moviedbapp.repository;

import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.db.MovieDb;

import java.util.List;

import io.realm.Realm;

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
}
