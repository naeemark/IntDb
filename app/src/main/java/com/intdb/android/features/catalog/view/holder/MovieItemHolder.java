package com.intdb.android.features.catalog.view.holder;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.intdb.android.R;
import com.intdb.android.features.details.ItemDetailsActivity;
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

    @BindView(R.id.container_image)
    protected View mImageContainer;
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
        Intent intent = new Intent(mContext, ItemDetailsActivity.class);
        intent.putExtra(ItemDetailsActivity.EXTRA_MOVIE, mMovie);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, mImageContainer, ItemDetailsActivity.TRANSITION);
            mContext.startActivity(intent, options.toBundle());
        } else {
            mContext.startActivity(intent);
        }
    }

}