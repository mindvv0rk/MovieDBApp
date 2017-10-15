package com.ai.moviedbapp.movies;

import com.ai.moviedbapp.R;
import com.ai.moviedbapp.databinding.MovieItemBinding;
import com.ai.moviedbapp.entities.Movie;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies;

    public MoviesAdapter() {
        mMovies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_item, parent, false);
        return new MovieViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MovieViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.setData(movie);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieItemBinding mBinding;

        public MovieViewHolder(View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);
        }

        public void setData(Movie movie) {
            if (movie.getBitmap() != null) {
                mBinding.poster.setImageBitmap(movie.getBitmap());
            } else {
                Bitmap bitmap = decodeBitmap(movie.getPoster());
                if (bitmap != null) {
//                    movie.setBitmap(bitmap);
                    mBinding.poster.setImageBitmap(bitmap);
                }
            }

            mBinding.title.setText(movie.getName());

        }

        private Bitmap decodeBitmap(byte[] bytes) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
    }
}
