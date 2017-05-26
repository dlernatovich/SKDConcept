package com.artlite.ckconcept.helpers.user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Artli on 12.10.2016.
 */
public final class KitUserNameHelper {

    private static final String K_NOT_AVAILABLE_VALUE = "NA";

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
            return K_NOT_AVAILABLE_VALUE;
        }
        return builder.toString().trim().toUpperCase();
    }
}
