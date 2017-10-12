package com.intdb.android.features.details;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.intdb.android.R;
import com.intdb.android.model.Movie;
import com.intdb.android.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    public static final String TRANSITION = "TransitionName";

    @BindView(R.id.container_image)
    protected View mImageContainer;
    @BindView(R.id.img_progress)
    protected ProgressBar imageProgressBar;
    @BindView(R.id.imageView_banner)
    protected ImageView mLogoImage;
    @BindView(R.id.title)
    protected TextView mTitle;

    @BindView(R.id.released)
    protected TextView mReleaseDate;

    @BindView(R.id.textview_averageVotes)
    protected TextView mVoteAverage;
    @BindView(R.id.ratingBar)
    protected RatingBar ratingBar;

    @BindView(R.id.description)
    protected TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        showBackArrow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageContainer.setTransitionName(TRANSITION);
        }

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        mTitle.setText(movie.getTitle());
        mReleaseDate.setText(getString(R.string.prefix_released_date, movie.getReleaseDate()));

        mVoteAverage.setText(getString(R.string.suffix_rating, String.valueOf(movie.getVoteAverage())));
        ratingBar.setRating((float) (movie.getVoteAverage() / 2.0));

        mDescription.setText(movie.getOverview());

        ImageUtils.loadImage(this, mLogoImage, imageProgressBar, (movie.getBackdropPath()==null)? movie.getPosterPath(): movie.getBackdropPath());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
