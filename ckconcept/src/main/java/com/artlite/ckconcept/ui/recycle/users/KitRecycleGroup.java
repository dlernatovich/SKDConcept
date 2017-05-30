package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;
import com.magnet.max.android.api.group.MMXUserGroup;

import java.lang.ref.WeakReference;

/**
 * Class which provide the recycle representation of the {@link MMXUserGroup}
 */

public class KitRecycleGroup extends BaseObject {

    /**
     * {@link Integer} value of the layout ID
     */
    private final int layoutID;

    /**
     * {@link WeakReference} of the {@link MMXUserGroup}
     */
    private final WeakReference<MMXUserGroup> group;

    /**
     * Constructor which provide the create object with layout ID and {@link MMXUserGroup} object
     *
     * @param layoutID layout ID
     * @param group    {@link MMXUserGroup} object
     */
    public KitRecycleGroup(int layoutID,
                           @NonNull final MMXUserGroup group) {
        this.layoutID = layoutID;
        this.group = new WeakReference<MMXUserGroup>(group);
    }

    /**
     * Constructor which provide the create the {@link KitRecycleGroup} from the instance
     * of the {@link Parcel}
     *
     * @param parcel instance of the {@link Parcel}
     */
    protected KitRecycleGroup(Parcel parcel) {
        super(parcel);
        this.layoutID = parcel.readInt();
        this.group = parcel.readParcelable(MMXUserGroup.class.getClassLoader());
    }

    /**
     * Method which provide the getting group
     *
     * @return group object
     */
    @Nullable
    public MMXUserGroup getGroup() {
        if (group != null) {
            return group.get();
        }
        return null;
    }

    /**
     * Method which provide the getting of the {@link BaseRecyclerItem}
     * for the {@link KitRecycleGroup}
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleItemView(context);
    }

    /**
     * Method which provide the writing of the {@link KitRecycleAlphabetDivider} to parcel
     *
     * @param parcel instance of the {@link Parcel}
     * @param flags  {@link Integer} value of the flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeInt(this.layoutID);
        final MMXUserGroup group = getGroup();
        if (group != null) {
            parcel.writeParcelable(group, flags);
        }
    }

    /**
     * Instance of the {@link Creator}
     */
    public static final Creator<KitRecycleGroup> CREATOR = new Creator<KitRecycleGroup>() {
        @Override
        public KitRecycleGroup createFromParcel(Parcel source) {
            return new KitRecycleGroup(source);
        }

        @Override
        public KitRecycleGroup[] newArray(int size) {
            return new KitRecycleGroup[size];
        }
    };

    /**
     * Class which provide the recycle logic for the {@link KitRecycleGroup}
     */
    private final class RecycleItemView extends BaseRecyclerItem<KitRecycleGroup> {

        /**
         * Instance of the {@link TextView}
         */
        private TextView name;

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
         * @param recycleGroup instance of the {@link KitRecycleGroup}
         */
        @Override
        public void setUp(@NonNull KitRecycleGroup recycleGroup) {
            final MMXUserGroup userGroup = recycleGroup.getGroup();
            if (userGroup != null) {
                name.setText(userGroup.getName());
            }
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
         * Method which provide the {@link Integer} value of the click view Id for the
         * instance of the {@link RecycleItemView}
         *
         * @return {@link Integer} value of the click view Id
         */
        @Override
        protected int getClickedID() {
            return R.id.container;
        }

        /**
         * Method which provide the interface linking
         */
        @Override
        protected void onLinkInterface() {
            name = (TextView) findViewById(R.id.label_group_name);
        }

        /**
         * Method which provide the action for create view action
         */
        @Override
        protected void onCreateView() {

        }
    }
}
