package com.ai.moviedbapp.entities;

import com.ai.moviedbapp.entities.db.MovieDb;

public final class MovieDetails {

    private long mId;
    private String mName;
    private String mDescription;
    private double mRating;
    private String mReleaseDate;
    private String mPosterPath;

    public static MovieDetails createFromMovieDb(MovieDb movieDb) {
        return new MovieDetails(movieDb.getId(), movieDb.getName(), movieDb.getDescription(),
                movieDb.getRating(), movieDb.getReleaseDate(), movieDb.getPosterPath());
    }

    public MovieDetails(long id, String name, String description, double rating, String releaseDate, String posterPath) {
        mId = id;
        mName = name;
        mDescription = description;
        mRating = rating;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
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

    public String getRating() {
        return String.valueOf(mRating);
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPosterPath() {
        return mPosterPath;
    }
}
