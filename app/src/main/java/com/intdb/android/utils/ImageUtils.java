package com.intdb.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.intdb.android.BuildConfig;
import com.intdb.android.R;

import timber.log.Timber;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 12/10/2017
 */

public class ImageUtils {

    public static void loadImage(final Context context, final ImageView imageView, final ProgressBar progressBar, String path) {

        String url = ImageUtils.getPosterUrl(path);
        Timber.e("Path: " + url);

        Glide.with(context)
                .load((path == null) ? R.mipmap.ic_launcher_round : url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap arg0, GlideAnimation<? super Bitmap> arg1) {
                        progressBar.setVisibility(View.GONE);
                        imageView.setImageBitmap(arg0);
                    }
                });
    }

    private static String getPosterUrl(String posterPath) {
        return BuildConfig.SUFFIX_IMAGE_URL + posterPath;
    }
}
