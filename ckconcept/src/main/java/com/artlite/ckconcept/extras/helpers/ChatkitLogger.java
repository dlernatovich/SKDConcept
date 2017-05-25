package com.artlite.ckconcept.extras.helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dlernatovich on 11/17/2016.
 */

public final class ChatkitLogger {

    private static final String K_ERROR_TAG = "[ERROR]:";
    private static final String K_INFO_TAG = "[INFO]:";

    /**
     * Method which provide the show error message
     *
     * @param methodName method name
     * @param owner      owner
     * @param error      error
     * @param object     object
     */
    public static void error(@Nullable final String methodName,
                             @Nullable final Object owner,
                             @Nullable final Throwable error,
                             @Nullable final Object object) {
        final String message = getMessage(methodName, owner, error, object);
        if (Log.isLoggable(K_ERROR_TAG, Log.ERROR) == true) {
            Log.e(K_ERROR_TAG, message);
        }
    }

    /**
     * Method which provide the show information message
     *
     * @param methodName method name
     * @param owner      owner
     * @param error      error
     * @param object     object
     */
    public static void info(@Nullable final String methodName,
                            @Nullable final Object owner,
                            @Nullable final Throwable error,
                            @Nullable final Object object) {
        final String message = getMessage(methodName, owner, error, object);
        if (Log.isLoggable(K_ERROR_TAG, Log.INFO) == true) {
            Log.d(K_INFO_TAG, message);
        }
    }

    /**
     * Method which provide the message getting
     *
     * @param methodName method name
     * @param owner      owner
     * @param error      error
     * @param object     object
     * @return formatted message
     */
    @NonNull
    private static String getMessage(@Nullable final String methodName,
                                     @Nullable final Object owner,
                                     @Nullable final Throwable error,
                                     @Nullable final Object object) {
        final StringBuilder builder = new StringBuilder();
        if ((methodName != null) && (methodName.isEmpty() == false)) {
            builder.append("\n\tMETHOD:\t").append(methodName);
        }
        if (owner != null) {
            builder.append("\n\tOWNER:\t").append(owner.toString());
        }
        if (error != null) {
            builder.append("\n\tERROR:\t").append(error.toString());
        }
        if (object != null) {
            builder.append("\n\tOBJECT:\t").append(object.toString());
        }
        return builder.toString().trim();
    }
}
