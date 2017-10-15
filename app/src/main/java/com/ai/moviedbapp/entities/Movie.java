package com.ai.moviedbapp.entities;

import com.ai.moviedbapp.entities.db.MovieDb;

import android.graphics.Bitmap;

public final class Movie {

    private long mId;
    private String mName;
    private byte[] mPoster;
    private Bitmap mBitmap;

    public Movie(long id, String name, byte[] poster) {
        mId = id;
        mName = name;
        mPoster = poster;
    }

    public static Movie createFromDbo(MovieDb movieDb) {
        return new Movie(movieDb.getId(), movieDb.getName(), movieDb.getPoster());
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public byte[] getPoster() {
        return mPoster;
    }
}
