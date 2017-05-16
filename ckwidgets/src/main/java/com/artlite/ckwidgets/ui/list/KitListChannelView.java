package com.artlite.ckwidgets.ui.list;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.artlite.adapteredrecyclerview.anotations.FindViewBy;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.bslibrary.ui.fonted.BSTextView;
import com.artlite.ckwidgets.R;
import com.magnet.mmx.client.api.MMXChannel;

/**
 * Created by dlernatovich on 5/16/2017.
 */

public final class KitListChannelView extends BaseObject {

    /**
     * {@link Integer} value of layout ID
     */
    @LayoutRes
    private final int layoutId;

    /**
     * Instance of {@link MMXChannel}
     */
    private final MMXChannel channel;

    /**
     * {@link String} value of the channel name
     */
    private final String channelName;

    /**
     * {@link String} value of the channel description
     */
    private final String channelDescription;

    /**
     * Default constructor for {@link BaseObject}
     */
    public KitListChannelView(int layoutId, MMXChannel channel) {
        this.layoutId = layoutId;
        this.channel = channel;
        this.channelName = (channel == null) ? "Undefined" : channel.getName();
        this.channelDescription = (channel == null) ? "No description" : channel.getSummary();
    }

    /**
     * Constructor which provide the create {@link KitListChannelView} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitListChannelView(Parcel source) {
        super(source);
        this.layoutId = source.readInt();
        this.channel = source.readParcelable(MMXChannel.class.getClassLoader());
        this.channelName = source.readString();
        this.channelDescription = source.readString();
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
        parcel.writeInt(layoutId);
        parcel.writeParcelable(channel, flags);
        parcel.writeString(channelName);
        parcel.writeString(channelDescription);
    }

    /**
     * Method which provide the getting of the current recycler item
     *
     * @param context current context
     * @return current instance for the Recycler item
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleView(context);
    }

    /**
     * Instance of {@link Parcelable.Creator}
     */
    public static final Parcelable.Creator<KitListChannelView> CREATOR =
            new Parcelable.Creator<KitListChannelView>() {
                @Override
                public KitListChannelView createFromParcel(Parcel source) {
                    return new KitListChannelView(source);
                }

                @Override
                public KitListChannelView[] newArray(int size) {
                    return new KitListChannelView[size];
                }
            };

    /**
     * Inner recycle view class
     */
    private class RecycleView extends BaseRecyclerItem<KitListChannelView> {

        /**
         * Instance of the {@link ImageView}
         */
        private ImageView avatar;
        /**
         * Instance of {@link BSTextView}
         */
        private BSTextView labelHeader;

        /**
         * Instance of {@link BSTextView}
         */
        private BSTextView labelDescription;

        /**
         * Default constructor
         *
         * @param context context to set
         */
        public RecycleView(@NonNull Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up for the current recycler item
         *
         * @param baseObject current object
         */
        @Override
        public void setUp(@NonNull KitListChannelView baseObject) {
            labelHeader.setText(baseObject.channelName);
            labelDescription.setText(baseObject.channelDescription);
        }

        /**
         * Method which provide the interface linking
         *
         * @warning it should be use only for library projects
         * @information for now added the annotation for link interface, use {@link FindViewBy} for
         * injecting view
         */
        @Override
        protected void onLinkInterface() {
            labelHeader = (BSTextView) findViewById(R.id.label_header);
            labelDescription = (BSTextView) findViewById(R.id.label_description);
        }

        /**
         * Method which provide to getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return layoutId;
        }

        /**
         * Method which provide the getting of the clicked view ID
         *
         * @return clicked view ID
         */
        @Override
        protected int getClickedID() {
            return com.artlite.ckconcept.R.id.content;
        }

        /**
         * Method which provide the action when view will create
         */
        @Override
        protected void onCreateView() {

        }
    }
}
