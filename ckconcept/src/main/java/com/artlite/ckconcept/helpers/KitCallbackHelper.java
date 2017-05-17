package com.artlite.ckconcept.helpers;

import android.content.Context;
import android.support.annotation.Nullable;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Method which provide the help functional for {@link OnKitActionCallback}
 */

public final class KitCallbackHelper extends BSBaseHelper {

    /**
     * Method which provide the performing of the success method in {@link OnKitActionCallback}
     *
     * @param callback instance of the {@link OnKitActionCallback}
     * @param context  instance of the {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param objects  {@link List} value of the objects
     * @see OnKitActionCallback#onDataReceived(Context, int, List)
     */
    public static void onSuccess(@Nullable final OnKitActionCallback callback,
                                 @Nullable final Context context,
                                 int offset,
                                 @Nullable final List<BaseObject> objects) {
        final List<BaseObject> result = (objects == null)
                ? new ArrayList<BaseObject>() : objects;
        if (BSValidationHelper.validateNull(callback)) {
            callback.onDataReceived(context, offset, result);
        }
    }

    /**
     * Method which provide the performing of the success method in {@link OnKitActionCallback}
     *
     * @param callback instance of the {@link OnKitActionCallback}
     * @param context  instance of the {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param error    instance of {@link Throwable}
     * @see OnKitActionCallback#onDataReceived(Context, int, List)
     */
    public static void onError(@Nullable final OnKitActionCallback callback,
                               @Nullable final Context context,
                               int offset,
                               @Nullable final Throwable error) {
        if (BSValidationHelper.validateNull(callback)) {
            callback.onServerError(context, offset, error);
        }
    }

}
