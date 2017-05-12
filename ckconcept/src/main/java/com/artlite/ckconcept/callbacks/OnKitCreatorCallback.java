package com.artlite.ckconcept.callbacks;

import android.support.annotation.Nullable;

import com.artlite.ckconcept.models.widget.KitWidgetModel;

/**
 * Interface which provide the creator callback for the {@link KitWidgetModel}
 */

public interface OnKitCreatorCallback<T> {

    /**
     * Method which provide the create {@link KitWidgetModel} from
     *
     * @param object instance of {@link Object}
     * @return instance of {@link KitWidgetModel}
     */
    @Nullable
    KitWidgetModel create(@Nullable final T object);
}
