package com.intdb.android.features.catalog.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intdb.android.R;
import com.intdb.android.features.catalog.view.holder.MovieItemHolder;
import com.intdb.android.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieItemHolder> {

    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private LayoutInflater mLayoutInflater;

    private List<Movie> mMovieList = new ArrayList<>();

    public MoviesAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
     }

    @Override
    public MovieItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.movie_list_item_layout, parent, false);
        return new MovieItemHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieItemHolder holder, int position) {
        holder.setValues(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void clearList() {
        mMovieList.clear();
        notifyDataSetChanged();
    }

    public void addMovies(List<Movie> movies) {
        mMovieList.addAll(movies);
        notifyDataSetChanged();
    }
}

