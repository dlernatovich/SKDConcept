//package com.artlite.ckwidgets.ui.list;
//
//import android.content.Context;
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.NonNull;
//import android.widget.ImageView;
//
//import com.artlite.adapteredrecyclerview.anotations.FindViewBy;
//import com.artlite.adapteredrecyclerview.models.BaseObject;
//import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
//import com.artlite.bslibrary.managers.BSContextManager;
//import com.artlite.bslibrary.ui.fonted.BSTextView;
//import com.artlite.ckconcept.helpers.channel.KitChannelHelper;
//import com.artlite.ckconcept.models.list.KitBaseListObject;
//import com.artlite.ckwidgets.R;
//import com.magnet.mmx.client.api.ChannelDetail;
//
///**
// * Created by dlernatovich on 5/16/2017.
// */
//
//public final class KitListChannelView extends KitBaseListObject<ChannelDetail> {
//
//    /**
//     * {@link String} value of the channel name
//     */
//    private final String channelName;
//
//    /**
//     * {@link String} value of the channel description
//     */
//    private final String channelDescription;
//
//    /**
//     * {@link String} value of channel short name
//     */
//    private final String channelShortName;
//
//    /**
//     * {@link Integer} value of the channel icon
//     */
//    @DrawableRes
//    private final int iconType;
//
//    /**
//     * Default constructor for {@link BaseObject}
//     */
//    public KitListChannelView(Parcelable channel) {
//        super(channel);
//        final ChannelDetail channelObject = getObject();
//        this.channelName = KitChannelHelper.getChannelName(channelObject);
//        this.channelDescription = KitChannelHelper
//                .getChannelDescription(BSContextManager.getRegisteredContext(), channelObject);
//        this.iconType = KitChannelHelper.getChannelIcon(channelObject);
//        this.channelShortName = KitChannelHelper.getChannelShortName(channelObject);
//    }
//
//    /**
//     * Constructor which provide the create {@link KitListChannelView} from {@link Parcel}
//     *
//     * @param source instance of {@link Parcel}
//     */
//    public KitListChannelView(Parcel source) {
//        super(source);
//        this.channelName = source.readString();
//        this.channelDescription = source.readString();
//        this.channelShortName = source.readString();
//        this.iconType = source.readInt();
//    }
//
//    /**
//     * Method which provide the write {@link BaseObject} to {@link Parcel}
//     *
//     * @param parcel instance of {@link Parcel}
//     * @param flags  flags value
//     */
//    @Override
//    public void writeToParcel(Parcel parcel, int flags) {
//        super.writeToParcel(parcel, flags);
//        parcel.writeString(channelName);
//        parcel.writeString(channelDescription);
//        parcel.writeString(channelShortName);
//        parcel.writeInt(iconType);
//    }
//
//    /**
//     * Method which provide the getting of the instance of the {@link ClassLoader}
//     *
//     * @return instance of the {@link ClassLoader}
//     */
//    @NonNull
//    @Override
//    protected ClassLoader getClassLoader() {
//        return ChannelDetail.class.getClassLoader();
//    }
//
//    /**
//     * Method which provide the getting of the current recycler item
//     *
//     * @param context current context
//     * @return current instance for the Recycler item
//     */
//    @Override
//    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
//        return new RecycleView(context);
//    }
//
//    /**
//     * Instance of {@link Parcelable.Creator}
//     */
//    public static final Parcelable.Creator<KitListChannelView> CREATOR =
//            new Parcelable.Creator<KitListChannelView>() {
//                @Override
//                public KitListChannelView createFromParcel(Parcel source) {
//                    return new KitListChannelView(source);
//                }
//
//                @Override
//                public KitListChannelView[] newArray(int size) {
//                    return new KitListChannelView[size];
//                }
//            };
//
//    /**
//     * Inner recycle view class
//     */
//    private static class RecycleView extends BaseRecyclerItem<KitListChannelView> {
//
//        /**
//         * Instance of the {@link ImageView}
//         */
//        private ImageView avatar;
//        /**
//         * Instance of {@link BSTextView}
//         */
//        private BSTextView labelHeader;
//
//        /**
//         * Instance of {@link BSTextView}
//         */
//        private BSTextView labelDescription;
//
//        /**
//         * Instance of the {@link BSTextView}
//         */
//        private BSTextView labelShortName;
//
//        /**
//         * Instance of the {@link ImageView}
//         */
//        private ImageView imageType;
//
//        /**
//         * Default constructor
//         *
//         * @param context context to set
//         */
//        public RecycleView(@NonNull Context context) {
//            super(context);
//        }
//
//        /**
//         * Method which provide the setting up for the current recycler item
//         *
//         * @param baseObject current object
//         */
//        @Override
//        public void setUp(@NonNull KitListChannelView baseObject) {
//            labelHeader.setText(baseObject.channelName);
//            labelDescription.setText(baseObject.channelDescription);
//            labelShortName.setText(baseObject.channelShortName);
//            imageType.setImageResource(baseObject.iconType);
//        }
//
//        /**
//         * Method which provide the interface linking
//         *
//         * @warning it should be use only for library projects
//         * @information for now added the annotation for link interface, use {@link FindViewBy} for
//         * injecting view
//         */
//        @Override
//        protected void onLinkInterface() {
//            labelHeader = (BSTextView) findViewById(R.id.label_header);
//            labelDescription = (BSTextView) findViewById(R.id.label_description);
//            labelShortName = (BSTextView) findViewById(R.id.label_short_name);
//            imageType = (ImageView) findViewById(R.id.image_message_type);
//        }
//
//        /**
//         * Method which provide to getting of the layout ID
//         *
//         * @return layout ID
//         */
//        @Override
//        protected int getLayoutId() {
//            return R.layout.recycle_kit_channel;
//        }
//
//        /**
//         * Method which provide the getting of the clicked view ID
//         *
//         * @return clicked view ID
//         */
//        @Override
//        protected int getClickedID() {
//            return R.id.content;
//        }
//
//        /**
//         * Method which provide the action when view will create
//         */
//        @Override
//        protected void onCreateView() {
//
//        }
//    }
//}
