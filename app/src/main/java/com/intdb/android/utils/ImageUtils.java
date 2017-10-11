package com.intdb.android.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.intdb.android.BuildConfig;

import timber.log.Timber;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public class ImageUtils {

    public static void loadImage(Context context, ImageView imageView, final ProgressBar progressBar, String path) {

        Timber.e("Path: "+ ImageUtils.getPosterUrl(path));

        Glide.with(context)
                .load(ImageUtils.getPosterUrl(path))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);
    }

    private static String getPosterUrl(String posterPath) {
        return BuildConfig.SUFFIX_IMAGE_URL + posterPath;
    }
}
