package com.artlite.ckwidgets.ui.list;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.constants.KitMessageTags;
import com.artlite.ckconcept.helpers.user.KitUserHelper;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckwidgets.R;
import com.magnet.mmx.client.api.MMXChannel;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/6/2017.
 */

public final class KitListMessageTextOther extends KitBaseListObject<MMXMessage> {

    /**
     * {@link String} value of the user name
     */
    private String userName;

    /**
     * {@link String} value of the content
     */
    private String content;

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    public KitListMessageTextOther(Parcelable object) {
        super(object);
        final MMXMessage message = getObject();
        if (message != null) {
            userName = KitUserHelper.getFullName(message.getSender());
            content = message.getMetaData().get(KitMessageTags.TEXT.getValue());
        }
    }

    /**
     * Constructor which provide the create {@link KitBaseListObject} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitListMessageTextOther(Parcel source) {
        super(source);
    }

    /**
     * Method which provide the getting of the instance of the {@link ClassLoader}
     *
     * @return instance of the {@link ClassLoader}
     */
    @NonNull
    @Override
    protected ClassLoader getClassLoader() {
        return MMXChannel.class.getClassLoader();
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
    private static class RecycleView extends BaseRecyclerItem<KitListMessageTextOther> {

        /**
         * Instance of the {@link TextView}
         */
        private TextView labelUserName;

        /**
         * Instance of the {@link TextView}
         */
        private TextView labelContent;

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
         * @param object instance of the {@link KitListMessageTextOther}
         */
        @Override
        public void setUp(@NonNull KitListMessageTextOther object) {
            this.labelUserName.setText(object.userName);
            this.labelContent.setText(object.content);
        }

        /**
         * Method which provide to getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return R.layout.recycle_kit_message_text_other;
        }

        /**
         * Method which provide the interface linking
         */
        @Override
        protected void onLinkInterface() {
            this.labelUserName = (TextView) findViewById(R.id.label_user_name);
            this.labelContent = (TextView) findViewById(R.id.label_content);
        }

        /**
         * Method which provide the action when view will create
         */
        @Override
        protected void onCreateView() {

        }
    }
}
