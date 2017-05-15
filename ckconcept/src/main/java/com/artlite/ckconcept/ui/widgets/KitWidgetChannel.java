package com.artlite.ckconcept.ui.widgets;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.artlite.adapteredrecyclerview.anotations.FindViewBy;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.adapteredrecyclerview.ui.views.AdapteredRecyclerView;
import com.artlite.bslibrary.ui.fonted.BSTextView;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckconcept.ui.views.KitChannelsView;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dlernatovich on 5/15/2017.
 */

public final class KitWidgetChannel extends KitWidgetModel<MMXChannel> {

    /**
     * Constructor which provide the create of the {@link KitWidgetModel}
     */
    public KitWidgetChannel() {
        super();
    }

    /**
     * Constructor which provide the create of the {@link KitWidgetModel} from instance of
     * {@link Object}
     *
     * @param object instance of {@link Object}
     */
    public KitWidgetChannel(@Nullable MMXChannel object) {
        super(object);
    }

    /**
     * Method which provide the getting of the layout for the {@link View} with create
     * widget functional
     *
     * @return {@link Integer} value of the layout ID
     */
    @Nullable
    @Override
    public Integer getLayoutCreate() {
        return null;
    }

    /**
     * Method which provide the getting of the layout for the {@link View} with displaying of the
     * widget
     *
     * @return {@link Integer} value of the layout ID
     */
    @Nullable
    @Override
    public Integer getLayoutDisplay() {
        return null;
    }

    /**
     * Method which provide the getting of the layout for the {@link View} with displaying of the
     * widget in the {@link AdapteredRecyclerView}
     *
     * @return {@link Integer} value of the layout ID
     */
    @Nullable
    @Override
    public Integer getLayoutList() {
        return R.layout.recycle_kit_channel;
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public BSView getViewCreate() {
        return null;
    }

    /**
     * Method which provide the checking if widget need to have of the create view
     *
     * @return checking if widget need to have of the create view
     */
    @Override
    public boolean isNeedCreateView() {
        return false;
    }

    /**
     * Method which provide the getting view for details
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public BSView getViewDetails() {
        return null;
    }

    /**
     * Method which provide the checking if widget need to have of the details view
     *
     * @return checking if widget need to have of the create view
     */
    @Override
    public boolean isNeedDetailsView() {
        return false;
    }

    /**
     * Method which provide the getting view for list representation
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public BaseObject getViewList() {
        return new RecycleObject(getLayoutList(), getObject());
    }

    /**
     * Method which provide the checking if widget need to have of the create view
     *
     * @return checking if widget need to have of the create view
     */
    @Override
    public boolean isNeedListView() {
        return true;
    }

    /**
     * Method which provide the applying of the widget from the instance of inner {@link Object}
     */
    @Override
    public void apply() {
        apply(getObject());
    }

    /**
     * Method which provide applying of the content from {@link Object}
     *
     * @param object instance of {@link Object}
     */
    @Override
    public void apply(@Nullable MMXChannel object) {

    }

    /**
     * Method which provide the getting of headers for the menu of the create of the widget
     *
     * @return {@link String} value of the menu header
     */
    @Nullable
    @Override
    public List<KitMenuModel> getMenuHeaders() {
        return Arrays.asList(
                new KitMenuModel("Create channel",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class),
                new KitMenuModel("Create channel 1",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class),
                new KitMenuModel("Create channel 2",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class),
                new KitMenuModel("Create channel 3",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class),
                new KitMenuModel("Create channel 4",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class),
                new KitMenuModel("Create channel 5",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class),
                new KitMenuModel("Create channel 6",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class),
                new KitMenuModel("Create channel 7",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class),
                new KitMenuModel("Create channel 8",
                        R.mipmap.ic_no_menu,
                        KitWidgetChannel.class,
                        KitChannelsView.class)
        );
    }

    /**
     * Method which provide the getting of the menu header
     *
     * @return checking result
     */
    @Override
    public boolean isNeedMenuHeader() {
        return true;
    }

    //==============================================================================================
    //                                  RECYCLE OBJECT
    //==============================================================================================

    /**
     * Class which provide the recycle functional
     */
    private static class RecycleObject extends BaseObject {

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
        public RecycleObject(int layoutId, MMXChannel channel) {
            this.layoutId = layoutId;
            this.channel = channel;
            this.channelName = (channel == null) ? "Undefined" : channel.getName();
            this.channelDescription = (channel == null) ? "No description" : channel.getSummary();
        }

        /**
         * Constructor which provide the create {@link RecycleObject} from {@link Parcel}
         *
         * @param source instance of {@link Parcel}
         */
        public RecycleObject(Parcel source) {
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
         * Instance of {@link Creator}
         */
        public static final Creator<RecycleObject> CREATOR =
                new Creator<RecycleObject>() {
                    @Override
                    public RecycleObject createFromParcel(Parcel source) {
                        return new RecycleObject(source);
                    }

                    @Override
                    public RecycleObject[] newArray(int size) {
                        return new RecycleObject[size];
                    }
                };

        /**
         * Inner recycle view class
         */
        private class RecycleView extends BaseRecyclerItem<RecycleObject> {

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
            public void setUp(@NonNull RecycleObject baseObject) {
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
                return R.id.content;
            }

            /**
             * Method which provide the action when view will create
             */
            @Override
            protected void onCreateView() {

            }
        }
    }
}
