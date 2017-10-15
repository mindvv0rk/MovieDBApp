package com.ai.moviedbapp.movies;

import com.ai.moviedbapp.R;
import com.ai.moviedbapp.core.FormViewState;
import com.ai.moviedbapp.core.PresenterManager;
import com.ai.moviedbapp.databinding.ActivityMovieBinding;
import com.ai.moviedbapp.entities.Movie;
import com.ai.moviedbapp.presenter.MoviePresenter;
import com.ai.moviedbapp.view.GridSpaceItemDecorator;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

public class MovieActivity extends AppCompatActivity implements IMovieView {

    private static final String TAG = MovieActivity.class.getSimpleName();

    private static final int COLUMNS_COUNT = 2;

    private int mPresenterId;
    private MoviePresenter mMoviePresenter;
    private ActivityMovieBinding mBinding;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        mBinding.recycler.setLayoutManager(new GridLayoutManager(this, COLUMNS_COUNT));
        mAdapter = new MoviesAdapter();
        mBinding.recycler.setAdapter(mAdapter);

        int spaceSize = getResources().getDimensionPixelSize(R.dimen.grid_items_space);
        GridSpaceItemDecorator decorator = new GridSpaceItemDecorator(COLUMNS_COUNT, spaceSize, true);
        mBinding.recycler.addItemDecoration(decorator);

        mPresenterId = MovieActivity.class.getSimpleName().hashCode();
        mMoviePresenter = PresenterManager.getPresenter(mPresenterId, MoviePresenter::new);

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

    }

    @Override
    public void showData(List<Movie> movies) {
        mAdapter.setMovies(movies);
        mAdapter.notifyDataSetChanged();
        mBinding.setState(FormViewState.SUCCESS);
    }
}
