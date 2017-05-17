package com.artlite.ckconcept.constants;

import android.support.annotation.Nullable;

import com.magnet.mmx.client.api.MMXMessage;

import java.util.Map;

/**
 * Container which provide the keeping of the {@link Map} tags for
 * the content of the {@link MMXMessage}
 */

public enum KitMessageTags {
    TYPE("type"),
    TEXT("message"),
    LONGITUDE("longitude"),
    LATITUDE("latitude"),
    LANG("lang"),
    FORMAT("format");

    /**
     * {@link String} value of the {@link KitMessageTags}
     */
    private final String value;

    /**
     * Constructor which provide to create of the {@link KitMessageTags} from the
     *
     * @param value {@link String} value of the {@link KitMessageTags}
     */
    KitMessageTags(String value) {
        this.value = value;
    }

    /**
     * Method which provide the getting of the {@link String} value of the {@link KitMessageTags}
     *
     * @return {@link String} value of the {@link KitMessageTags}
     */
    public String getValue() {
        return value;
    }

    /**
     * Method which provide the getting of the {@link String} value for the {@link MMXMessage}
     *
     * @param message instance of {@link MMXMessage}
     * @return {@link String} value for the {@link MMXMessage}
     */
    @Nullable
    public static String getType(@Nullable final MMXMessage message) {
        if (message != null) {
            return message.getContent().get(KitMessageTags.TYPE.getValue());
        }
        return null;
    }
}
