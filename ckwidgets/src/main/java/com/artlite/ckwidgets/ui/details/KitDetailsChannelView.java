package com.artlite.ckwidgets.ui.details;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.ckconcept.helpers.KitChannelHelper;
import com.artlite.ckconcept.ui.abs.KitBaseDetailsView;
import com.artlite.ckwidgets.R;
import com.magnet.mmx.client.api.ChannelDetail;

/**
 * Created by dlernatovich on 5/16/2017.
 */

public class KitDetailsChannelView extends KitBaseDetailsView<ChannelDetail> {

    /**
     * Instance of the {@link TextView}
     */
    private TextView labelName;

    /**
     * Instance of the {@link TextView}
     */
    private TextView labelDescription;

    /**
     * Instance of the {@link ImageView}
     */
    private ImageView imageType;

    /**
     * {@link String} value of the name
     */
    private String name;

    /**
     * {@link String} value of the description
     */
    private String description;

    @DrawableRes
    private int iconId;

    /**
     * Constructor which provide the create of the {@link KitBaseDetailsView} from the
     *
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     */
    public KitDetailsChannelView(Context context, @Nullable Parcelable object) {
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
        setOnClickListeners(R.id.button_close);
        this.labelName = (TextView) findViewById(R.id.label_name);
        this.labelDescription = (TextView) findViewById(R.id.label_description);
        this.imageType = (ImageView) findViewById(R.id.image_type);
        if (isHaveObject()) {
            BSThreadManager.execute(new BSThreadManager.OnExecutionCallback() {
                @Override
                public void onBackground() {
                    final ChannelDetail detail = getObject();
                    name = KitChannelHelper.getChannelName(detail);
                    description = KitChannelHelper.getChannelDescription(getContext(), detail);
                    iconId = KitChannelHelper.getChannelIcon(detail);
                }

                @Override
                public void onMain() {
                    labelName.setText(name);
                    labelDescription.setText(description);
                    imageType.setImageResource(iconId);
                }
            });
        }
    }

    /**
     * Method which provide the performing of the on click functional
     *
     * @param view instance of the {@link View}
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_close) {
            dismiss();
        }
    }
}
