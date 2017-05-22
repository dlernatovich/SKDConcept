package com.artlite.ckconcept.constants;

import android.support.annotation.NonNull;

/**
 * Class which provide the type for the {@link com.artlite.ckconcept.models.widget.KitWidgetModel}
 */

public enum KitWidgetType {
    CHANNEL(createName("Channel"));

    /**
     * {@link String} constants of the prefix for the {@link KitWidgetType}
     */
    private static final String K_PREFIX = "Widget:";

    /**
     * {@link String} value of the type
     */
    private final String value;

    /**
     * Constructor which provide the creating of the {@link KitWidgetType} from {@link String} value
     *
     * @param value {@link String} value of the type
     */
    KitWidgetType(String value) {
        this.value = value;
    }

    /**
     * Method which provide the getting of the {@link String} value of the type
     *
     * @return {@link String} value of the type
     */
    public String getValue() {
        return value;
    }

    /**
     * Method which provide the create {@link String} value of the name for the {@link KitWidgetType}
     *
     * @param name {@link String} value of the name
     * @return {@link String} value of the name
     */
    @NonNull
    private static final String createName(@NonNull final String name) {
        return String.format("%s%s", K_PREFIX, name);
    }
}
