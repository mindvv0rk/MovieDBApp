package com.ai.moviedbapp.movies;

import com.ai.moviedbapp.R;
import com.ai.moviedbapp.core.FormViewState;
import com.ai.moviedbapp.core.PresenterManager;
import com.ai.moviedbapp.databinding.ActivityMoviesBinding;
import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.entities.Sort;
import com.ai.moviedbapp.movies.details.MovieDetailsActivity;
import com.ai.moviedbapp.presenter.MoviePresenter;
import com.ai.moviedbapp.view.GridSpaceItemDecorator;
import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemSpanLookup;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MovieActivity extends AppCompatActivity implements IMovieView, IMovieAdapterClickHandler,
        IRetryable, ISortClickHandler, Paginate.Callbacks {

    private static final String TAG = MovieActivity.class.getSimpleName();

    private static final int COLUMNS_COUNT = 2;

    private int mPresenterId;
    private MoviePresenter mMoviePresenter;
    private ActivityMoviesBinding mBinding;
    private MoviesAdapter mAdapter;

    private boolean isLoading;
    private boolean isLoadedAllItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies);
        mBinding.setRetryHandler(this);
        mBinding.setSortHandler(this);

        initRecycler();

        mPresenterId = MovieActivity.class.getSimpleName().hashCode();
        mMoviePresenter = PresenterManager.getPresenter(mPresenterId, MoviePresenter::new);
    }

    private void initRecycler() {
        mBinding.recycler.setLayoutManager(new GridLayoutManager(this, COLUMNS_COUNT));
        mAdapter = new MoviesAdapter(this);
        mBinding.recycler.setAdapter(mAdapter);

        Paginate.with(mBinding.recycler, this)
                .setLoadingTriggerThreshold(6)
                .addLoadingListItem(true)
                .setLoadingListItemSpanSizeLookup(() -> COLUMNS_COUNT)
                .build();


        int spaceSize = getResources().getDimensionPixelSize(R.dimen.grid_items_space);
        GridSpaceItemDecorator decorator = new GridSpaceItemDecorator(COLUMNS_COUNT, spaceSize, true);
        mBinding.recycler.addItemDecoration(decorator);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mMoviePresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        mMoviePresenter.detachView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            PresenterManager.removePresenter(mPresenterId);
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        mBinding.setState(FormViewState.LOADING);
    }

    @Override
    public void showError(String message) {
        mBinding.setState(FormViewState.ERROR);
        mBinding.error.setText(message);
    }

    @Override
    public void showData(List<Movie> movies, Sort sort) {
        mAdapter.setMovies(movies);
        mAdapter.notifyDataSetChanged();
        mBinding.setSortType(sort.getCurrentSort());
        mBinding.setState(FormViewState.SUCCESS);
    }

    @Override
    public void loadedAllItems() {
        isLoadedAllItems = true;
    }

    @Override
    public void showLoadingNextPage() {
        isLoading = true;
    }

    @Override
    public void showNextPage(List<Movie> movies) {
        if (movies != null) {
            mAdapter.setNextMovies(movies);
            mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(), movies.size());
        }
        isLoading = false;
    }

    @Override
    public void onMovieClick(View view, long movieId) {
        MovieDetailsActivity.start(this, movieId);
    }

    @Override
    public void onRetryClick(View view) {
        mMoviePresenter.reloadMovies();
    }

    @Override
    public void onSortButtonClick(View view) {
        mMoviePresenter.changeSortType();
    }

    @Override
    public void onLoadMore() {
        if (mMoviePresenter != null) mMoviePresenter.loadNextPage();
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return isLoadedAllItems;
    }
}
