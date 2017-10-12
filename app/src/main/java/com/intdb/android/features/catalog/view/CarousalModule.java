package com.intdb.android.features.catalog.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.intdb.android.R;
import com.intdb.android.features.catalog.view.adapter.MoviesAdapter;
import com.intdb.android.features.catalog.view.impl.CatalogActivity;
import com.intdb.android.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public class CarousalModule {

    private static final int LAST_PAGE = 900;
    private static final int MOVIES_PAGE_SIZE = 20;

    private final CatalogActivity mActivity;
    private final View mCarousalView;
    private final String mTitle;
    private final String mSortBy;
    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;
    private List<Movie> mList = new ArrayList<>();

    LinearLayoutManager mLayoutManager;

    public CarousalModule(CatalogActivity context, View carousalView, String title, String sortBy) {
        mActivity = context;
        mCarousalView = carousalView;
        mTitle = title;
        mSortBy = sortBy;
        initializeList();
    }

    private void initializeList() {
        ((TextView)mCarousalView.findViewById(R.id.textView_carousel_title)).setText(mTitle);

        mRecyclerView = mCarousalView.findViewById(R.id.recyclerView_movies);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MoviesAdapter(mActivity.getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);

        // Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
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

            if (!isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= MOVIES_PAGE_SIZE) {
                    mActivity.loadMoreItems(getNextPageNumber());
                }
            }
        }
    };

    private int getNextPageNumber() {
        return (mList.size() / MOVIES_PAGE_SIZE) + 1;
    }

    private boolean isLastPage() {
        return mList.size() / MOVIES_PAGE_SIZE == LAST_PAGE;
    }

    public void loadList(List<Movie> list) {
        mList.addAll(list);
        showList();
    }

    private void showList() {
        mAdapter.clearList();
        mAdapter.addMovies(mList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public String getSortBy() {
        return mSortBy;
    }

    public void showLoading() {
        mCarousalView.findViewById(R.id.textView_carousel_loading).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        mCarousalView.findViewById(R.id.textView_carousel_loading).setVisibility(View.GONE);
    }
}
