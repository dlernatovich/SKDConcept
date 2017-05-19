package com.artlite.ckconcept.callbacks;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.mvp.view.KitWidgetBaseView;

/**
 * Class which provide the listener for the {@link KitWidgetBaseView}
 */

public interface OnKitViewCallback {

    /**
     * Method which provide the action when {@link BSView.Event} received
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     * @param event   instance of the {@link BSView.Event}
     */
    void onCreateEventReceived(@NonNull Context context,
                               @NonNull BSView view,
                               @NonNull BSView.Event event);

    /**
     * Method which provide the action when user press on the channel object
     *
     * @param index  current index
     * @param object current object
     */
    void onItemClick(int index, @NonNull BaseObject object);

    /**
     * Method which provide the action when user doing the long press on item
     *
     * @param index  index
     * @param object object
     */
    void onItemLongClick(int index, @NonNull BaseObject object);

    /**
     * Method which provide the action listening
     *
     * @param recycleEvent event
     * @param index        index
     * @param object       object
     */
    void onActionReceived(@NonNull RecycleEvent recycleEvent,
                          int index,
                          @NonNull BaseObject object);

    /**
     * Method which provide the action when menu item was press
     *
     * @param object instance of the {@link KitMenuModel}
     */
    void onMenuItemClick(@NonNull KitMenuModel object);
}
