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
    public Movie insertOrUpdateMovie(MovieDb movie) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MovieDb realmMovie = realm.copyToRealmOrUpdate(movie);
        realm.commitTransaction();

        Movie presentableMovie = Movie.createFromDbo(realmMovie);

        realm.close();

        return presentableMovie;
    }

    @Override
    public Single<List<Movie>> getMovies(com.ai.moviedbapp.entities.Sort sort, int page, int pageSize) {
        return Single
                .create(singleSubscriber -> {
                    Realm realm = Realm.getDefaultInstance();
                    String fieldForSort = sort.getCurrentSort() == SortType.POPULARITY ? POPULARITY_FIELD : RATING_FIELD;
                    RealmResults<MovieDb> results = realm
                            .where(MovieDb.class)
                            .findAllSorted(fieldForSort, Sort.DESCENDING);

                    List<MovieDb> pageMovies = results.subList(
                            getStartPageIndex(page, pageSize),
                            getEndPageIndex(page, pageSize, results.size()));
                    List<Movie> movies = new ArrayList<>(pageMovies.size());
                    for (MovieDb movieDb : pageMovies) {
                        Movie movie = Movie.createFromDbo(movieDb);
                        movies.add(movie);
                    }

                    realm.close();

                    singleSubscriber.onSuccess(movies);
                });
    }

    private int getStartPageIndex(int page, int pageSize) {
        return pageSize * (page -1);
    }

    private int getEndPageIndex(int page, int pageSize, int totalSize) {
        int result = pageSize * page;
        if (result > totalSize) result = totalSize - 1;
        return result;
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
            realm.close();
            singleSubscriber.onSuccess(movieDetails);
        });
    }
}
