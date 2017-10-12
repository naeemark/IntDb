package com.intdb.android.features.catalog.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.intdb.android.R;
import com.intdb.android.features.catalog.view.impl.CatalogActivity;
import com.intdb.android.model.Movie;
import com.intdb.android.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */


public class MovieItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.imageView_poster)
    protected ImageView mLogoImageView;
    @BindView(R.id.img_progress)
    protected ProgressBar imageProgressBar;

    private Context mContext;
    private Movie mMovie;


    public MovieItemHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        setIsRecyclable(false);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setValues(Movie movie) {
        mMovie = movie;

        ImageUtils.loadImage(mContext, mLogoImageView, imageProgressBar,  mMovie.getPosterPath());
    }

    @Override
    public void onClick(View v) {
        ((CatalogActivity)mContext).showToast(mMovie.getTitle() + " clicked!");
    }

}