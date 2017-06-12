package com.artlite.ckconcept.models.define;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Class which provide the definition for the object type
 */

public abstract class KitBaseDefiner {

    /**
     * Instance of the {@link Class}
     */
    private final Class callerClass;

    /**
     * Default constructor for the {@link KitBaseDefiner}
     *
     * @param callerClass instance of the {@link Class}
     */
    public KitBaseDefiner(@NonNull final Class callerClass) {
        this.callerClass = callerClass;
    }

    /**
     * Method which provide the define functional for the {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    @Nullable
    public abstract String define(@Nullable final Object object);

    /**
     * Method which provide the getting of the instance of the {@link Class}
     *
     * @return instance of the {@link Class}
     */
    @NonNull
    public Class getObjectClass() {
        return callerClass;
    }
}
