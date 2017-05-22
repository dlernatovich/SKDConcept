package com.artlite.ckconcept.constants;

import android.support.annotation.NonNull;

import com.artlite.bslibrary.ui.view.BSView;

/**
 * Class which provide the keep constants of the events
 */

public final class KitEvent {

    //==============================================================================================
    //                                          PREFIX
    //==============================================================================================

    /**
     * {@link String} value of the prefix for the events
     */
    private static final String K_EVENT_PREFIX = "[KIT_EVENT]";

    //==============================================================================================
    //                                          EVENTS
    //==============================================================================================

    /**
     * Instance of the {@link BSView.Event}
     *
     * @see com.artlite.ckconcept.ui.views.channels.KitChannelsView
     */
    public static final BSView.Event CREATE_CHANNEL = createEvent("KitChannelsView:CreateChannel");

    //==============================================================================================
    //                                          METHODS
    //==============================================================================================

    /**
     * Method which provide the getting of the {@link BSView.Event} from the {@link String} value
     * of the event name
     *
     * @param name {@link String} value of the event name
     * @return instance of the {@link BSView.Event}
     */
    private static BSView.Event createEvent(@NonNull final String name) {
        return new BSView.Event(getEventName(name));
    }

    /**
     * Method which provide the getting of the Event name
     *
     * @param name {@link String} value of the event name
     * @return formatted {@link String} value of the event name
     */
    @NonNull
    private static String getEventName(@NonNull String name) {
        return String.format("%s%s", K_EVENT_PREFIX, name);
    }

}
