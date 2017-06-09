package com.artlite.ckwidgets.factories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.factories.OnKitCreatorFactory;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckwidgets.widgets.KitWidgetMessageText;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/6/2017.
 */

public final class KitCreatorMessageText implements OnKitCreatorFactory<MMXMessage> {
    /**
     * Method which provide the create {@link KitWidgetModel} from
     *
     * @param object instance of {@link Object}
     * @return instance of {@link KitWidgetModel}
     */
    @Nullable
    @Override
    public KitWidgetModel create(@Nullable MMXMessage object) {
        return new KitWidgetMessageText(object);
    }

    /**
     * Method which provide the create of the {@link KitWidgetModel} for first initialization
     *
     * @return instance of the {@link KitWidgetModel}
     */
    @NonNull
    @Override
    public KitWidgetModel createForRegistration() {
        return new KitWidgetMessageText();
    }
}
