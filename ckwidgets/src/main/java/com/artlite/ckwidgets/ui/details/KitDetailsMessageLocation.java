package com.artlite.ckwidgets.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.artlite.bslibrary.helpers.intent.BSIntentHelper;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.map.BSMapHelper;
import com.artlite.ckconcept.helpers.image.KitImageHelper;
import com.artlite.ckconcept.helpers.message.KitMessageHelper;
import com.artlite.ckconcept.ui.abs.details.KitBaseDetailsView;
import com.artlite.ckwidgets.R;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/7/2017.
 */

public final class KitDetailsMessageLocation extends KitBaseDetailsView<MMXMessage> {

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView imageView;

    /**
     * {@link String} value of the latitude
     */
    private String latitude;

    /**
     * {@link String} value of the longitude
     */
    private String longitude;

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
     * Method which provide the interface linking
     */
    @Override
    protected void onLinkInterface() {

    }

    /**
     * Method which provide the functional after {@link View} creation
     */
    @Override
    protected void onCreateView() {
        //Link interface
        this.imageView = (ImageView) findViewById(R.id.image_map);
        //Set click listener
        setOnClickListeners(R.id.button_open);
        //Setup interface
        onInitInterface();
    }

    /**
     * Method which provide the initialize of the interface
     */
    protected void onInitInterface() {
        final MMXMessage message = getObject();
        if (message != null) {
            latitude = KitMessageHelper.getLatitude(message);
            longitude = KitMessageHelper.getLongitude(message);
            final String url = BSMapHelper.getPreviewLargeMap(latitude, longitude);
            KitImageHelper.load(imageView, url);
        }
    }

    /**
     * Method which provide the onClick functional
     *
     * @param view instance of the {@link View}
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_open) {
            showLocation();
        }
    }

    /**
     * Method which provide the location showing in the GoogleMaps
     */
    protected void showLocation() {
        try {
            Intent intent = BSIntentHelper.showLocation(Float.parseFloat(latitude),
                    Float.parseFloat(longitude), 18);
            getContext().startActivity(intent, null);
        } catch (Exception ex) {
            BSLogHelper.log(this, "void showLocation()", ex, null);
        }
    }
}
