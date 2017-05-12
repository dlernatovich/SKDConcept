package com.artlite.ckconcept.models.menu;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Class which provide the container for the menu item
 */

public final class KitMenuModel {
    /**
     * {@link String} value of the text
     */
    private final String text;

    /**
     * Instance of {@link Class}
     */
    private final String ownerClass;

    /**
     * {@link String} value of the caller class name
     */
    private final String callerClassName;

    /**
     * {@link Boolean} value which provide to define if menu is need to show
     */
    private final boolean isNeedShow;

    /**
     * Constructor which provide to create the {@link KitMenuModel} from
     *
     * @param text   {@link String} value of the text
     * @param owner  instance of {@link Class}
     * @param caller instance of {@link Class}
     */
    public KitMenuModel(@Nullable final String text,
                        @NonNull final Class owner,
                        @NonNull final Class caller) {
        this(text, owner.getSimpleName(), caller.getSimpleName());
    }

    /**
     * Constructor which provide to create the {@link KitMenuModel} from
     *
     * @param text   {@link String} value of the text
     * @param owner  {@link String} value of the class simple name
     * @param caller {@link String} value of caller name
     */
    public KitMenuModel(@Nullable final String text,
                        @NonNull final String owner,
                        @NonNull final String caller) {
        this.text = text;
        this.ownerClass = (owner == null) ? "" : owner;
        this.callerClassName = (caller == null) ? "" : caller;
        this.isNeedShow = (text != null);
    }

    /**
     * Method which provide to getting of the {@link String} value of the text
     *
     * @return {@link String} value of the text
     */
    @Nullable
    public String getText() {
        return text;
    }

    /**
     * Method which provide to getting of the instance of the owner {@link Class}
     *
     * @return {@link String} value of the class simple name
     */
    @NonNull
    public String getOwnerClass() {
        return ownerClass;
    }

    /**
     * Method which provide the getting of the caller class name
     *
     * @return {@link String} value of the class simple name
     */
    @NonNull
    public String getCallerClass() {
        return callerClassName;
    }
}
