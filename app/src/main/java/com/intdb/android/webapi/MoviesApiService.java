package com.intdb.android.webapi;

import com.intdb.android.model.response.MoviesListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 */

public interface MoviesApiService {

    @GET("movie/popular?api_key=7a4a2330f51514657f80707a217bcf9d&language=en_US")
    Observable<MoviesListResponse> getMovies(@Query("page") String pageNumber);


}
