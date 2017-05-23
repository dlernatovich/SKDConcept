package com.artlite.ckconcept.callbacks;

import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.magnet.mmx.client.api.ChannelDetail;

/**
 * Callback which provide the callback for the view with the channels
 */
public interface OnKitChannelsCallback {

    /**
     * Method which provide the functional when user press of the {@link ChannelDetail}
     *
     * @param index   {@link Integer} value of the {@link ChannelDetail} index
     * @param channel instance of the {@link ChannelDetail}
     */
    void onChannelClick(int index, @NonNull final ChannelDetail channel);

    /**
     * Method which provide the functional when user do the long press of the {@link ChannelDetail}
     *
     * @param index   {@link Integer} value of the {@link ChannelDetail} index
     * @param channel instance of the {@link ChannelDetail}
     */
    void onChannelLongClick(int index, @NonNull final ChannelDetail channel);

    /**
     * Method which provide the the functional when user sent the action
     * for the {@link com.artlite.ckconcept.ui.views.channels.KitChannelsView}
     *
     * @param recycleEvent instance of the {@link RecycleEvent}
     * @param index        {@link Integer} value of the {@link ChannelDetail} index
     * @param channel      instance of the {@link ChannelDetail}
     */
    void onActionReceived(@NonNull RecycleEvent recycleEvent,
                          int index,
                          @NonNull ChannelDetail channel);
}
