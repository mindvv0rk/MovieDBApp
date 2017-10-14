package com.ai.moviedbapp.movies;

import com.ai.moviedbapp.R;
import com.ai.moviedbapp.core.PresenterManager;
import com.ai.moviedbapp.databinding.ActivityMovieBinding;
import com.ai.moviedbapp.presenter.MoviePresenter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieActivity extends AppCompatActivity implements IMovieView {

    private static final String TAG = MovieActivity.class.getSimpleName();

    private int mPresenterId;
    private MoviePresenter mMoviePresenter;
    private ActivityMovieBinding mBinding;
    private long start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

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
        mBinding.text.setText("loading");
        start = System.currentTimeMillis();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showData() {
        long result = System.currentTimeMillis() - start;
        mBinding.text.setText("loaded = " + (result/1000));
    }
}
