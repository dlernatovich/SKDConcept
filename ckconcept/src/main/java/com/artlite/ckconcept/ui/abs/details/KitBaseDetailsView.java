package com.artlite.ckconcept.ui.abs.details;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.ui.view.BSView;

/**
 * Class which provide the displaying of the interface for the details of the widget object
 */

public abstract class KitBaseDetailsView<T extends Parcelable> extends BSView {

    /**
     * Instance of {@link Object}
     */
    private final Parcelable object;

    /**
     * Constructor which provide the create of the {@link KitBaseDetailsView} from the
     *
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     */
    public KitBaseDetailsView(Context context, @Nullable final Parcelable object) {
        super(context);
        this.object = object;
    }

    /**
     * Method which provide the getting of the instance of the {@link Object}
     *
     * @return instance of the {@link Object}
     */
    @Nullable
    protected T getObject() {
        try {
            return (T) object;
        } catch (Exception ex) {
            BSLogHelper.log(this, "getObject", ex, object);
            return null;
        }
    }

    /**
     * Method which provide the checking if {@link KitBaseDetailsView} is have of the object
     *
     * @return checking result
     */
    protected boolean isHaveObject() {
        return getObject() != null;
    }

}
