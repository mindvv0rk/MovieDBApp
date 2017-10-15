package com.ai.moviedbapp.entities;

import com.ai.moviedbapp.core.FormViewState;

import android.support.annotation.IntDef;

public class Sort {

    private static final String POPULAR_SORT = "popularity.desc";
    private static final String TOP_RATED_SORT = "vote_average.desc";

    private @SortType int mCurrentSort = SortType.POPULARITY;

    public int getCurrentSort() {
        return mCurrentSort;
    }

    public void setCurrentSort(@SortType int currentSort) {
        mCurrentSort = currentSort;
    }

    public void changeSortType() {
        mCurrentSort = mCurrentSort == SortType.POPULARITY ? SortType.RATING : SortType.POPULARITY;
    }

    public String getParamForCurrentSort() {
        return mCurrentSort == SortType.POPULARITY ? POPULAR_SORT : TOP_RATED_SORT;
    }

    @IntDef({SortType.POPULARITY, SortType.RATING})
    public @interface SortType {
        int POPULARITY = 0;
        int RATING = 1;

    }

}
