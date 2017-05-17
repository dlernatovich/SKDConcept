package com.artlite.ckconcept.callbacks;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;

import java.util.List;

/**
 * Callback which provide the getting of the server data
 */

public interface OnKitActionCallback {
    /**
     * Method which provide the action when data is received
     *
     * @param context instance of {@link Context}
     * @param offset  {@link Integer} value of the offset
     * @param items   {@link List} of the {@link BaseObject}
     */
    void onDataReceived(@NonNull final Context context,
                        int offset,
                        @NonNull final List<BaseObject> items);

    /**
     * Method which provide the performing of the error when the data received
     *
     * @param context instance of {@link Context}
     * @param offset  {@link Integer} value of the offset
     * @param error   instance of {@link Throwable}
     */
    void onServerError(@NonNull final Context context,
                       int offset,
                       @NonNull final Throwable error);
}
