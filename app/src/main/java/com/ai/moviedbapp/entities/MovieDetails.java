package com.ai.moviedbapp.entities;

public final class MovieDetails {

    private long mId;
    private String mName;
    private String mDescription;
    private double mRating;
    private String mReleaseDate;
    private byte[] mPoster;



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

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public byte[] getPoster() {
        return mPoster;
    }
}
