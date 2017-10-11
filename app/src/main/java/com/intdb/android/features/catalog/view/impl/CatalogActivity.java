package com.intdb.android.features.catalog.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intdb.android.R;
import com.intdb.android.app.injection.AppComponent;
import com.intdb.android.app.presenter.loader.PresenterFactory;
import com.intdb.android.app.view.impl.BaseActivity;
import com.intdb.android.features.catalog.injection.CatalogViewModule;
import com.intdb.android.features.catalog.injection.DaggerCatalogViewComponent;
import com.intdb.android.features.catalog.presenter.CatalogPresenter;
import com.intdb.android.features.catalog.view.CatalogView;
import com.intdb.android.features.catalog.view.adapter.MoviesAdapter;
import com.intdb.android.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public final class CatalogActivity extends BaseActivity<CatalogPresenter, CatalogView> implements CatalogView {
    private static final int LAST_PAGE = 900;
    private static final int MOVIES_PAGE_SIZE = 20;
    @Inject
    PresenterFactory<CatalogPresenter> mPresenterFactory;

    @BindView(R.id.recyclerView_movies)
    protected RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private String[] yearsRange;
    private List<Movie> mMoviesList = new ArrayList<>();
    private boolean isLoading;

    LinearLayoutManager mLayoutManager;


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerCatalogViewComponent.builder()
                .appComponent(parentComponent)
                .catalogViewModule(new CatalogViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CatalogPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_catalog;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        initializeList();
    }

    private void initializeList() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMoviesAdapter = new MoviesAdapter(getLayoutInflater());
        mRecyclerView.setAdapter(mMoviesAdapter);

        // Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void loadList() {
        assert mPresenter != null;
        mPresenter.loadMoviesPage(getNextPageNumber());
    }

    @Override
    public void loadList(List<Movie> list) {
        mMoviesList.addAll(list);
        showList();
    }
    private void showList() {
        mMoviesAdapter.clearList();
        mMoviesAdapter.addMovies(mMoviesList);
        mMoviesAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void loadMoreItems() {
        Timber.e("Load More...");
        loadList();
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= MOVIES_PAGE_SIZE) {
                    loadMoreItems();
                }
            }
        }
    };

    private int getNextPageNumber() {
        return (mMoviesList.size() / MOVIES_PAGE_SIZE) + 1;
    }

    private boolean isLastPage() {
        return mMoviesList.size() / MOVIES_PAGE_SIZE == LAST_PAGE;
    }

}
