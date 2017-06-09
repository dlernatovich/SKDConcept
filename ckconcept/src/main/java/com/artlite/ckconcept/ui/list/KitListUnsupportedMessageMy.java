package com.artlite.ckconcept.ui.list;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.constants.KitMessageType;
import com.artlite.ckconcept.helpers.message.KitMessageHelper;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/2/2017.
 */

public final class KitListUnsupportedMessageMy extends KitBaseListObject<MMXMessage> {

    /**
     * Instance of the {@link KitMessageType}
     */
    private KitMessageType type;

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     */
    public KitListUnsupportedMessageMy() {
        super((Parcelable) null);
    }

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    public KitListUnsupportedMessageMy(Parcelable object) {
        super(object);
    }

    /**
     * Constructor which provide the create {@link KitBaseListObject} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitListUnsupportedMessageMy(Parcel source) {
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
            this.type = KitMessageHelper.getType(message);
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
        return Parcelable.class.getClassLoader();
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
    public static final Creator<KitListUnsupportedMessageMy> CREATOR =
            new Creator<KitListUnsupportedMessageMy>() {
                @Override
                public KitListUnsupportedMessageMy createFromParcel(Parcel source) {
                    return new KitListUnsupportedMessageMy(source);
                }

                @Override
                public KitListUnsupportedMessageMy[] newArray(int size) {
                    return new KitListUnsupportedMessageMy[size];
                }
            };

    /**
     * Inner recycle view class
     */
    private static final class RecycleView extends BaseRecyclerItem<KitListUnsupportedMessageMy> {

        /**
         * Instance of the {@link View}
         */
        private View viewAdditional;

        /**
         * Instance of the {@link ImageView}
         */
        private ImageView imageAdditional;

        /**
         * Instance of the {@link TextView}
         */
        private TextView labelAdditional;

        /**
         * Default constructor
         *
         * @param context instance of {@link Context}
         */
        public RecycleView(@NonNull Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up for the current recycler item
         *
         * @param support current object
         */
        @Override
        public void setUp(@NonNull KitListUnsupportedMessageMy support) {
            if ((support.type == null) || (support.type == KitMessageType.UNKNOWN)) {
                viewAdditional.setVisibility(GONE);
            } else {
                viewAdditional.setVisibility(VISIBLE);
                imageAdditional.setImageResource(support.type.getIcon());
                labelAdditional.setText(support.type.getText());
            }
        }

        /**
         * Method which provide to getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return R.layout.recycle_ck_message_my_not_support;
        }

        /**
         * Method which provide the interface linking
         */
        @Override
        protected void onLinkInterface() {
            this.viewAdditional = findViewById(R.id.view_additional_information);
            this.imageAdditional = (ImageView) findViewById(R.id.image_additional);
            this.labelAdditional = (TextView) findViewById(R.id.label_type);
        }

        /**
         * Method which provide the action when view will create
         */
        @Override
        protected void onCreateView() {

        }
    }
}
