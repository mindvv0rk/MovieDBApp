package com.ai.moviedbapp.entities;

import com.ai.moviedbapp.entities.db.MovieDb;

public final class MovieDetails {

    private long mId;
    private String mName;
    private String mDescription;
    private double mRating;
    private String mReleaseDate;
    private byte[] mPoster;

    public static MovieDetails createFromMovieDb(MovieDb movieDb) {
        return new MovieDetails(movieDb.getId(), movieDb.getName(), movieDb.getDescription(),
                movieDb.getRating(), movieDb.getReleaseDate(), movieDb.getPoster());
    }

    public MovieDetails(long id, String name, String description, double rating, String releaseDate, byte[] poster) {
        mId = id;
        mName = name;
        mDescription = description;
        mRating = rating;
        mReleaseDate = releaseDate;
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

    public String getRating() {
        return String.valueOf(mRating);
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public byte[] getPoster() {
        return mPoster;
    }
}
