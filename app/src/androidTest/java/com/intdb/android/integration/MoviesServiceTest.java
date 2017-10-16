package com.intdb.android.integration;

import com.intdb.android.BuildConfig;
import com.intdb.android.model.response.MoviesListResponse;
import com.intdb.android.webapi.MoviesApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static com.intdb.android.constants.SortBy.POPULARITY;
import static junit.framework.Assert.assertTrue;

public class MoviesServiceTest {

    private MoviesApiService mApiService;

    @Before
    public void setUp() throws Exception {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        mApiService = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build().create(MoviesApiService.class);
    }

    @Test
    public void checkGetPlaces() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        Observable<MoviesListResponse> observable = mApiService.getMovies(POPULARITY, String.valueOf(1));

        observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesListResponse>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MoviesListResponse response) {
                        Timber.e(response.getResults().size() + "");
                        assertTrue(response.getResults().size() > 0);
                        signal.countDown();

                    }
                });
        signal.await();
    }
}
