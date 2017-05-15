package com.artlite.ckconcept.models.widget;

import android.support.annotation.Nullable;

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
}
