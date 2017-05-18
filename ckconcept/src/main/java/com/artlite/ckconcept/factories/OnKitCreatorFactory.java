package com.artlite.ckconcept.factories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.models.widget.KitWidgetModel;

/**
 * Interface which provide the factory callback for the {@link KitWidgetModel}
 */

public interface OnKitCreatorFactory<T> {

    /**
     * Method which provide the create {@link KitWidgetModel} from
     *
     * @param object instance of {@link Object}
     * @return instance of {@link KitWidgetModel}
     */
    @Nullable
    KitWidgetModel create(@Nullable final T object);

    /**
     * Method which provide the create of the {@link KitWidgetModel} for first initialization
     *
     * @return instance of the {@link KitWidgetModel}
     */
    @NonNull
    KitWidgetModel createForRegistration();
}
