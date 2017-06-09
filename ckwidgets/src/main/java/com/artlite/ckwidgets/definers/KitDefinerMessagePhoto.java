package com.artlite.ckwidgets.definers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.constants.KitMessageType;
import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.helpers.message.KitMessageHelper;
import com.artlite.ckconcept.models.define.KitBaseDefiner;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/9/2017.
 */

public final class KitDefinerMessagePhoto extends KitBaseDefiner {

    /**
     * Default constructor for the {@link KitBaseDefiner}
     *
     * @param callerClass instance of the {@link Class}
     */
    public KitDefinerMessagePhoto(@NonNull Class callerClass) {
        super(callerClass);
    }

    /**
     * Method which provide the define functional for the {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    @Nullable
    @Override
    public String define(@Nullable Object object) {
        if ((object != null) && (object instanceof MMXMessage)) {
            final MMXMessage message = (MMXMessage) object;
            final KitMessageType type = KitMessageHelper.getType(message);
            if (type == KitMessageType.PHOTO) {
                return KitWidgetType.MESSAGE_PHOTO.getValue();
            }
        }
        return null;
    }
}
