package com.artlite.ckconcept.managers.channel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.artlite.ckconcept.ui.abs.channels.KitBaseChannelsView;
import com.magnet.max.android.User;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which provide the caching channels temporary for
 * {@link KitBaseChannelsView}
 * Created by dlernatovich on 11/17/2016.
 */

public final class KitChannelsCacheManager {

    /**
     * Instance of the {@link KitChannelsCacheManager}
     */
    private static KitChannelsCacheManager instance;

    /**
     * {@link List} of the {@link ChannelDetail}
     */
    private final List<ChannelDetail> cache;

    /**
     * {@link String} value of the {@link User} Id
     */
    private String userID;

    /**
     * Method which provide the getting instance
     *
     * @return cache instance
     */
    public static KitChannelsCacheManager getInstance() {
        if (instance == null) {
            instance = new KitChannelsCacheManager();
        }
        return instance;
    }

    /**
     * Default constructor
     */
    private KitChannelsCacheManager() {
        cache = new ArrayList<>();
    }


    /**
     * Method which provide the getting channels
     *
     * @return channels {@link List} of the {@link ChannelDetail}
     */
    @NonNull
    public final List<ChannelDetail> getChannels() {
        final String currentUserID = User.getCurrentUserId();
        synchronized (cache) {
            final List<ChannelDetail> channels = new ArrayList<>();
            if ((currentUserID != null) &&
                    (this.userID != null) &&
                    (this.userID.equalsIgnoreCase(currentUserID) == true)) {
                channels.addAll(cache);
            }
            return channels;
        }
    }

    /**
     * Method which provide the set channels
     *
     * @param channels {@link List} of the {@link ChannelDetail}
     */
    public void setChannels(@Nullable final List<ChannelDetail> channels) {
        final String currentUserID = User.getCurrentUserId();
        synchronized (cache) {
            if ((currentUserID != null) &&
                    (currentUserID.isEmpty() == false)) {
                this.userID = currentUserID;
            }

            if ((channels != null) &&
                    (channels.isEmpty() == false)) {
                cache.clear();
                cache.addAll(channels);
            }
        }
    }

    //==============================================================================================
    //                                  UPDATE CACHE NAME
    //==============================================================================================

    /**
     * Method which provide the update of the channel name
     *
     * @param detail  instance of the {@link ChannelDetail}
     * @param newName {@link String} value of the {@link ChannelDetail} new name
     */
    public void updateChannelName(@Nullable final ChannelDetail detail,
                                  @Nullable final String newName) {
        if (detail != null) {
            updateChannelName(detail.getChannel(), newName);
        }
    }

    /**
     * Method which provide the update of the channel name
     *
     * @param mmxChannel instance of the {@link MMXChannel}
     * @param newName    {@link String} value of the {@link ChannelDetail} new name
     */
    public void updateChannelName(@Nullable final MMXChannel mmxChannel,
                                  @Nullable final String newName) {
        if (mmxChannel != null) {
            updateChannelName(mmxChannel.getId(), newName);
        }
    }

    /**
     * Method which provide the update of the channel name
     *
     * @param channelID {@link String} value of the {@link MMXChannel} Id
     * @param newName   {@link String} value of the {@link ChannelDetail} new name
     */
    public void updateChannelName(@Nullable final String channelID,
                                  @Nullable final String newName) {
        synchronized (cache) {
            if ((TextUtils.isEmpty(channelID) == false) &&
                    (TextUtils.isEmpty(newName) == false)) {
                final MMXChannel channel = getChannel(channelID);
                if (channel != null) {
                    channel.name(newName);
                }
            }
        }
    }


    //==============================================================================================
    //                                      GET CHANNEL
    //==============================================================================================

    /**
     * Method which provide the getting channel by ID
     *
     * @param channelID {@link String} value of the {@link MMXChannel} Id
     * @return instance of the {@link MMXChannel}
     */
    @Nullable
    public MMXChannel getChannel(@Nullable final String channelID) {
        synchronized (cache) {
            for (final ChannelDetail detail : cache) {
                if ((detail.getChannel() != null) &&
                        (TextUtils.isEmpty(detail.getChannel().getId()) == false)) {
                    final String innerChannelID = detail.getChannel().getId();
                    if (innerChannelID.equalsIgnoreCase(channelID) == true) {
                        return detail.getChannel();
                    }
                }
            }
            return null;
        }
    }

    /**
     * Method which provide the getting of the channel position
     * (TODO: TEMPORARY FIX, BECAUSE FOR NOW EQUALS AND HASHCODE FOR MMXCHANNEL CLASS WORKING HORRIBLE)
     *
     * @param channelID {@link String} value of the {@link MMXChannel} Id
     * @return channel position
     */
    protected int getChannelPosition(@Nullable final String channelID) {
        synchronized (cache) {
            int index = -1;
            for (final ChannelDetail detail : cache) {
                index++;
                if ((detail.getChannel() != null) &&
                        (TextUtils.isEmpty(detail.getChannel().getId()) == false)) {
                    final String innerChannelID = detail.getChannel().getId();
                    if (innerChannelID.equalsIgnoreCase(channelID) == true) {
                        return index;
                    }
                }
            }
            return -1;
        }
    }


    //==============================================================================================
    //                                      CLEAR UNREAD
    //==============================================================================================

    /**
     * Method which provide the clear unread count for messages
     *
     * @param detail instance of the {@link ChannelDetail}
     */
    public void clearUnreadMessages(@Nullable final ChannelDetail detail) {
        if (detail != null) {
            clearUnreadMessages(detail.getChannel());
        }
    }

    /**
     * Method which provide the clear unread count for messages
     *
     * @param channel instance of the {@link MMXChannel}
     */
    public void clearUnreadMessages(@Nullable final MMXChannel channel) {
        if (channel != null) {
            clearUnreadMessages(channel.getId());
        }
    }

    /**
     * Method which provide the clear unread count for messages
     *
     * @param id {@link String} value of the {@link MMXChannel} Id
     */
    public void clearUnreadMessages(@Nullable final String id) {
        if (TextUtils.isEmpty(id) == false) {
            final MMXChannel channel = getChannel(id);
            if (channel != null) {
                channel.setUnreadCount(new Long(0));
            }
        }
    }

    //==============================================================================================
    //                                      DELETE
    //==============================================================================================

    /**
     * Method which provide the delete channel from cache
     *
     * @param detail instance of the {@link ChannelDetail}
     */
    public void delete(@Nullable final ChannelDetail detail) {
        if (detail != null) {
            delete(detail.getChannel());
        }
    }

    /**
     * Method which provide the delete channel from cache
     *
     * @param channel instance of the {@link MMXChannel}
     */
    public void delete(@Nullable final MMXChannel channel) {
        if (channel != null) {
            delete(channel.getId());
        }
    }

    /**
     * Method which provide the delete channel from cache
     *
     * @param id {@link String} value of the {@link MMXChannel} Id
     */
    public void delete(@Nullable final String id) {
        if (TextUtils.isEmpty(id) == false) {
            final int index = getChannelPosition(id);
            if (index != -1) {
                synchronized (cache) {
                    if ((index >= 0) && (index < cache.size())) {
                        cache.remove(index);
                    }
                }
            }
        }
    }

}
