package com.ai.moviedbapp.entities;

import com.ai.moviedbapp.entities.db.MovieDb;

import android.graphics.Bitmap;

public final class Movie {

    private long mId;
    private String mName;
    private String mPosterPath;

    public Movie(long id, String name, String posterPath) {
        mId = id;
        mName = name;
        mPosterPath = posterPath;
    }

    public static Movie createFromDbo(MovieDb movieDb) {
        return new Movie(movieDb.getId(), movieDb.getName(), movieDb.getPosterPath());
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPosterPath() {
        return mPosterPath;
    }
}

