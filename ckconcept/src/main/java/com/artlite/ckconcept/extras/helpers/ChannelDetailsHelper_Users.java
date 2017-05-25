package com.artlite.ckconcept.extras.helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.magnet.max.android.User;
import com.magnet.max.android.UserProfile;
import com.magnet.mmx.client.api.ChannelDetail;

import java.util.List;

/**
 * Created by Artli on 25.11.2016.
 */

class ChannelDetailsHelper_Users extends ChannelDetailsHelper_Activity {

    /**
     * Method which provide the getting name from {@link UserProfile}
     *
     * @param userProfile instance of the {@link UserProfile}
     * @return name for {@link UserProfile}
     */
    @NonNull
    protected static String getProfileID(@Nullable final UserProfile userProfile) {
        final StringBuilder builder = new StringBuilder("");
        if (userProfile == null) {
            return builder.toString().trim();
        }
        final String id = (userProfile.getUserIdentifier() != null) ?
                userProfile.getUserIdentifier() : "";
        builder.append(id);
        return builder.toString().trim();
    }

    /**
     * Method which provide the getting name from {@link UserProfile}
     *
     * @param userProfile instance of the {@link UserProfile}
     * @return name for {@link UserProfile}
     */
    @NonNull
    protected static String getProfileName(@Nullable final UserProfile userProfile) {
        final StringBuilder builder = new StringBuilder("");
        if (userProfile == null) {
            return builder.toString().trim();
        }
        final String ownerName = (userProfile.getDisplayName() != null) ?
                userProfile.getDisplayName() : (String.format("%s %s",
                (userProfile.getFirstName() != null)
                        ? userProfile.getFirstName() : "",
                (userProfile.getLastName() != null)
                        ? userProfile.getLastName() : ""));
        builder.append(ownerName);
        return builder.toString().trim();
    }

    /**
     * Method which provide checking if current profile is you
     *
     * @param profile profile to check
     * @return checking result
     */
    public static boolean isCurrentUser(@Nullable final UserProfile profile) {
        final String currentUserID = User.getCurrentUserId();
        if ((TextUtils.isEmpty(currentUserID) == false) &&
                (profile != null) &&
                (TextUtils.equals(currentUserID, profile.getUserIdentifier()) == true)) {
            return true;
        }
        return false;
    }

    /**
     * Method which provide the getting the user with whom current user is chatting, if channel have
     * only owner it return owner
     *
     * @param channelDetail channel details
     * @return instance of the {@link UserProfile}
     */
    @Nullable
    public static UserProfile getOpositeUser(@Nullable final ChannelDetail channelDetail) {
        if (channelDetail == null) {
            return null;
        }
        final List<UserProfile> users = channelDetail.getSubscribers();
        UserProfile result = null;
        if ((users == null) && (users.isEmpty() == true)) {
            return channelDetail.getOwner();
        }

        for (final UserProfile profile : users) {
            if (isCurrentUser(profile) == false) {
                result = profile;
                break;
            }
        }

        if (result == null) {
            result = channelDetail.getOwner();
        }
        return result;
    }

    /**
     * Method which provide the getting of the oposite user ID
     *
     * @param channelDetail channel details
     * @return oposite user ID
     */
    protected static String getOpositeUserID(@Nullable final ChannelDetail channelDetail) {
        return getProfileID(getOpositeUser(channelDetail));
    }
}
