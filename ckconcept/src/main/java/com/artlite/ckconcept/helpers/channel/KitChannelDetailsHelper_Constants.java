package com.artlite.ckconcept.helpers.channel;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.magnet.max.android.helpers.abs.MaxCoreBaseHelper;
import com.magnet.mmx.client.api.ChannelDetailOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artli on 23.11.2016.
 */

class KitChannelDetailsHelper_Constants extends MaxCoreBaseHelper {

    protected static final String K_NAME_REGEXP = "([-])|(NO_NAME)|(^\\d*$)";

    /**
     * Receiving channels options
     */
    protected static final ChannelDetailOptions K_CHANNEL_DETAIL_OPTIONS = new ChannelDetailOptions
            .Builder()
            .numOfMessages(1)
            .numOfSubcribers(100)
            .build();

    /**
     * Message types
     */
    public enum MessageType {
        MAP, POOL, TEXT, VOTE, CHECKLIST, NONE, PHOTO;
    }

    /**
     * Channel filter enumerator
     */
    public enum ChannelFilter {
        APPROVAL("MMXApproval"),
        NONE("");

        private final String filterKeyword;

        /**
         * Default constructor
         *
         * @param filterKeyword filter keyword
         */
        ChannelFilter(@NonNull final String filterKeyword) {
            this.filterKeyword = filterKeyword;
        }

        /**
         * Get filter keyword
         *
         * @return method which provide the getting of the keyword filtering
         */
        @NonNull
        public String getFilterKeyword() {
            return filterKeyword;
        }
    }


    /**
     * Class which provide the ImageBitmap cache
     */
    protected static class Cache {
        private static Cache instance;
        private final Map<String, Drawable> cache;

        /**
         * Default constructor
         */
        private Cache() {
            cache = new HashMap<>();
        }

        /**
         * Method which provide the getting instance of the {@link Cache}
         *
         * @return instance of the {@link Cache}
         */
        public static Cache getInstance() {
            if (instance == null) {
                instance = new Cache();
            }
            return instance;
        }

        /**
         * Method which provide the {@link GlideDrawable} setting
         *
         * @param userID   user ID
         * @param drawable instance of the {@link GlideDrawable}
         */
        public void setDrawable(@Nullable final String userID,
                                @Nullable final Drawable drawable) {
            synchronized (cache) {
                if ((TextUtils.isEmpty(userID) == false) &&
                        (drawable != null)) {
                    cache.put(userID, drawable);
                }
            }
        }

        /**
         * Method which provide the getting drawable
         *
         * @param userID user ID
         * @return drawable
         */
        @Nullable
        public final Drawable getDrawable(@Nullable final String userID) {
            synchronized (cache) {
                if ((TextUtils.isEmpty(userID) == false) &&
                        (cache.containsKey(userID) == true)) {
                    return cache.get(userID);
                }
                return null;
            }
        }
    }
}
