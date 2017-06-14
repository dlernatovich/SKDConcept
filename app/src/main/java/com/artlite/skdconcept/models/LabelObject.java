package com.artlite.skdconcept.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.artlite.bslibrary.managers.BSRandomManager;

/**
 * Example for the {@link com.artlite.ckconcept.ui.views.universal.KitUniversalWidgetView}
 */

public final class LabelObject implements Parcelable {
    /**
     * {@link String} value of the description
     */
    public final String description;


    /**
     * Method which provide the content description
     *
     * @return {@link Integer} value of the content description
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Method which provide the writing of the Object to the parcel
     *
     * @param dest  instance of the {@link Parcel}
     * @param flags {@link Integer} value of the flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
    }

    /**
     * Default constructor
     */
    public LabelObject() {
        this.description = BSRandomManager.generateSentence(20);
    }

    /**
     * Constructor which provide the create the {@link LabelObject} from the instance of the
     * {@link Parcel}
     *
     * @param in instance of the {@link Parcel}
     */
    protected LabelObject(Parcel in) {
        this.description = in.readString();
    }

    /**
     * Instance of the {@link Creator}
     */
    public static final Creator<LabelObject> CREATOR = new Creator<LabelObject>() {
        @Override
        public LabelObject createFromParcel(Parcel source) {
            return new LabelObject(source);
        }

        @Override
        public LabelObject[] newArray(int size) {
            return new LabelObject[size];
        }
    };
}
