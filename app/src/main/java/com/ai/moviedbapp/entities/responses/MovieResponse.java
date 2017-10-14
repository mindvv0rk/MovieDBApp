package com.ai.moviedbapp.entities.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class MovieResponse {

    @SerializedName("page")
    private int mPage;

    @SerializedName("total_results")
    private int mTotalResults;

    @SerializedName("total_pages")
    private int mTotalPages;

    @SerializedName("results")
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public int getPage() {
        return mPage;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public class Movie {
        @SerializedName("id")
        private long mId;

        @SerializedName("title")
        private String mName;

        @SerializedName("poster_path")
        private String mPosterPath;

        @SerializedName("overview")
        private String mDescription;

        @SerializedName("vote_average")
        private double mVote;

        @SerializedName("popularity")
        private double mPopularity;

        @SerializedName("release_date")
        private String mReleaseDate;

        public long getId() {
            return mId;
        }

        public String getName() {
            return mName;
        }

        public String getPosterPath() {
            return mPosterPath;
        }

        public String getDescription() {
            return mDescription;
        }

        public double getVote() {
            return mVote;
        }

        public double getPopularity() {
            return mPopularity;
        }

        public String getReleaseDate() {
            return mReleaseDate;
        }
    }


}
