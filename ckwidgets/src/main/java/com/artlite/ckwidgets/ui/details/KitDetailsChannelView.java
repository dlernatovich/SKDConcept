package com.artlite.ckwidgets.ui.details;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckconcept.ui.abs.KitBaseDetailsView;
import com.artlite.ckwidgets.R;
import com.magnet.mmx.client.api.MMXChannel;

/**
 * Created by dlernatovich on 5/16/2017.
 */

public class KitDetailsChannelView extends KitBaseDetailsView<MMXChannel> {

    /**
     * Constructor which provide the create of the {@link KitBaseDetailsView} from the
     *
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     */
    public KitDetailsChannelView(Context context, @Nullable Object object) {
        super(context, object);
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the layout ID
     *
     * @return {@link Integer} value of the layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_channel_details;
    }

    /**
     * Method which provide the functional after {@link View} creation
     */
    @Override
    protected void onCreateView() {
        if (isHaveObject()) {
            BSLogHelper.log(this, "onCreateView", null, getObject());
        }
    }
}
