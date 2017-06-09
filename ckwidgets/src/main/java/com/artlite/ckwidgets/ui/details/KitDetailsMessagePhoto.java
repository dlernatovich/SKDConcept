package com.artlite.ckwidgets.ui.details;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.artlite.ckconcept.helpers.message.KitMessageHelper;
import com.artlite.ckconcept.managers.image.KitImageManager;
import com.artlite.ckconcept.ui.abs.details.KitBaseDetailsView;
import com.artlite.ckwidgets.R;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/9/2017.
 */

public final class KitDetailsMessagePhoto extends KitBaseDetailsView<MMXMessage> {

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView imageView;

    /**
     * Constructor which provide the create of the {@link KitBaseDetailsView} from the
     *
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     */
    public KitDetailsMessagePhoto(Context context, @Nullable Parcelable object) {
        super(context, object);
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the layout ID
     *
     * @return {@link Integer} value of the layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_details_photo;
    }

    /**
     * Method which provide the functional after {@link View} creation
     */
    @Override
    protected void onCreateView() {
        this.imageView = (ImageView) findViewById(R.id.image_details);
        onInitInterface();
    }

    /**
     * Method which provide the interface initialization
     */
    private void onInitInterface() {
        final MMXMessage message = getObject();
        if (message != null) {
            final String url = KitMessageHelper.getPhoto(message);
            KitImageManager.getInstance().load(imageView, url);
        }
    }
}
