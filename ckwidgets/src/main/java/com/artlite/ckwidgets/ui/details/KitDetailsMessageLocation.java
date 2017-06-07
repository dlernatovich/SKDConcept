package com.artlite.ckwidgets.ui.details;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.ckconcept.ui.abs.details.KitBaseDetailsView;
import com.artlite.ckwidgets.R;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/7/2017.
 */

public final class KitDetailsMessageLocation extends KitBaseDetailsView<MMXMessage> {

    /**
     * Constructor which provide the create of the {@link KitBaseDetailsView} from the
     *
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     */
    public KitDetailsMessageLocation(Context context, @Nullable Parcelable object) {
        super(context, object);
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the layout ID
     *
     * @return {@link Integer} value of the layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_details_location;
    }

    /**
     * Method which provide the functional after {@link View} creation
     */
    @Override
    protected void onCreateView() {

    }
}
