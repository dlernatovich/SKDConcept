package com.artlite.ckwidgets.ui.list;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.constants.KitMessageType;
import com.artlite.ckconcept.helpers.map.KitMapHelper;
import com.artlite.ckconcept.helpers.message.KitMessageHelper;
import com.artlite.ckconcept.managers.image.KitImageManager;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckwidgets.R;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/7/2017.
 */

public final class KitListMessageLocationMy extends KitBaseListObject<MMXMessage> {

    /**
     * {@link String} value of the latitude
     */
    private String latitude;

    /**
     * {@link String} value of the longitude
     */
    private String longitude;

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    public KitListMessageLocationMy(Parcelable object) {
        super(object);
    }

    /**
     * Constructor which provide the create {@link KitBaseListObject} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitListMessageLocationMy(Parcel source) {
        super(source);
    }

    /**
     * Method which provide the performing the action when the {@link Parcelable} was set
     *
     * @param message instance of the {@link Object}
     */
    @Override
    public void onPerformInitialize(@Nullable MMXMessage message) {
        if (message != null) {
            final KitMessageType type = KitMessageHelper.getType(message);
            if (type == KitMessageType.MAP) {
                this.latitude = KitMessageHelper.getLatitude(message);
                this.longitude = KitMessageHelper.getLongitude(message);
            }
        }
    }

    /**
     * Method which provide the getting of the instance of the {@link ClassLoader}
     *
     * @return instance of the {@link ClassLoader}
     */
    @NonNull
    @Override
    protected ClassLoader getClassLoader() {
        return null;
    }

    /**
     * Method which provide the getting of the instance of the {@link BaseRecyclerItem}
     *
     * @param context instance of the {@link Context}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleView(context);
    }

    /**
     * Inner recycle view class
     */
    private static class RecycleView extends BaseRecyclerItem<KitListMessageLocationMy> {

        /**
         * Instance of the {@link ImageView}
         */
        private ImageView imageView;

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
         * @param object instance of the {@link KitListMessageLocationMy}
         */
        @Override
        public void setUp(@NonNull KitListMessageLocationMy object) {
            final String link = KitMapHelper.getPreviewSmallMap(object.latitude,
                    object.longitude);
            KitImageManager.getInstance().load(imageView, link);
        }

        /**
         * Method which provide to getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return R.layout.recycle_kit_message_location_my;
        }

        /**
         * Method which provide the interface linking
         */
        @Override
        protected void onLinkInterface() {
            this.imageView = (ImageView) findViewById(R.id.image_map);
        }

        /**
         * Method which provide the getting of the {@link Integer} value of the clicked Id
         *
         * @return {@link Integer} value of the clicked Id
         */
        @Override
        protected int getClickedID() {
            return R.id.container;
        }

        /**
         * Method which provide the action when view will create
         */
        @Override
        protected void onCreateView() {

        }
    }
}
