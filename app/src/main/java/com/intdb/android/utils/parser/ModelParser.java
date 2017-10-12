package com.intdb.android.utils.parser;

import com.intdb.android.model.Movie;
import com.intdb.android.model.response.MoviesListResponse;
import com.intdb.android.model.response.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

/**
 * A utility to provide parser
 */
@Singleton
public class ModelParser {

    @Inject
    public ModelParser() {
    }

    public List<Movie> mapMovies(MoviesListResponse moviesListResponse) {
        List<Movie> movieList = new ArrayList<>();

        if (moviesListResponse != null) {
            List<Result> results = moviesListResponse.getResults();
            if (results != null) {
                for (Result result : results) {
                    Movie movie = new Movie(
                            result.getId(),
                            result.getTitle(),
                            result.getOriginalTitle(),
                            result.getPosterPath(),
                            result.getBackdropPath(),
                            result.getOverview(),
                            result.getReleaseDate(),
                            result.getVoteAverage(),
                            result.getPopularity(),
                            result.getVoteCount()
                    );
                    movieList.add(movie);
                }
            }
        }
        return movieList;
    }
}
