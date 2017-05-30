package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;

/**
 * Class which provide the recycle representation for the no groups item
 */

public class KitRecycleNoGroups extends BaseObject {

    /**
     * {@link Integer} value of the layout ID
     */
    private final int layoutID;

    /**
     * Constructor which provide the create {@link KitRecycleNoGroups} from {@link Integer}
     * value of the layout Id
     *
     * @param layoutID {@link Integer} value of the layout Id
     */
    public KitRecycleNoGroups(int layoutID) {
        this.layoutID = layoutID;
    }

    /**
     * Constructor which provide the create the {@link KitRecycleNoGroups} from the instance
     * of the {@link Parcel}
     *
     * @param parcel instance of the {@link Parcel}
     */
    protected KitRecycleNoGroups(Parcel parcel) {
        super(parcel);
        this.layoutID = parcel.readInt();
    }

    /**
     * Method which provide the getting of the {@link BaseRecyclerItem}
     * for the {@link KitRecycleNoGroups}
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleItemView(context);
    }

    /**
     * Method which provide the writing of the {@link KitRecycleNoGroups} to parcel
     *
     * @param parcel instance of the {@link Parcel}
     * @param flags  {@link Integer} value of the flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeInt(this.layoutID);
    }

    /**
     * Instance of the {@link Creator}
     */
    public static final Creator<KitRecycleNoGroups> CREATOR = new Creator<KitRecycleNoGroups>() {
        @Override
        public KitRecycleNoGroups createFromParcel(Parcel source) {
            return new KitRecycleNoGroups(source);
        }

        @Override
        public KitRecycleNoGroups[] newArray(int size) {
            return new KitRecycleNoGroups[size];
        }
    };

    /**
     * Class which provide the recycle logic for the {@link KitRecycleNoGroups}
     */
    private final class RecycleItemView extends BaseRecyclerItem<KitRecycleNoGroups> {

        /**
         * Constructor which provide the create {@link RecycleItemView} from the instance
         * of the {@link Context}
         *
         * @param context instance of {@link Context}
         */
        public RecycleItemView(Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up of the {@link RecycleItemView}
         *
         * @param groups instance of the {@link KitRecycleNoGroups}
         */
        @Override
        public void setUp(@NonNull KitRecycleNoGroups groups) {

        }

        /**
         * Method which provide the {@link Integer} value of the layout Id for the
         * instance of the {@link RecycleItemView}
         *
         * @return {@link Integer} value of the layout Id
         */
        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        /**
         * Method which provide the interface linking
         */
        @Override
        protected void onLinkInterface() {

        }

        /**
         * Method which provide the action for create view action
         */
        @Override
        protected void onCreateView() {

        }
    }
}
