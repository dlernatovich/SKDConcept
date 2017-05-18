package com.artlite.ckconcept.constants;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.artlite.ckconcept.R;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Class which provide the keeping of the {@link MMXMessage} types
 */
public enum KitMessageType {
    UNKNOWN("unknown", R.mipmap.ic_ck_unknown, R.string.text_ck_unknown),
    TEXT("text", R.mipmap.ic_ck_message, R.string.text_ck_text),
    PHOTO("photo", R.mipmap.ic_ck_photo, R.string.text_ck_photo),
    MAP("location", R.mipmap.ic_ck_map, R.string.text_ck_location),
    VIDEO("video", R.mipmap.ic_ck_video, R.string.text_ck_video),
    POLL("poll", R.mipmap.ic_ck_poll, R.string.text_ck_pool),
    ANSWER("poll", R.mipmap.ic_ck_poll, R.string.text_ck_answer),
    CODE("code", R.mipmap.ic_ck_code, R.string.text_ck_code);

    /**
     * {@link String} value of the {@link KitMessageType}
     */
    private final String value;

    /**
     * {@link Integer} value of the {@link Drawable} resource Id
     */
    @DrawableRes
    private final int icon;

    /**
     * {@link Integer} value of the {@link String} description
     */
    @StringRes
    private final int text;

    /**
     * Constructor which provide to create of the {@link KitMessageType} from the
     *
     * @param value {@link String} value of the {@link KitMessageType}
     * @param icon  {@link Integer} value of the icon
     * @param text  {@link Integer} value of the text
     */
    KitMessageType(String value,
                   @DrawableRes int icon,
                   @StringRes int text) {
        this.value = value;
        this.icon = icon;
        this.text = text;
    }

    /**
     * Method which provide the getting of the {@link String} value of the {@link KitMessageType}
     *
     * @return {@link String} value of the {@link KitMessageType}
     */
    public String getValue() {
        return value;
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the drawable Id
     *
     * @return {@link Integer} value of the drawable Id
     */
    @DrawableRes
    public int getIcon() {
        return icon;
    }

    /**
     * Method which provide the getting of the {@link Integer} value
     * of the {@link String} description
     *
     * @return {@link Integer} value of the {@link String} description
     */
    @StringRes
    public int getText() {
        return text;
    }

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
        final String type = KitMessageTags.getType(message);
        return getType(type);
    }

    /**
     * Method which provide the getting of the {@link KitMessageType} from
     * the {@link String} value of the {@link MMXMessage} type
     *
     * @param type {@link String} value of the {@link MMXMessage} type
     * @return {@link KitMessageType} from the {@link String} value of the {@link MMXMessage} type
     */
    public static KitMessageType getType(@Nullable String type) {
        if (type == null) {
            return UNKNOWN;
        }
        if (type.equalsIgnoreCase(TEXT.getValue())) {
            return TEXT;
        } else if (type.equalsIgnoreCase(PHOTO.getValue())) {
            return PHOTO;
        } else if (type.equalsIgnoreCase(MAP.getValue())) {
            return MAP;
        } else if (type.equalsIgnoreCase(VIDEO.getValue())) {
            return VIDEO;
        } else if (type.equalsIgnoreCase(POLL.getValue())) {
            return POLL;
        } else if (type.equalsIgnoreCase(ANSWER.getValue())) {
            return ANSWER;
        } else if (type.equalsIgnoreCase(CODE.getValue())) {
            return CODE;
        } else {
            return UNKNOWN;
        }
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
        final String type = KitMessageTags.getType(message);
        return getIcon(type);
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
        return getType(type).getIcon();
    }

    //==============================================================================================
    //                                          GET ICON
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
        final KitMessageType type = getType(message);
        if (type == TEXT) {
            return message.getContent().get(KitMessageTags.TEXT.getValue());
        } else {
            return context.getResources().getString(type.getText());
        }
    }
}
