package com.artlite.ckconcept.helpers.image;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.artlite.bslibrary.managers.BSContextManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Class which provide the helping functional which related to {@link ImageView}
 */

public final class KitImageHelper extends BSBaseHelper {

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url) {
        final String methodName = "load(imageView, url)";
        load(imageView, url, new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e,
                                       String model,
                                       Target<GlideDrawable> target,
                                       boolean isFirstResource) {
                log(null, methodName, e, null);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource,
                                           String model,
                                           Target<GlideDrawable> target,
                                           boolean isFromMemoryCache,
                                           boolean isFirstResource) {
                log(null, methodName, null, "Image loaded: " + resource);
                if (imageView != null) {
                    imageView.setImageDrawable(resource);
                }
                return false;
            }
        });
    }

    /**
     * Method which provide the loading of the image by it {@link String} value of the
     * URL for instance of the {@link ImageView}
     *
     * @param imageView instance of the {@link ImageView}
     * @param url       {@link String} value of the image URL
     */
    public static void load(@Nullable final ImageView imageView,
                            @Nullable final String url,
                            @Nullable final RequestListener<String, GlideDrawable> callback) {
        final String methodName = "load(imageView, url, callback)";
        if (isNull(imageView, url)) {
            return;
        }
        Glide.with(BSContextManager.getRegisteredContext())
                .load(url)
                .centerCrop()
                .fitCenter()
                .listener(callback)
                .into(imageView);
    }
}
