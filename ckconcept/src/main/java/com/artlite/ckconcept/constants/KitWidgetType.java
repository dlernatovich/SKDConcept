package com.artlite.ckconcept.constants;

/**
 * Created by dlernatovich on 5/15/2017.
 */

public enum KitWidgetType {
    CHANNEL("KIT_CHANNEL");

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
}
