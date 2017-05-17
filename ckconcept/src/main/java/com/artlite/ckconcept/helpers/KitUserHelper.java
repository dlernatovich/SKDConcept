package com.artlite.ckconcept.helpers;

import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;
import com.magnet.max.android.User;

/**
 * Class which provide the functional for the {@link User}
 */

public final class KitUserHelper extends BSBaseHelper {

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

}
