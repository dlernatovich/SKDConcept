package com.artlite.ckconcept.callbacks;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;

import java.util.List;

/**
 * Callback which provide the getting of the server data
 */

public interface OnKitActionCallback {
    /**
     * Method which provide the action when data is received
     *
     * @param context instance of {@link Context}
     */
    void onDataReceived(@NonNull final Context context,
                        @NonNull final List<BaseRecyclerItem> items);
}
