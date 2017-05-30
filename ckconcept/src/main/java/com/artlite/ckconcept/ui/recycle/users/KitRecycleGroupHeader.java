package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;

/**
 * Class which provide the recycle representation for the group header
 */
public class KitRecycleGroupHeader extends BaseObject {

    /**
     * Instance of the {@link RecycleEvent}
     */
    public static final RecycleEvent K_MANAGE_EVENT =
            new RecycleEvent("KitRecycleGroupHeader:ManageEvent");

    /**
     * {@link Integer} value of the layout ID
     */
    private final int layoutID;

    /**
     * Constructor which provide the create {@link KitRecycleGroupHeader} from {@link Integer}
     * value of the layout Id
     *
     * @param layoutID {@link Integer} value of the layout Id
     */
    public KitRecycleGroupHeader(int layoutID) {
        this.layoutID = layoutID;
    }

    /**
     * Constructor which provide the create the {@link KitRecycleGroupHeader} from the instance
     * of the {@link Parcel}
     *
     * @param parcel instance of the {@link Parcel}
     */
    protected KitRecycleGroupHeader(Parcel parcel) {
        super(parcel);
        this.layoutID = parcel.readInt();
    }

    /**
     * Method which provide the getting of the {@link BaseRecyclerItem}
     * for the {@link KitRecycleGroupHeader}
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleItemView(context);
    }

    /**
     * Method which provide the writing of the {@link KitRecycleGroupHeader} to parcel
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
    public static final Creator<KitRecycleGroupHeader> CREATOR = new Creator<KitRecycleGroupHeader>() {
        @Override
        public KitRecycleGroupHeader createFromParcel(Parcel source) {
            return new KitRecycleGroupHeader(source);
        }

        @Override
        public KitRecycleGroupHeader[] newArray(int size) {
            return new KitRecycleGroupHeader[size];
        }
    };

    /**
     * Class which provide the recycle logic for the {@link KitRecycleGroupHeader}
     */
    private final class RecycleItemView extends BaseRecyclerItem<KitRecycleGroupHeader> {

        /**
         * Instance of {@link Button}
         */
        private Button manageButton;

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
         * @param header instance of the {@link KitRecycleGroupHeader}
         */
        @Override
        public void setUp(@NonNull KitRecycleGroupHeader header) {

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
            manageButton = (Button) findViewById(R.id.button_manage_header);
        }

        /**
         * Method which provide the action for create view action
         */
        @Override
        protected void onCreateView() {
            manageButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    sendEvent(K_MANAGE_EVENT);
                }
            });
        }
    }
}
