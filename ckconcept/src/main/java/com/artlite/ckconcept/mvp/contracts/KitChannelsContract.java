package com.artlite.ckconcept.mvp.contracts;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;

import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.ckconcept.helpers.channel.KitChannelDetailsHelper;
import com.artlite.ckconcept.ui.abs.channels.KitBaseChannelsView;
import com.magnet.max.android.rest.options.CacheOptions;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.ChannelDetailOptions;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.Comparator;
import java.util.List;

/**
 * Contract which provide the functional for channel views
 *
 * @see {@link com.magnet.mmx.client.api.MMXChannel}
 * Created by dlernatovich on 11/17/2016.
 */

public interface KitChannelsContract {

    /**
     * Long click event
     *
     * @see KitBaseChannelsView
     */
    RecycleEvent LONG_CLICK_EVENT = new RecycleEvent(0);

    /**
     * Listener which provide the perform actions when channel is clicking
     */
    interface OnChannelClickListener {
        /**
         * Method which provide the action when user click on channel
         *
         * @param channel channel
         */
        void onChannelClick(@NonNull final ChannelDetail channel);

        /**
         * Method which provide the action when user do a long click on channel
         *
         * @param channel channel object
         */
        void onChannelLongClick(@NonNull final ChannelDetail channel);
    }

    /**
     * Comparartor which provide the sorting of the
     */
    class ChannelsDateComparator implements Comparator<ChannelDetail> {
        @Override
        public int compare(ChannelDetail lhs, ChannelDetail rhs) {
            final MMXChannel lch = lhs.getChannel();
            final MMXChannel rch = rhs.getChannel();
            if (lch.getLastTimeActive() == null || rch.getLastTimeActive() == null) {
                return 0;
            }
            return lch.getLastTimeActive().compareTo(rch.getLastTimeActive());
        }
    }

    /**
     * View class
     */
    interface View extends KitBaseExtrasContract.BaseView {

        /**
         * Method which provide the channels setting to the
         * {@link android.support.v7.widget.RecyclerView}
         *
         * @param channels    channels to set
         * @param isFirstInit check if the first initialization
         */
        void setChannels(@Nullable final List<ChannelDetail> channels,
                         final boolean isFirstInit);

        /**
         * Method which provide the setting channels
         *
         * @param channels channels to set
         */
        void setChannels(@Nullable final List<ChannelDetail> channels);

        /**
         * Method which provide the progress showing
         */
        void showProgress();

        /**
         * Method which provide the hide progress
         */
        void hideProgress();

    }

    /**
     * View class
     */
    abstract class ViewClass extends KitBaseExtrasContract.BaseViewClass implements View {

    }

    /**
     * Presenter
     */
    interface Presenter extends KitBaseExtrasContract.BasePresenter {

        /**
         * Default cache time
         *
         * @see KitChannelsContract.Presenter#getCacheOptions()
         */
        int K_DEFAULT_CACHE_TIME = 60;

        /**
         * Receive channel options
         */
        ChannelDetailOptions CHANNEL_DETAIL_OPTIONS = new ChannelDetailOptions
                .Builder()
                .numOfMessages(1)
                .numOfSubcribers(100)
                .build();

        /**
         * Method which provide the channels receiving
         *
         * @param showRefresh is need to show refresh spinner
         */
        void receiveChannels(final boolean showRefresh);

        /**
         * Method which provide the channels receiving with delay
         *
         * @param delay       delay time
         * @param showRefresh is need to show refresh spinner
         */
        void receiveChannels(boolean showRefresh, final int delay);

        /**
         * Method which provide the progress switching
         *
         * @param isShow is need show
         */
        void switchProgress(final boolean isShow);

        /**
         * Method which provide the channels setting
         *
         * @param channels channels details
         */
        void setChannels(@NonNull final List<ChannelDetail> channels);

        /**
         * Method which provide the getting of the channel layout
         *
         * @return channel layout
         * REQUIREMENT IDS AND TYPES:
         * @+id/container as {@link android.view.View}
         * @+id/image_group as {@link android.widget.ImageView}
         * @+id/view_circle_name as {@link android.view.View}
         * @+id/label_short_name as {@link android.support.v7.widget.AppCompatTextView}
         * @+id/image_channel_avatar as {@link android.widget.ImageView}
         * @+id/label_channel_name as {@link android.support.v7.widget.AppCompatTextView}
         * @+id/label_date as {@link android.support.v7.widget.AppCompatTextView}
         * @+id/label_last_message as {@link android.support.v7.widget.AppCompatTextView}
         * @+id/image_type as {@link android.widget.ImageView}
         * @+id/view_unread as {@link android.view.View}
         */
        @LayoutRes
        int getChannelLayout();

        /**
         * Method which provide the getting of the photo description for channel
         *
         * @return photo text
         */
        @StringRes
        int getPhotoText();

        /**
         * Method which provide the getting of the location description for channel
         *
         * @return location description
         */
        @StringRes
        int getLocationText();

        /**
         * Method which provide the getting of the no messages description for channel
         *
         * @return no messages text
         */
        @StringRes
        int getNoMessageText();

        /**
         * Method which provide the getting of the pool text
         *
         * @return poll text
         */
        @StringRes
        int getPoolText();

        /**
         * Method which provide the getting of the answer text
         *
         * @return answer text
         */
        @StringRes
        int getAnswerText();

        /**
         * Method which provide the getting of the checklist text
         *
         * @return checklist text
         */
        @StringRes
        int getChecklistText();

        /**
         * Method which provide the getting of the refresh color
         *
         * @return refresh color
         */
        @ColorRes
        int getRefreshColor();

        /**
         * Get color for refresh background
         *
         * @return color for refresh background
         */
        @ColorRes
        int getRefreshColorBackground();

        /**
         * Method which provide the getting of the message image resource
         *
         * @return message image resource
         */
        @DrawableRes
        int getMessageImage();

        /**
         * Method which provide the getting of the location image resource
         *
         * @return location image resource
         */
        @DrawableRes
        int getLocationImage();

        /**
         * Method which provide the getting of the photo image resource
         *
         * @return photo image resource
         */
        @DrawableRes
        int getPhotoImage();

        /**
         * Method which provide the getting of the pool image resource
         *
         * @return pool image resource
         */
        @DrawableRes
        int getPoolImage();

        /**
         * Method which provide the getting of the vote image resource
         *
         * @return vote image resource
         */
        @DrawableRes
        int getVoteImage();

        /**
         * Method which provide the getting of the checklist image resource
         *
         * @return checklist image resource
         */
        @DrawableRes
        int getChecklistImage();

        /**
         * Method which provide the getting of the layout manager
         *
         * @param context instance of the {@link Context}
         * @return layout manager
         */
        @NonNull
        RecyclerView.LayoutManager getLayoutManager(@NonNull final Context context);

        /**
         * Method which provide the getting of the channel filter
         *
         * @return channel filter
         */
        @NonNull
        KitChannelDetailsHelper.ChannelFilter getChannelFilter();

        /**
         * Method which provide the channel filtering
         *
         * @param channels channels list
         * @return filtered channels list
         */
        @NonNull
        List<ChannelDetail> filterChannel(@NonNull final List<ChannelDetail> channels);

        /**
         * Method which provide the getting of {@link CacheOptions}
         *
         * @return instance of {@link CacheOptions}
         */
        @NonNull
        CacheOptions getCacheOptions();
    }

}
