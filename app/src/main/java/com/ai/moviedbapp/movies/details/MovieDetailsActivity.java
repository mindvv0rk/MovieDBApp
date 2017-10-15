package com.ai.moviedbapp.movies.details;

import com.ai.moviedbapp.R;
import com.ai.moviedbapp.core.FormViewState;
import com.ai.moviedbapp.core.PresenterManager;
import com.ai.moviedbapp.databinding.ActivityMovieDetailsBinding;
import com.ai.moviedbapp.entities.MovieDetails;
import com.ai.moviedbapp.movies.MovieActivity;
import com.ai.moviedbapp.presenter.MovieDetailsPresenter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class MovieDetailsActivity extends AppCompatActivity implements IMovieDetailsView {
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    private static final long INVALID_MOVIE_ID = -1;
    private static final String MOVIE_ID_KEY = "keys:movieId";

    private ActivityMovieDetailsBinding mBinding;
    private int mPresenterId;
    private MovieDetailsPresenter mPresenter;

    public static void start(Context context, long movieId) {
        Intent starter = new Intent(context, MovieDetailsActivity.class);
        starter.putExtra(MOVIE_ID_KEY, movieId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        long movieId = getIntent().getLongExtra(MOVIE_ID_KEY, INVALID_MOVIE_ID);
        if (movieId == INVALID_MOVIE_ID) {
            //show error
        }

        mPresenterId = (MovieDetailsActivity.class.getSimpleName() + movieId).hashCode();
        mPresenter = PresenterManager.getPresenter(mPresenterId, () -> new MovieDetailsPresenter(movieId));

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        mPresenter.detachView();
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
    }

    @Override
    public void showMovie(MovieDetails movie) {
        mBinding.setState(FormViewState.SUCCESS);
        mBinding.setMovie(movie);
        byte[] posterBytes = movie.getPoster();
        Bitmap poster = BitmapFactory.decodeByteArray(posterBytes, 0, posterBytes.length);
        mBinding.poster.setImageBitmap(poster);
    }
}
