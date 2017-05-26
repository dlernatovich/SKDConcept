package com.artlite.ckconcept.helpers.channel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;

/**
 * Created by Artli on 23.11.2016.
 */

class KitChannelDetailsHelper_Unread extends KitChannelDetailsHelper_Constants {

    //==============================================================================================
    //                                    UNREAD MESSAGES
    //==============================================================================================

    /**
     * Method which provide the checking if channel have unread messages
     *
     * @param detail channel details
     * @return checking results
     */
    public static boolean isHaveUnreadMessages(@Nullable final ChannelDetail detail) {
        if (detail == null) {
            return false;
        }
        return isHaveUnreadMessages(detail.getChannel());
    }

    /**
     * Method which provide the checking if channel have unread messages
     *
     * @param channel channel
     * @return checking results
     */
    public static boolean isHaveUnreadMessages(@Nullable final MMXChannel channel) {
        if (channel == null) {
            return false;
        }
        return getUnreadMessagesCount(channel) > 0;
    }

    /**
     * Method which provide the getting of the messages unread count
     *
     * @param channel channel instance
     * @return messages unread count
     */
    public static int getUnreadMessagesCount(@Nullable final MMXChannel channel) {
        if (channel == null) {
            return 0;
        }
        final Long unreadCount = channel.getUnreadCount();
        if (unreadCount == null) {
            return 0;
        }
        return unreadCount.intValue();
    }

    /**
     * Method which provide the getting of the messages unread count
     *
     * @param detail channel details
     * @return messages unread count
     */
    public static int getUnreadMessagesCount(@Nullable final ChannelDetail detail) {
        if (detail == null) {
            return 0;
        }
        return getUnreadMessagesCount(detail.getChannel());
    }

    /**
     * Method which provide the getting of the unread count string with limitation of the maximum
     * number (example lim:30 messages:100 = 30+)
     *
     * @param detail        channel details
     * @param maximumNumber maximum message number
     * @return string value of the maximum message number
     */
    @NonNull
    public static String getUnreadCountString(@Nullable final ChannelDetail detail,
                                              int maximumNumber) {
        int count = getUnreadMessagesCount(detail);
        return getUnreadCountString(count, maximumNumber);
    }

    /**
     * Method which provide the getting of the unread count string with limitation of the maximum
     * number (example lim:30 messages:100 = 30+)
     *
     * @param detail        channel details
     * @param maximumNumber maximum message number
     * @return string value of the maximum message number
     */
    @NonNull
    public static String getUnreadCountString(@Nullable final MMXChannel detail,
                                              int maximumNumber) {
        int count = getUnreadMessagesCount(detail);
        return getUnreadCountString(count, maximumNumber);
    }

    /**
     * Method which provide the getting of the unread count string with limitation of the maximum
     * number (example lim:30 messages:100 = 30+)
     *
     * @param value         value
     * @param maximumNumber limitation
     * @return string value
     */
    @NonNull
    private static String getUnreadCountString(int value,
                                               int maximumNumber) {
        final String additionalSymbol = (value > maximumNumber) ? "+" : "";
        final int currentValue = (value > maximumNumber) ? maximumNumber : value;
        return String.format("%d%s", currentValue, additionalSymbol);
    }
}
