package com.artlite.ckconcept.callbacks;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.bslibrary.ui.view.BSView.Event;

/**
 * Class which provide the event callback from {@link BSView} that can be showed as {@link Dialog}
 */

public abstract class OnKitEventCallback implements BSView.OnDialogCallback {
    /**
     * Method which provide the functional when the {@link Dialog} is showing
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     */
    @Override
    public void onShow(@NonNull Context context, @NonNull BSView view) {

    }

    /**
     * Method which provide the functional when {@link Dialog} is onCancel
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     */
    @Override
    public void onCancel(@NonNull Context context, @NonNull BSView view) {

    }

    /**
     * Method which provide the action when dialog is closed
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     */
    @Override
    public void onClose(@NonNull Context context, @NonNull BSView view) {

    }
}
