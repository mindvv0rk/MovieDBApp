package com.ai.moviedbapp.movies;

import com.ai.moviedbapp.R;
import com.ai.moviedbapp.databinding.MovieItemBinding;
import com.ai.moviedbapp.entities.Movie;
import com.squareup.picasso.Picasso;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies;
    private IMovieAdapterClickHandler mClickHandler;

    public MoviesAdapter(IMovieAdapterClickHandler handler) {
        mMovies = new ArrayList<>();
        mClickHandler = handler;
    }

    public void setMovies(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
    }

    public void setNextMovies(List<Movie> movies) {
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
        holder.setData(movie, mClickHandler);
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

        public void setData(Movie movie, IMovieAdapterClickHandler handler) {
            mBinding.setHandler(handler);
            mBinding.setId(movie.getId());
            mBinding.title.setText(movie.getName());
            setPoster(movie.getPosterPath(), mBinding.poster);
        }

        private void setPoster(String posterPath, ImageView imageView) {
            Picasso.with(imageView.getContext())
                    .load(posterPath)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }
}
