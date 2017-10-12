package com.intdb.android.webapi;

import com.intdb.android.BuildConfig;
import com.intdb.android.model.response.MoviesListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 */

public interface MoviesApiService {

    // https://api.themoviedb.org/3/discover/movie?
    // api_key=114fe6670282f6a632638661e5e86dee&
    // language=en-US&
    // sort_by=popularity.desc&
    // include_adult=false&
    // include_video=false&
    // page=1

    @GET("discover/movie?api_key="+ BuildConfig.API_KEY+"&language=en_US")
    Observable<MoviesListResponse> getMovies(@Query("sort_by") String sortBy, @Query("page") String pageNumber);

}
