package com.artlite.ckconcept.helpers.channel;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.magnet.max.android.helpers.abs.MaxCoreBaseHelper;
import com.magnet.mmx.client.api.ChannelDetailOptions;

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
}
