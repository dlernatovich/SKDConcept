package com.artlite.ckconcept.helpers;

import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.validation.BSValidationHelper;

/**
 * Helper which provide the functional for name forming
 */

public class KitNameHelper {
    /**
     * {@link String} value for the formatting of the type for class
     */
    public static final String K_CLASSES_TYPE = "ClassTypeByName:%s";

    /**
     * Method which provide the getting of the formatted {@link String} type from class
     *
     * @param aClass instance of {@link Class}
     * @return formatted {@link String} type from class
     */
    @Nullable
    public static String getClassType(@Nullable final Class aClass) {
        if (BSValidationHelper.validateNull(aClass)) {
            return String.format(K_CLASSES_TYPE, aClass.getName());
        }
        return null;
    }
}
