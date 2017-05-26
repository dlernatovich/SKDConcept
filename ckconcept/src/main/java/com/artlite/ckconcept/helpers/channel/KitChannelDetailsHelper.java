package com.artlite.ckconcept.helpers.channel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.ImageView;

import com.artlite.bslibrary.managers.BSContextManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.magnet.max.android.UserProfile;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which provide the support actions for the
 * {@link com.magnet.mmx.client.api.ChannelDetail}
 * Created by dlernatovich on 11/17/2016.
 */

public final class KitChannelDetailsHelper extends KitChannelDetailsHelper_Users {

    /**
     * Method which provide the setting of the channel avatar
     *
     * @param context       context
     * @param imageView     image view for avatar set
     * @param channelDetail channel details
     */
    public static void setChannelAvatar(@Nullable final Context context,
                                        @Nullable final ImageView imageView,
                                        @Nullable final ChannelDetail channelDetail) {
        final String methodName = "setChannelAvatar()";
        if (nullValidate(methodName, context, imageView, channelDetail) == true) {
            final String url = getChannelAvatar(channelDetail);
            final String ownerID = getOpositeUserID(channelDetail);
            imageView.setImageDrawable(Cache.getInstance().getDrawable(ownerID));
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .fitCenter()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e,
                                                   String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFirstResource) {
                            onExecuteCallback(methodName, false, e, null, null);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource,
                                                       String model,
                                                       Target<GlideDrawable> target,
                                                       boolean isFromMemoryCache,
                                                       boolean isFirstResource) {
                            Cache.getInstance().setDrawable(ownerID, resource);
                            if (imageView != null) {
                                imageView.setImageDrawable(resource);
                            }
                            return false;
                        }
                    })
                    .into(imageView);
        }
    }

    /**
     * Method which provide the getting {@link ChannelDetail} avatar link
     *
     * @param channelDetail channel details
     * @return avatar URL
     */
    @NonNull
    public static String getChannelAvatar(@Nullable final ChannelDetail channelDetail) {
        final StringBuilder result = new StringBuilder("");
        if (channelDetail != null) {
            final UserProfile user = getOpositeUser(channelDetail);
            if (user != null) {
                final String url = user.generateAvatarUrl();
                if (TextUtils.isEmpty(url) == false) {
                    result.append(url);
                }
            }
        }
        return result.toString().trim();
    }

    /**
     * Method which provide the getting of the short channel name
     *
     * @param channelDetail channel details
     * @return channel short name
     */
    @NonNull
    public static String getChannelShortName(@Nullable final ChannelDetail channelDetail) {
        final String channelName = getProfileName(getOpositeUser(channelDetail));
        final String[] names = channelName.split(" ");
        final StringBuilder result = new StringBuilder("");
        for (int i = 0; i < names.length; i++) {
            final String name = names[i];
            if (TextUtils.isEmpty(name) == false) {
                result.append(name.charAt(0));
            }
        }
        return result.toString().trim();
    }

    /**
     * Method which provide the getting of the channel name
     *
     * @return channel name
     */
    @NonNull
    public static String getChannelName(@Nullable final ChannelDetail channelDetail) {
        final StringBuilder builder = new StringBuilder("");
        if (channelDetail == null) {
            return builder.toString().trim();
        }
        //If channel details not null
        if (validateName(channelDetail) == true) {
            return channelDetail.getChannel().getName();
        }
        final String subscribers = getNameFromSubscribers(channelDetail.getSubscribers());
        if (TextUtils.isEmpty(subscribers)) {
            builder.append(getOwnerName(channelDetail));
        } else {
            builder.append(subscribers);
        }
        return builder.toString().trim();
    }

    /**
     * Method which provide the checking if current conversation is Group chat
     *
     * @return checking results
     */
    public static boolean isGroupChat(@Nullable final ChannelDetail channelDetail) {
        if (channelDetail == null) {
            return false;
        }
        final List<UserProfile> subscribers = channelDetail.getSubscribers();
        if ((subscribers != null) &&
                (subscribers.size() > 2)) {
            return true;
        }
        return false;
    }

    /**
     * Method which provide the getting of the channel name from the subscribers
     *
     * @param users users list
     * @return channel name
     */
    @NonNull
    public static String getNameFromSubscribers(@Nullable final List<UserProfile> users) {
        if ((users == null) ||
                (users.isEmpty())) {
            return "";
        }
        List<String> usersArray = new ArrayList<>();
        for (final UserProfile userProfile : users) {
            if (isCurrentUser(userProfile) == false) {
                final String name = getProfileName(userProfile);
                if (TextUtils.isEmpty(name) == false) {
                    usersArray.add(name);
                }
            }
        }
        Collections.sort(usersArray);
        final String result = TextUtils.join(", ", usersArray);
        return result;
    }

    /**
     * Method which provide the getting channel name from owner
     *
     * @param channelDetail channel details
     * @return channel name
     */
    @NonNull
    public static String getOwnerName(@Nullable final ChannelDetail channelDetail) {
        final StringBuilder builder = new StringBuilder("");
        if (channelDetail == null) {
            return builder.toString().trim();
        }
        final UserProfile owner = channelDetail.getOwner();
        if (owner != null) {
            builder.append(getProfileName(owner));
        }
        return builder.toString().trim();
    }

    /**
     * Method which provide the get {@link ChannelDetail} date
     *
     * @param channel instance of the {@link ChannelDetail}
     * @return formatted {@link java.util.Date}
     */
    @NonNull
    public static String getDate(@Nullable final ChannelDetail channel) {
        final Date currentDate = new Date();
        MMXChannel mmxChannel = null;
        if (channel != null) {
            mmxChannel = channel.getChannel();
        }
        final Date date = (mmxChannel == null)
                ? currentDate : (mmxChannel.getLastTimeActive() == null)
                ? currentDate : (mmxChannel.getLastTimeActive());
        return DateUtils.getRelativeTimeSpanString(date.getTime(),
                currentDate.getTime(),
                DateUtils.SECOND_IN_MILLIS).toString();

    }

    /**
     * Method which provide the getting of the last message
     *
     * @param context         context
     * @param channel         channel
     * @param noMessages      no messages text
     * @param locationMessage location text
     * @param photoMessage    photo message text
     * @return
     */
    @NonNull
    public static String getLastMessage(@Nullable final Context context,
                                        @Nullable final ChannelDetail channel,
                                        @StringRes int noMessages,
                                        @StringRes int locationMessage,
                                        @StringRes int poolMessage,
                                        @StringRes int photoMessage,
                                        @StringRes int answerMessage,
                                        @StringRes int checklistMessage) {
        return KitChannelHelper
                .getChannelDescription(BSContextManager.getRegisteredContext(), channel);
    }

    /**
     * Method which provide the getting of the last message drawable
     *
     * @param context      context
     * @param channel      channel
     * @param messageRes   message image res
     * @param locationRes  location image res
     * @param photoRes     photo image res
     * @param poolRes      poll image res
     * @param voteRes      vote image res
     * @param checklistRes checklist image res
     * @return
     */
    @Nullable
    public static Drawable getLastMessageTypeDrawable(@Nullable final Context context,
                                                      @Nullable final ChannelDetail channel,
                                                      @DrawableRes int messageRes,
                                                      @DrawableRes int locationRes,
                                                      @DrawableRes int photoRes,
                                                      @DrawableRes int poolRes,
                                                      @DrawableRes int voteRes,
                                                      @DrawableRes int checklistRes) {
        return context.getResources().getDrawable(KitChannelHelper.getChannelIcon(channel));
    }

    /**
     * Method which provide the getting of the last message text
     *
     * @param channel channel
     * @return last message text
     */
    @NonNull
    protected static String getLastMessageText(@Nullable final ChannelDetail channel) {
        return KitChannelHelper
                .getChannelDescription(BSContextManager.getRegisteredContext(), channel);
    }

    /**
     * Method which provide the getting of the channel ID
     *
     * @param channelDetail channel details
     * @return channel ID
     */
    @NonNull
    public static String getChannelID(@Nullable final ChannelDetail channelDetail) {
        final StringBuilder result = new StringBuilder("");
        if (channelDetail != null) {
            final MMXChannel channel = channelDetail.getChannel();
            if (channel != null) {
                final String channelID = channel.getIdentifier();
                if (TextUtils.isEmpty(channelID) == false) {
                    result.append(channelID);
                }
            }
        }
        return result.toString().trim();
    }

    /**
     * Method which provide the get owner ID
     *
     * @param channelDetail channel details
     * @return owner ID
     */
    @NonNull
    public static String getOwnerID(@Nullable final ChannelDetail channelDetail) {
        final StringBuilder result = new StringBuilder("");
        if (channelDetail != null) {
            final UserProfile owner = channelDetail.getOwner();
            if (owner != null) {
                final String ownerID = owner.getUserIdentifier();
                if (TextUtils.isEmpty(ownerID) == false) {
                    result.append(ownerID);
                }
            }
        }
        return result.toString().trim();
    }

    /**
     * Method which provide the checking if channel contain the filter data
     *
     * @param channelDetail channel details
     * @param filter        filter
     * @return checking value
     */
    public static boolean isContainKeyword(@Nullable final ChannelDetail channelDetail,
                                           @NonNull final ChannelFilter filter) {
        if (filter == ChannelFilter.NONE) {
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
