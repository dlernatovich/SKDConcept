package com.artlite.ckconcept.helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.magnet.max.android.UserProfile;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;
import com.magnet.mmx.client.api.MMXMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which provide the additional functional for the {@link MMXChannel} and {@link ChannelDetail}
 */

public final class KitChannelHelper {

    /**
     * Method which provide the getting of the {@link ChannelDetail} name from it subscribers list
     *
     * @param channel instance of {@link ChannelDetail}
     * @return {@link String} value of the channel name
     */
    @NonNull
    public static String getChannelName(@Nullable final ChannelDetail channel) {
        final List<String> names = new ArrayList<>();
        String channelName = "";
        if (channel != null) {
            final List<UserProfile> users = channel.getSubscribers();
            if (BSValidationHelper.validateEmpty(users)) {
                for (UserProfile profile : users) {
                    final String userFullName = profile.getDisplayName();
                    if (BSValidationHelper.validateEmpty(userFullName)) {
                        names.add(userFullName);
                    }
                }
            }
            channelName = TextUtils.join(",", names);
        }
        return channelName;
    }

    /**
     * Method which proivde the getting of the last content of the {@link MMXMessage}
     *
     * @param channel instance of {@link ChannelDetail}
     * @return {@link String} value of the last of the {@link MMXMessage}
     */
    @NonNull
    public static String getChannelLastMessage(@Nullable final ChannelDetail channel) {
        String result = "";
        if (channel != null) {
            final List<MMXMessage> messages = channel.getMessages();
            if (BSValidationHelper.validateEmpty(messages)) {
                final MMXMessage message = messages.get(0);
                if (message != null) {
                    result = message.getContent().get("message");
                }
            }
            if (BSValidationHelper.isEmpty(result)) {
                result = "No messages";
            }
        }
        return result;
    }
}
