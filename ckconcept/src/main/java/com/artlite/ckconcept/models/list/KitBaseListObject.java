package com.artlite.ckconcept.models.list;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.helpers.log.BSLogHelper;

/**
 * Created by dlernatovich on 5/17/2017.
 */

public abstract class KitBaseListObject<T extends Parcelable> extends BaseObject {

    /**
     * Instance of the {@link Object}
     */
    private Object object;

    /**
     * {@link String} value of the type
     */
    private String type;

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    public KitBaseListObject(Object object) {
        this.object = object;
        if (getObject() != null) {
            onPerformInitialize(getObject());
        }
    }

    /**
     * Constructor which provide the create {@link KitBaseListObject} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitBaseListObject(Parcel source) {
        super(source);
        if (getClassLoader() != null) {
            this.object = source.readParcelable(getClassLoader());
        }
        this.type = source.readString();
        onPerformInitialize(getObject());
    }

    /**
     * Method which provide the performing the action when the {@link Parcelable} was set
     *
     * @param object instance of the {@link Object}
     */
    public abstract void onPerformInitialize(@NonNull final T object);

    /**
     * Method which provide the getting of the instance of the {@link Object}
     *
     * @return instance of the {@link Object}
     */
    @Nullable
    public T getObject() {
        try {
            return (T) object;
        } catch (Exception ex) {
            BSLogHelper.log(this, "getObject", ex, object);
            return null;
        }
    }

    /**
     * Method which provide the checking if {@link KitBaseListObject} is have of the object
     *
     * @return checking result
     * @information also called from the default constructor
     */
    protected boolean isHaveObject() {
        return getObject() != null;
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
        if ((getClassLoader() == null) && (object instanceof Parcelable)) {
            parcel.writeParcelable((Parcelable) object, flags);
        }
        parcel.writeString((type == null) ? "" : type);
    }

    /**
     * Method which provide the getting of the {@link String} value of the type
     *
     * @return {@link String} value of the type
     */
    @Nullable
    public String getType() {
        return type;
    }

    /**
     * Method which provide the setting of the {@link String} value of the type
     *
     * @param type
     */
    public void setType(@Nullable final String type) {
        this.type = type;
    }

    /**
     * Method which provide the getting of the instance of the {@link ClassLoader}
     *
     * @return instance of the {@link ClassLoader}
     */
    @Nullable
    protected abstract ClassLoader getClassLoader();

}
