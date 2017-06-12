package com.artlite.ckwidgets.factories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.factories.OnKitCreatorFactory;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckwidgets.widgets.KitWidgetMessagePhoto;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/9/2017.
 */

public final class KitCreatorMessagePhoto implements OnKitCreatorFactory<MMXMessage> {
    /**
     * Method which provide the create {@link KitWidgetModel} from
     *
     * @param object instance of {@link Object}
     * @return instance of {@link KitWidgetModel}
     */
    @Nullable
    @Override
    public KitWidgetModel create(@Nullable MMXMessage object) {
        return new KitWidgetMessagePhoto(object);
    }

    /**
     * Method which provide the create of the {@link KitWidgetModel} for first initialization
     *
     * @return instance of the {@link KitWidgetModel}
     */
    @NonNull
    @Override
    public KitWidgetModel createForRegistration() {
        return new KitWidgetMessagePhoto();
    }

    /**
     * Method which provide the getting of the {@link String} value of the widget type
     *
     * @return {@link String} value of the widget type
     */
    @Nullable
    @Override
    public String getType() {
        return KitWidgetType.MESSAGE_PHOTO.getValue();
    }
}
