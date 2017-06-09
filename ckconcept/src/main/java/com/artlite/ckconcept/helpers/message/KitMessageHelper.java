package com.artlite.ckconcept.helpers.message;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.constants.KitMessageTags;
import com.artlite.ckconcept.constants.KitMessageType;
import com.magnet.max.android.Attachment;
import com.magnet.max.android.User;
import com.magnet.mmx.client.api.MMXMessage;
import com.magnet.mmx.client.ext.ObjectIdentifier;
import com.magnet.mmx.client.ext.poll.MMXChecklist;
import com.magnet.mmx.client.ext.poll.MMXPoll;

import java.util.Map;

/**
 * Method which provide the functional for the {@link MMXMessage}
 */

public final class KitMessageHelper {

    /**
     * {@link String} constant of the tag answer
     */
    private static final String K_TAG_ANSWER = "object/MMXPollAnswer";

    /**
     * {@link String} constant of the tag ID
     */
    private static final String K_TAG_ID = "object/" + ObjectIdentifier.TYPE;

    /**
     * {@link String} constant of the question
     */
    private static final String K_TAG_QUESTION = "question";

    /**
     * {@link String} constant of the tag of the checklist
     */
    private static final String K_TAG_CHECKLIST = "object/"
            + MMXChecklist.MMXChecklistSelection.TYPE;

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
        return defineType(message);
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
        final KitMessageType type = getType(message);
        return type.getIcon();
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
        final KitMessageType type = getType(message);
        return KitMessageType.getDescription(context, message, type);
    }

    /**
     * Method which provide the defining of the {@link MMXMessage} type
     * (<b></>WARNING:</b></> Method which copied from old version of ChatKit)
     *
     * @param message instance of the {@link MMXMessage}
     * @return type which using in MMXListItemFactory
     * @warning Method which copied from old version of ChatKit
     */
    @NonNull
    private static KitMessageType defineType(MMXMessage message) {
        String contentType = message.getContentType();
        if (contentType != null) {
            if (contentType.equals(K_TAG_ANSWER)) {
                return KitMessageType.ANSWER;
            } else if (contentType.equals(K_TAG_CHECKLIST)) {
                return KitMessageType.POLL;
            } else if (contentType.equals(K_TAG_ID)) {
                ObjectIdentifier objectIdentifier = (ObjectIdentifier) message.getPayload();
                int pos = objectIdentifier.getObjectType().indexOf("/");
                String objectType = null;
                if (pos >= 0) {
                    objectType = objectIdentifier.getObjectType().substring(pos + 1);
                } else {
                    objectType = objectIdentifier.getObjectType();
                }
                if (MMXChecklist.TYPE.equals(objectType)) {
                    return KitMessageType.POLL;
                } else if (MMXPoll.TYPE.equals(objectType)) {
                    return KitMessageType.POLL;
                }
            }
        }
        String tagType = null;
        Map<String, String> content = message.getMetaData();
        if (content != null) {
            if ((content.containsKey(KitMessageTags.LANG.getValue())
                    && (content.containsKey(KitMessageTags.FORMAT.getValue())))) {
                tagType = content.get(KitMessageTags.FORMAT.getValue());
            } else if (content.containsKey(KitMessageTags.TYPE.getValue())) {
                tagType = content.get(KitMessageTags.TYPE.getValue());
            } else if (content.containsKey(K_TAG_QUESTION)) {
                return KitMessageType.SURVEY;
            }
        }
        final KitMessageType type = KitMessageType.getType(tagType);
        if (type == KitMessageType.UNKNOWN) {
            return KitMessageType.getType(message);
        }
        return type;
    }

    /**
     * Method which provide the checking if the current {@link MMXMessage} is my
     *
     * @param message instance of the {@link MMXMessage}
     * @return checking value
     */
    public static boolean isMy(@Nullable final MMXMessage message) {
        final String currentId = User.getCurrentUserId();
        if ((message != null) && (currentId != null) && (message.getSender() != null)) {
            final String senderId = message.getSender().getUserIdentifier();
            if (senderId != null) {
                return currentId.equals(senderId);
            }
        }
        return false;
    }

    //==============================================================================================
    //                                          LOCATION
    //==============================================================================================

    /**
     * Method which provide the getting of the longitude from the instance of the {@link MMXMessage}
     *
     * @param message instance of the {@link MMXMessage}
     * @return {@link String} value of the longitude
     */
    @Nullable
    public static String getLongitude(@Nullable final MMXMessage message) {
        if (message != null) {
            final KitMessageType type = getType(message);
            if (type == KitMessageType.MAP) {
                return (message.getMetaData().containsKey(KitMessageTags.LONGITUDE.getValue()))
                        ? message.getMetaData().get(KitMessageTags.LONGITUDE.getValue()) : null;
            }
        }
        return null;
    }

    /**
     * Method which provide the getting of the latitude from the instance of the {@link MMXMessage}
     *
     * @param message instance of the {@link MMXMessage}
     * @return {@link String} value of the longitude
     */
    @Nullable
    public static String getLatitude(@Nullable final MMXMessage message) {
        if (message != null) {
            final KitMessageType type = getType(message);
            if (type == KitMessageType.MAP) {
                return (message.getMetaData().containsKey(KitMessageTags.LATITUDE.getValue()))
                        ? message.getMetaData().get(KitMessageTags.LATITUDE.getValue()) : null;
            }
        }
        return null;
    }

    //==============================================================================================
    //                                      GET PHOTO
    //==============================================================================================

    /**
     * Method which provide the getting of the photo link from
     * the instance of the {@link MMXMessage}
     *
     * @param message instance of the {@link MMXMessage}
     * @return {@link String} value of the photo URL
     */
    @Nullable
    public static String getPhoto(@Nullable final MMXMessage message) {
        if ((message != null) && (getType(message) == KitMessageType.PHOTO)) {
            if (!message.getAttachments().isEmpty()) {
                final Attachment attachment = message.getAttachments().get(0);
                final String url = Attachment.createDownloadUrl(attachment.getAttachmentId(),
                        User.getCurrentUserId());
                return url;
            }
        }
        return null;
    }
}
