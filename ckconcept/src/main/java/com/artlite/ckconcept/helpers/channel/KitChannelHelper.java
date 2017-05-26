package com.artlite.ckconcept.helpers.channel;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.ckconcept.constants.KitChannelFilter;
import com.artlite.ckconcept.constants.KitMessageType;
import com.artlite.ckconcept.helpers.message.KitMessageHelper;
import com.magnet.max.android.UserProfile;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;
import com.magnet.mmx.client.api.MMXMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which provide the additional functional for the {@link MMXChannel} and {@link ChannelDetail}
 */

public final class KitChannelHelper extends BSBaseHelper {

    protected static final String K_NAME_REGEXP = "([-])|(NO_NAME)|(^\\d*$)";

    /**
     * {@link String} constants of the ignored user
     */
    private static final String K_IGNORED_USER = "System User";

    /**
     * Method which provide the getting of the {@link ChannelDetail} name from it subscribers list
     *
     * @param channel instance of {@link ChannelDetail}
     * @return {@link String} value of the channel name
     */
    @NonNull
    public static String getChannelName(@Nullable final ChannelDetail channel) {
        final List<String> names = new ArrayList<>();
        String channelName = (validateName(channel) == false)
                ? null : channel.getChannel().getName();
        if ((channel != null) && (channel.getChannel() != null) && (channelName == null)) {
            final List<UserProfile> users = channel.getSubscribers();
            if (BSValidationHelper.validateEmpty(users)) {
                for (UserProfile profile : users) {
                    final String userFullName = profile.getDisplayName();
                    if (BSValidationHelper.validateEmpty(userFullName)) {
                        if (!userFullName.equalsIgnoreCase(K_IGNORED_USER)) {
                            names.add(userFullName);
                        }
                    }
                }
            }
            channelName = TextUtils.join(", ", names);
        }
        if (channelName == null) {
            channelName = "";
        }
        return channelName;
    }

    /**
     * Method which provide the getting of the short name for the {@link ChannelDetail}
     *
     * @param channel instance of {@link ChannelDetail}
     * @return {@link String} value of the channel short name
     */
    public static String getChannelShortName(@Nullable final ChannelDetail channel) {
        String channelName = "NA";
        if (channel != null) {
            final List<UserProfile> users = channel.getSubscribers();
            if (BSValidationHelper.validateEmpty(users)) {
                for (UserProfile profile : users) {
                    final String userFullName = profile.getDisplayName();
                    if (BSValidationHelper.validateEmpty(userFullName)) {
                        if (!userFullName.equalsIgnoreCase(K_IGNORED_USER)) {
                            channelName = (profile.getFirstName().substring(0, 1)
                                    + profile.getLastName().substring(0, 1)).trim();
                            if (!isEmpty(channelName)) {
                                break;
                            }
                        }
                    }
                }
            }
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
    public static String getChannelDescription(@Nullable final Context context,
                                               @Nullable final ChannelDetail channel) {
        String result = "";
        if (channel != null) {
            final List<MMXMessage> messages = channel.getMessages();
            if (BSValidationHelper.validateEmpty(messages)) {
                final MMXMessage message = messages.get(0);
                if (message != null) {
                    result = KitMessageHelper.getDescription(context, message);
                }
            }
            if (BSValidationHelper.isEmpty(result)) {
                result = "No messages";
            }
        }
        return result;
    }

    /**
     * Method which proivde the getting of the last content of the {@link MMXMessage}
     *
     * @param channel instance of {@link ChannelDetail}
     * @return {@link String} value of the last of the {@link MMXMessage}
     */
    @NonNull
    @DrawableRes
    public static int getChannelIcon(@Nullable final ChannelDetail channel) {
        int result = KitMessageType.UNKNOWN.getIcon();
        if (channel != null) {
            final List<MMXMessage> messages = channel.getMessages();
            if (BSValidationHelper.validateEmpty(messages)) {
                final MMXMessage message = messages.get(0);
                if (message != null) {
                    result = KitMessageHelper.getIcon(message);
                }
            }
        }
        return result;
    }

    /**
     * Method which provide the checking if channel contain the filter data
     *
     * @param channelDetail channel details
     * @param filter        filter
     * @return checking value
     */
    public static boolean isContainKeyword(@Nullable final ChannelDetail channelDetail,
                                           @NonNull final KitChannelFilter filter) {
        if (filter == KitChannelFilter.NONE) {
            return false;
        }
        final String filterValue = filter.getFilterKeyword();
        if ((channelDetail != null) &&
                (channelDetail.getChannel() != null) &&
                (channelDetail.getChannel().getSummary() != null)) {
            final MMXChannel channel = channelDetail.getChannel();
            return channel.getSummary().contains(filterValue);
        }
        return false;
    }

    //==============================================================================================
    //                                      VALIDATION
    //==============================================================================================

    /**
     * Method which provide the name validating
     *
     * @param channelDetail channel details
     * @return validation results
     */
    private static boolean validateName(@Nullable final ChannelDetail channelDetail) {
        if ((channelDetail == null) || (channelDetail.getChannel() == null)) {
            return false;
        }
        return validateName(channelDetail.getChannel());
    }

    /**
     * Method which provide the name validating
     *
     * @param channel channel
     * @return validation results
     */
    private static boolean validateName(@Nullable final MMXChannel channel) {
        if ((channel == null) || (TextUtils.isEmpty(channel.getName()) == true)) {
            return false;
        }
        return validateName(channel.getName());
    }

    /**
     * Method which provide the name validating
     *
     * @param name channel name
     * @return validation results
     */
    private static boolean validateName(String name) {
        if (TextUtils.isEmpty(name) == true) {
            return false;
        }
        Pattern pattern = Pattern.compile(K_NAME_REGEXP);
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }
}
