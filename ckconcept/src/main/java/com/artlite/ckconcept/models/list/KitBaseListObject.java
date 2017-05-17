package com.artlite.ckconcept.models.list;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.adapteredrecyclerview.models.BaseObject;

/**
 * Created by dlernatovich on 5/17/2017.
 */

public abstract class KitBaseListObject<T extends Parcelable> extends BaseObject {

    /**
     * Instance of the {@link Object}
     */
    private final T object;

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    public KitBaseListObject(T object) {
        this.object = object;
    }

    /**
     * Constructor which provide the create {@link KitBaseListObject} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitBaseListObject(Parcel source) {
        super(source);
        this.object = source.readParcelable(getClassLoader());
    }

    /**
     * Method which provide the getting of the instance of the {@link Object}
     *
     * @return instance of the {@link Object}
     */
    @Nullable
    public T getObject() {
        return object;
    }

    /**
     * Method which provide the write {@link BaseObject} to {@link Parcel}
     *
     * @param parcel instance of {@link Parcel}
     * @param flags  flags value
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeParcelable(object, flags);
    }

    /**
     * Method which provide the getting of the instance of the {@link ClassLoader}
     *
     * @return instance of the {@link ClassLoader}
     */
    @NonNull
    protected abstract ClassLoader getClassLoader();

}
