package com.artlite.ckconcept.helpers.channel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.magnet.max.android.callbacks.MaxCoreActionCallback;
import com.magnet.max.android.rest.options.CacheOptions;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artli on 23.11.2016.
 */

class KitChannelDetailsHelper_Channels extends KitChannelDetailsHelper_Unread {
    //==============================================================================================
    //                              GET ALL CHANNELS DETAILS
    //==============================================================================================

    /**
     * Method which provide the getting of the all channels with unreadCount
     *
     * @param callback server callback
     */
    public static void getAllChannels(@Nullable final MaxCoreActionCallback<List<ChannelDetail>> callback) {
        getAllChannels(null, callback);
    }

    /**
     * Method which provide the getting of the all channels with unreadCount
     *
     * @param callback server callback
     */
    public static void getAllChannels(@Nullable final CacheOptions options,
                                      @Nullable final MaxCoreActionCallback<List<ChannelDetail>> callback) {
        final String methodName = "getAllChannels(callback)";
        final List<MMXChannel> channels = new ArrayList<>();
        MMXChannel.getAllSubscriptions(new MMXChannel.OnFinishedListener<List<MMXChannel>>() {
            @Override
            public void onSuccess(List<MMXChannel> result) {
                onExecuteCallback(methodName, true, null, result, null);
                if (result != null) {
                    channels.addAll(result);
                }
                getChannelsDetails(channels, options, callback);
            }

            @Override
            public void onFailure(MMXChannel.FailureCode code, Throwable throwable) {
//                onExecuteCallback(methodName, false, throwable, null, null);
                getChannelsDetails(channels, options, callback);
            }
        });
    }

    /**
     * Method which provide the getting of the all of channels details
     *
     * @param channels channels from server
     * @param callback server callback
     */
    private static void getChannelsDetails(@NonNull final List<MMXChannel> channels,
                                           @Nullable final MaxCoreActionCallback<List<ChannelDetail>> callback) {
        getChannelsDetails(channels, null, callback);
    }

    /**
     * Method which provide the getting of the all of channels details
     *
     * @param channels channels from server
     * @param callback server callback
     */
    private static void getChannelsDetails(@NonNull final List<MMXChannel> channels,
                                           @Nullable final CacheOptions options,
                                           @Nullable final MaxCoreActionCallback<List<ChannelDetail>> callback) {
        final String methodName = "getChannelsDetails(channels, callback)";
        final List<ChannelDetail> channelDetails = new ArrayList<>();
        MMXChannel.getChannelDetail(channels, K_CHANNEL_DETAIL_OPTIONS, options,
                new MMXChannel.OnFinishedListener<List<ChannelDetail>>() {
                    @Override
                    public void onSuccess(List<ChannelDetail> result) {
                        onExecuteCallback(methodName, true, null, result, null);
                        if (result != null) {
                            channelDetails.addAll(result);
                        }
                        getChannelsUnreadCount(channelDetails, options, callback);

                    }

                    @Override
                    public void onFailure(MMXChannel.FailureCode code, Throwable throwable) {
//                        onExecuteCallback(methodName, false, throwable, null, null);
                        getChannelsUnreadCount(channelDetails, options, callback);
                    }
                });
    }

    /**
     * Method which provide the getting of the channels unread count messages
     *
     * @param channelDetails channels
     * @param callback       callback
     */
    private static void getChannelsUnreadCount(@NonNull final List<ChannelDetail> channelDetails,
                                               @Nullable final MaxCoreActionCallback<List<ChannelDetail>> callback) {
        getChannelsUnreadCount(channelDetails, null, callback);
    }

    /**
     * Method which provide the getting of the channels unread count messages
     *
     * @param channelDetails channels
     * @param callback       callback
     */
    private static void getChannelsUnreadCount(@NonNull final List<ChannelDetail> channelDetails,
                                               @Nullable final CacheOptions options,
                                               @Nullable final MaxCoreActionCallback<List<ChannelDetail>> callback) {
        final String methodName = "getChannelsUnreadCount(channelDetails, callback)";
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<MMXChannel> channels = new ArrayList<>();
                for (final ChannelDetail detail : channelDetails) {
                    final MMXChannel channel = detail.getChannel();
                    if (channel != null) {
                        channels.add(channel);
                    }
                }
                MMXChannel.getUnreadCount(channels, options, new MMXChannel.OnFinishedListener<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        onExecuteCallback(methodName, true, null, channelDetails, callback);
                    }

                    @Override
                    public void onFailure(MMXChannel.FailureCode code, Throwable throwable) {
                        onExecuteCallback(methodName, false, throwable, channelDetails, callback);
                    }
                });
            }
        }).start();
    }
}
