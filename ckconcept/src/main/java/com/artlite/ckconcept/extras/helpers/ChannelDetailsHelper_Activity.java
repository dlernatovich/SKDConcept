package com.artlite.ckconcept.extras.helpers;

import android.support.annotation.Nullable;

import com.magnet.max.android.callbacks.MaxCoreActionCallback;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;

/**
 * Created by Artli on 24.11.2016.
 */

class ChannelDetailsHelper_Activity extends ChannelDetailsHelper_Channels {

    //==============================================================================================
    //                              ACTIVE CHANNEL
    //==============================================================================================

    /**
     * Method which provide the resetting of the unread count
     *
     * @param detail channel details
     */
    public static void setChannelActive(@Nullable final ChannelDetail detail) {
        setChannelActive(detail, null);
    }

    /**
     * Method which provide the resetting of the unread count
     *
     * @param detail   channel details
     * @param callback server callback
     */
    public static void setChannelActive(@Nullable final ChannelDetail detail,
                                        @Nullable final MaxCoreActionCallback<MMXChannel> callback) {
        if (detail != null) {
            setChannelActive(detail.getChannel(), callback);
        }
    }

    /**
     * Method which provide the resetting of the unread count
     *
     * @param channel channel instance
     */
    public static void setChannelActive(@Nullable final MMXChannel channel) {
        setChannelActive(channel, null);
    }

    /**
     * Method which provide the resetting of the unread count
     *
     * @param channel  channel instance
     * @param callback server callback
     */
    public static void setChannelActive(@Nullable final MMXChannel channel,
                                        @Nullable final MaxCoreActionCallback<MMXChannel> callback) {
        final String methodName = "setChannelActive(@Nullable final MMXChannel channel)";
        if (channel != null) {
            channel.setActive(new MMXChannel.OnFinishedListener<Void>() {
                @Override
                public void onSuccess(Void result) {
                    onExecuteCallback(methodName, true, null, channel, callback);
                }

                @Override
                public void onFailure(MMXChannel.FailureCode code, Throwable throwable) {
                    onExecuteCallback(methodName, false, throwable, null, callback);
                }
            });
        }
    }

    //==============================================================================================
    //                                  INACTIVE CHANNEL
    //==============================================================================================

    /**
     * Method which provide the resetting of the unread count
     *
     * @param detail channel details
     */
    public static void setChannelInActive(@Nullable final ChannelDetail detail) {
        setChannelInActive(detail, null);
    }

    /**
     * Method which provide the resetting of the unread count
     *
     * @param detail   channel details
     * @param callback server callback
     */
    public static void setChannelInActive(@Nullable final ChannelDetail detail,
                                          @Nullable final MaxCoreActionCallback<MMXChannel> callback) {
        if (detail != null) {
            setChannelInActive(detail.getChannel(), callback);
        }
    }

    /**
     * Method which provide the resetting of the unread count
     *
     * @param channel channel instance
     */
    public static void setChannelInActive(@Nullable final MMXChannel channel) {
        setChannelInActive(channel, null);
    }

    /**
     * Method which provide the resetting of the unread count
     *
     * @param channel  channel instance
     * @param callback server callback
     */
    public static void setChannelInActive(@Nullable final MMXChannel channel,
                                          @Nullable final MaxCoreActionCallback<MMXChannel> callback) {
        final String methodName = "setChannelInActive(@Nullable final MMXChannel channel)";
        if (channel != null) {
            channel.setInActive(new MMXChannel.OnFinishedListener<Void>() {
                @Override
                public void onSuccess(Void result) {
                    onExecuteCallback(methodName, true, null, channel, callback);
                }

                @Override
                public void onFailure(MMXChannel.FailureCode code, Throwable throwable) {
                    onExecuteCallback(methodName, false, throwable, null, callback);
                }
            });
        }
    }
}
