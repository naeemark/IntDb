package com.intdb.android.features.catalog.view.impl;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.intdb.android.R;
import com.intdb.android.features.catalog.presenter.CarousalPresenter;
import com.intdb.android.features.catalog.view.CarousalModule;
import com.intdb.android.features.catalog.view.adapter.MoviesAdapter;
import com.intdb.android.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public class CarousalModuleImpl implements CarousalModule {

    private static final int LAST_PAGE = 900;
    private static final int MOVIES_PAGE_SIZE = 20;

    private final CatalogActivity mActivity;
    private final View mCarousalView;
    private final String mTitle;
    private final String mSortBy;
    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;
    private List<Movie> mList = new ArrayList<>();

    private LinearLayoutManager mLayoutManager;

    private CarousalPresenter mPresenter;

    public CarousalModuleImpl(CatalogActivity activity, View carousalView, String title, String sortBy) {
        mActivity = activity;
        mCarousalView = carousalView;
        mTitle = title;
        mSortBy = sortBy;
        initializeList();
    }

    @Override
    public void setPresenter(CarousalPresenter presenter) {
        mPresenter = presenter;
    }

    /**
     * Initialized Elements for a single Carousal
     */
    private void initializeList() {
        ((TextView) mCarousalView.findViewById(R.id.textView_carousel_title)).setText(mTitle);

        mRecyclerView = mCarousalView.findViewById(R.id.recyclerView_movies);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MoviesAdapter(mActivity.getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);

        // Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }


    /**
     * includes utility functions required for pagination
     */
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

            if (!mPresenter.isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= MOVIES_PAGE_SIZE) {
                    loadNextPage();
                }
            }
        }

        private void loadNextPage() {
            mPresenter.fetchCarousalPage(getNextPageNumber());
        }

        private int getNextPageNumber() {
            return (mList.size() / MOVIES_PAGE_SIZE) + 1;
        }

        private boolean isLastPage() {
            return mList.size() / MOVIES_PAGE_SIZE == LAST_PAGE;
        }

    };

    @Override
    public void loadList(List<Movie> list) {
        mList.addAll(list);
        showList();
    }

    /**
     * resets list of Adapter
     */
    private void showList() {
        mAdapter.clearList();
        mAdapter.addMovies(mList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public String getSortBy() {
        return mSortBy;
    }

    /**
     * Using to show loading status
     */
    @Override
    public void showLoading() {
        mCarousalView.findViewById(R.id.textView_carousel_loading).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mCarousalView.findViewById(R.id.textView_carousel_loading).setVisibility(View.GONE);
    }

    /**
     * Trigers network error by the activity
     */
    @Override
    public void showNetworkError() {
        mActivity.showNetworkError();
    }

    @Override
    public void reload() {
        mList.clear();
        mPresenter.fetchCarousalInitialPage();
    }
}
