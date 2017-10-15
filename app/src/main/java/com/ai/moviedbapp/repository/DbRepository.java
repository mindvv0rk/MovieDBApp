package com.ai.moviedbapp.repository;

import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.MovieDetails;
import com.ai.moviedbapp.entities.db.MovieDb;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Single;
import rx.SingleSubscriber;

import static com.ai.moviedbapp.entities.Sort.*;

public class DbRepository implements IDbRepository {

    private static final String ID_FIELD = "mId";
    private static final String POPULARITY_FIELD = "mPopularity";
    private static final String RATING_FIELD = "mRating";

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
    public Single<List<Movie>> getMovies(com.ai.moviedbapp.entities.Sort sort, int page) {
        return Single
                .create(singleSubscriber -> {
                    Realm realm = Realm.getDefaultInstance();
                    String fieldForSort = sort.getCurrentSort() == SortType.POPULARITY ? POPULARITY_FIELD : RATING_FIELD;
                    RealmResults<MovieDb> results = realm
                            .where(MovieDb.class)
                            .findAllSorted(fieldForSort, Sort.DESCENDING);
                    
                    List<Movie> movies = new ArrayList<>(results.size());
                    for (MovieDb movieDb : results) {
                        Movie movie = Movie.createFromDbo(movieDb);
                        movies.add(movie);
                    }

                    singleSubscriber.onSuccess(movies);
                });
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
