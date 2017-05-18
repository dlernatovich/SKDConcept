package com.artlite.ckconcept.helpers;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.constants.KitMessageType;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Method which provide the functional for the {@link MMXMessage}
 */

public final class KitMessageHelper {

    //==============================================================================================
    //                                          GET TYPE
    //==============================================================================================

    /**
     * Method which provide the getting of the {@link KitMessageType} from the instance of the
     * {@link MMXMessage}
     *
     * @param message instance of the {@link MMXMessage}
     * @return @link KitMessageType} from the instance of the {@link MMXMessage}
     */
    public static KitMessageType getType(@Nullable MMXMessage message) {
        return KitMessageType.getType(message);
    }

    /**
     * Method which provide the getting of the {@link KitMessageType} from
     * the {@link String} value of the {@link MMXMessage} type
     *
     * @param type {@link String} value of the {@link MMXMessage} type
     * @return {@link KitMessageType} from the {@link String} value of the {@link MMXMessage} type
     */
    public static KitMessageType getType(@Nullable String type) {
        return KitMessageType.getType(type);
    }

    //==============================================================================================
    //                                          GET ICON
    //==============================================================================================

    /**
     * Method which provide the getting of the {@link KitMessageType} from the instance of the
     * {@link MMXMessage}
     *
     * @param message instance of the {@link MMXMessage}
     * @return @link KitMessageType} from the instance of the {@link MMXMessage}
     */
    @DrawableRes
    public static int getIcon(@Nullable MMXMessage message) {
        return KitMessageType.getIcon(message);
    }

    /**
     * Method which provide the getting of the {@link KitMessageType} from
     * the {@link String} value of the {@link MMXMessage} type
     *
     * @param type {@link String} value of the {@link MMXMessage} type
     * @return {@link KitMessageType} from the {@link String} value of the {@link MMXMessage} type
     */
    @DrawableRes
    public static int getIcon(@Nullable String type) {
        return KitMessageType.getIcon(type);
    }

    //==============================================================================================
    //                                          GET DESCRIPTION
    //==============================================================================================

    /**
     * Method which provide the getting of the {@link MMXMessage} description
     *
     * @param message instance of the {@link MMXMessage}
     * @return {@link String} value of the {@link MMXMessage} description
     */
    @NonNull
    public static String getDescription(@Nullable Context context,
                                        @Nullable MMXMessage message) {
        return KitMessageType.getDescription(context, message);
    }
}
