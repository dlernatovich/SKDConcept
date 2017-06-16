package com.artlite.skdconcept.ui.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.managers.BSLocationManager;
import com.artlite.bslibrary.managers.BSTransferManager;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.callbacks.OnKitViewCallback;
import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.ui.views.chat.KitChatView;
import com.artlite.skdconcept.R;
import com.magnet.mmx.client.api.ChannelDetail;

public final class DetailsActivity extends BSActivity {

    /**
     * Instance of the {@link ChannelDetail}
     */
    private ChannelDetail channel;

    /**
     * Instance of the {@link KitChatView}
     */
    @FindViewBy(id = R.id.view_chat)
    private KitChatView chatView;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    /**
     * Method which provide the action when Activity is created
     *
     * @param bundle instance of {@link Bundle}
     */
    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        setTitle("Conversation details");
        channel = BSTransferManager.get(this);
        chatView.setChannel(channel);
        chatView.setOnViewCallback(viewCallback);
        BSLocationManager.getInstance().startLocationMonitoring(this);
    }

    /**
     * Method which provide the action when the view is destroy
     */
    @Override
    protected void onDestroy() {
        chatView.onDestroyView();
        super.onDestroy();
    }

    /**
     * Method which provide the defining if need to override of the transition animation
     *
     * @return defining results
     */
    @Override
    protected boolean isOverrideTransitionAnimation() {
        return true;
    }

    /**
     * Method which provide the checking if need back button into {@link ActionBar}
     *
     * @return checking if need back button into {@link ActionBar}
     */
    @Override
    protected boolean isNeedBackButton() {
        return true;
    }

    //==============================================================================================
    //                                      CALLBACKS
    //==============================================================================================

    /**
     * Instance of the {@link OnKitViewCallback}
     */
    private final OnKitViewCallback viewCallback = new OnKitViewCallback() {
        /**
         * Method which provide the action when {@link BSView.Event} received
         *
         * @param context instance of {@link Context}
         * @param view    instance of the {@link BSView}
         * @param event   instance of the {@link BSView.Event}
         */
        @Override
        public void onCreateEventReceived(@NonNull Context context,
                                          @NonNull BSView view,
                                          @NonNull BSView.Event event) {

        }

        /**
         * Method which provide the action when user press on the channel object
         *
         * @param index  current index
         * @param object current object
         */
        @Override
        public void onItemClick(int index,
                                @NonNull KitBaseListObject object) {

        }

        /**
         * Method which provide the action when user doing the long press on item
         *
         * @param index  index
         * @param object object
         */
        @Override
        public void onItemLongClick(int index,
                                    @NonNull KitBaseListObject object) {

        }

        /**
         * Method which provide the action listening
         *
         * @param recycleEvent event
         * @param index        index
         * @param object       object
         */
        @Override
        public void onActionReceived(@NonNull RecycleEvent recycleEvent,
                                     int index,
                                     @NonNull KitBaseListObject object) {

        }

        /**
         * Method which provide the action when menu item was press
         *
         * @param object instance of the {@link KitMenuModel}
         */
        @Override
        public void onMenuItemClick(@NonNull KitMenuModel object) {
            if (object.getType().equalsIgnoreCase(KitWidgetType.MESSAGE_PHOTO.getValue())) {
                startActivityForPickImage();
            } else if (object.getType().equalsIgnoreCase(KitWidgetType.MESSAGE_LOCATION.getValue())) {
                final Location location = BSLocationManager.getInstance().getLocation();
                if (location != null) {
                    if(chatView != null){
                        chatView.sendMessage(location);
                    }
                }
            }
        }
    };

    /**
     * Method which provide the on activity results with the image content
     *
     * @param bitmap instance of the {@link Bitmap}
     */
    @Override
    protected void onActivityImageResults(@NonNull Bitmap bitmap) {
        if (chatView != null) {
            chatView.sendMessage(bitmap);
        }
    }
}
