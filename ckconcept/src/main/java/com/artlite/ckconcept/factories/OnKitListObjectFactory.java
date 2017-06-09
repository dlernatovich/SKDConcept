package com.artlite.ckconcept.factories;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.models.list.KitBaseListObject;

/**
 * Factory which provide the create of the {@link KitBaseListObject}
 */

public interface OnKitListObjectFactory<T extends Parcelable> {

    /**
     * Method which provide the create of the {@link KitBaseListObject} from the instance of the
     * {@link Parcelable} object
     *
     * @param object instance of the {@link Parcelable} object
     * @return instance of the {@link KitBaseListObject}
     */
    KitBaseListObject create(@Nullable final T object);

}
