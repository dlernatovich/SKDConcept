package com.artlite.ckconcept.callbacks;

import com.magnet.mmx.client.api.MMX;
import com.magnet.mmx.client.api.MMXMessage;

/**
 * Created by dlernatovich on 6/2/2017.
 */

public abstract class OnKitMessagesCallback extends MMX.EventListener {

    /**
     * Default constructor for the {@link OnKitMessagesCallback}
     */
    public OnKitMessagesCallback() {
        register();
    }

    /**
     * Method which provide the action when message received
     *
     * @param message instance of the {@link MMXMessage}
     * @return if message received
     */
    @Override
    public abstract boolean onMessageReceived(MMXMessage message);

    /**
     * Method which provide the registration of the {@link MMX.EventListener}
     */
    public final void register() {
        MMX.registerListener(this);
    }

    /**
     * Method which provide the registration of the {@link MMX.EventListener}
     */
    public final void unregister() {
        MMX.unregisterListener(this);
    }
}
