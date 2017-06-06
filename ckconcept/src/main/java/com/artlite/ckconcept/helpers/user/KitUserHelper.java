package com.artlite.ckconcept.helpers.user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.magnet.max.android.User;

/**
 * Class which provide the functional for the {@link User}
 */

public final class KitUserHelper extends BSBaseHelper {

    /**
     * {@link String} constants of the not available
     */
    private static final String K_NOT_AVAILABLE_SHORT_VALUE = "NA";

    /**
     * {@link String} constants of the not available
     */
    private static final String K_NOT_AVAILABLE_VALUE = "Not available";

    /**
     * Method which provide the checking if current {@link User} is me
     *
     * @param user instance of {@link User}
     * @return checking result
     */
    public static boolean isMe(@Nullable final User user) {
        if (user == null) {
            return false;
        }
        return isMe(user.getUserIdentifier());
    }

    /**
     * Method which provide the checking if current {@link User} is me
     *
     * @param userId instance of {@link User}
     * @return checking result
     */
    public static boolean isMe(@Nullable final String userId) {
        if (userId == null) {
            return false;
        }
        final String currentId = User.getCurrentUserId();
        if (currentId != null) {
            return currentId.equals(userId);
        }
        return false;
    }

    /**
     * Method which provide the getting of the short user name
     * (FOR EXAMPLE: Joy Koul -> JK)
     *
     * @param userName user name for short
     * @return shroted user name
     */
    @NonNull
    public static String getShortName(@Nullable final String userName) {
        StringBuilder builder = new StringBuilder();
        if ((userName != null) && (userName.isEmpty() == false)) {
            userName.trim();
            String[] nameArray = userName.split(" ");
            for (String name : nameArray) {
                if (name.length() > 0) {
                    builder.append(name.charAt(0));
                }
            }
        }
        if (builder.toString().isEmpty() == true) {
            return K_NOT_AVAILABLE_SHORT_VALUE;
        }
        return builder.toString().trim().toUpperCase();
    }

    /**
     * Method which provide the getting of the {@link String} value of the full name
     *
     * @param user instance of the {@link User}
     * @return {@link String} value of the full name
     */
    @NonNull
    public static String getFullName(@Nullable final User user) {
        final StringBuilder builder = new StringBuilder();
        if (user != null) {
            final String userName = user.getFirstName() + " " + user.getLastName();
            if (userName != null) {
                builder.append(userName);
            }
        }
        if (builder.toString().isEmpty() == true) {
            return K_NOT_AVAILABLE_VALUE;
        }
        return builder.toString().trim();
    }

}
