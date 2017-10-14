package com.ai.moviedbapp.entities.db;

import com.ai.moviedbapp.entities.responses.MovieResponse;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class MovieDb extends RealmObject {

    @PrimaryKey
    private long mId;
    private String mName;
    private String mDescription;
    private double mRating;
    private double mPopularity;
    private String mReleaseDate;
    private String mPosterPath;
    private byte[] mPoster;

    public static MovieDb createFromServerMovie(MovieResponse.Movie movie) {
        return new MovieDb(movie.getId(), movie.getName(), movie.getDescription(),
                movie.getVote(), movie.getPopularity(), movie.getReleaseDate(), movie.getPosterPath());
    }

    public static MovieDb createFormRealmObject(MovieDb realmMovie) {
        return new MovieDb(realmMovie.getId(), realmMovie.getName(),
                realmMovie.getDescription(), realmMovie.getRating(), realmMovie.getPopularity(),
                realmMovie.getReleaseDate(), realmMovie.getPosterPath(), realmMovie.getPoster());
    }

    public MovieDb() {
    }

    public MovieDb(long id, String name, String description, double rating, double popularity,
                   String releaseDate, String posterPath) {
        mId = id;
        mName = name;
        mDescription = description;
        mRating = rating;
        mPopularity = popularity;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
    }

    public MovieDb(long id, String name, String description, double rating, double popularity, String releaseDate, String posterPath, byte[] poster) {
        mId = id;
        mName = name;
        mDescription = description;
        mRating = rating;
        mPopularity = popularity;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
        mPoster = poster;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPoster(byte[] poster) {
        mPoster = poster;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getRating() {
        return mRating;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public byte[] getPoster() {
        return mPoster;
    }
}
