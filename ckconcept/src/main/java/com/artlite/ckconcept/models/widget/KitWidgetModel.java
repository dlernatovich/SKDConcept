package com.artlite.ckconcept.models.widget;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.ui.abs.details.KitBaseDetailsView;

/**
 * Class which provide the container functional for the widget
 */

public abstract class KitWidgetModel<T> implements KitInterface<T> {


    /**
     * Instance of {@link Object}
     */
    private T object;

    /**
     * Constructor which provide the create of the {@link KitWidgetModel}
     */
    public KitWidgetModel() {
        this.object = null;
    }

    /**
     * Constructor which provide the create of the {@link KitWidgetModel} from instance of
     * {@link Object}
     *
     * @param object instance of {@link Object}
     */
    public KitWidgetModel(@Nullable final T object) {
        this.object = object;
    }

    /**
     * Method which provide the getting of the instance of {@link Object}
     *
     * @return instance of {@link Object}
     */
    @Nullable
    @Override
    public T getObject() {
        return object;
    }

    /**
     * Method which provide the getting view for details
     *
     * @param context instance of {@link Context}
     * @param object
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public KitBaseDetailsView getViewDetails(@NonNull Context context,
                                             @Nullable T object) {
        return null;
    }

    /**
     * Method which provide the checking if widget need to have of the details view
     *
     * @return checking if widget need to have of the create view
     */
    @Override
    public boolean isNeedDetailsView() {
        return false;
    }
}
