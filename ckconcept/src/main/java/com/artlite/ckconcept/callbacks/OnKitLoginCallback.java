package com.artlite.ckconcept.callbacks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.magnet.max.android.User;

/**
 * Callback which provide the functional for
 * the {@link com.artlite.ckconcept.ui.views.login.KitLoginView}
 */

public interface OnKitLoginCallback {

    /**
     * Method which provide the functional when login is successed
     *
     * @param user instance of {@link User}
     */
    void onLoginSuccess(@Nullable final User user);

    /**
     * Method which provide the action when login error
     *
     * @param throwable instance of {@link Throwable}
     */
    void onLoginError(@NonNull final Throwable throwable);
}
