package com.artlite.skdconcept.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.ckconcept.callbacks.OnKitChannelsCallback;
import com.artlite.ckconcept.ui.views.channels.KitChannelsView;
import com.artlite.skdconcept.R;
import com.magnet.mmx.client.api.ChannelDetail;

public class MainActivity extends BSActivity implements OnKitChannelsCallback {

    /**
     * Instance of the {@link KitChannelsView}
     */
    @FindViewBy(id = R.id.view_channels)
    private KitChannelsView channelsView;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * Method which provide the returning of the menu Id
     *
     * @return {@link Integer} value of the menu Id
     */
    @Override
    protected int getMenuId() {
        return R.menu.menu_main_activity;
    }

    /**
     * Method which provide the action when user press of the menu item
     *
     * @param item instance of the {@link MenuItem}
     * @return pressing result
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_create_channel) {
            startActivity(CreateChannelActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method which provide the action when Activity is created
     *
     * @param bundle instance of {@link Bundle}
     */
    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        setTitle("Conversations");
        channelsView.setChannelsCallback(this);
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


    /**
     * Method which provide the functional when user press of the {@link ChannelDetail}
     *
     * @param index   {@link Integer} value of the {@link ChannelDetail} index
     * @param channel instance of the {@link ChannelDetail}
     */
    @Override
    public void onChannelClick(int index, @NonNull ChannelDetail channel) {
        startActivity(DetailsActivity.class);
    }

    /**
     * Method which provide the functional when user do the long press of the {@link ChannelDetail}
     *
     * @param index   {@link Integer} value of the {@link ChannelDetail} index
     * @param channel instance of the {@link ChannelDetail}
     */
    @Override
    public void onChannelLongClick(int index, @NonNull ChannelDetail channel) {

    }

    /**
     * Method which provide the the functional when user sent the action
     * for the {@link KitChannelsView}
     *
     * @param recycleEvent instance of the {@link RecycleEvent}
     * @param index        {@link Integer} value of the {@link ChannelDetail} index
     * @param channel      instance of the {@link ChannelDetail}
     */
    @Override
    public void onActionReceived(@NonNull RecycleEvent recycleEvent, int index, @NonNull ChannelDetail channel) {

    }
}
