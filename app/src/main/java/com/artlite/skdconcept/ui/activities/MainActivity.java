package com.artlite.skdconcept.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.managers.BSTransferManager;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.ckconcept.mvp.contracts.KitChannelsContract;
import com.artlite.ckconcept.ui.views.channels.KitChannelsView;
import com.artlite.skdconcept.R;
import com.magnet.mmx.client.api.ChannelDetail;

public class MainActivity extends BSActivity implements KitChannelsContract.OnChannelClickListener {

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
        channelsView.setOnChannelClickListener(this);
    }

    /**
     * Method which provide the action when {@link android.app.Activity} is resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        channelsView.onResumeView();
    }

    /**
     * Method which provide the action when {@link android.app.Activity} is destroy
     */
    @Override
    protected void onDestroy() {
        channelsView.onDestroyView();
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

    /**
     * Method which provide the action when user click on channel
     *
     * @param channel channel
     */
    @Override
    public void onChannelClick(@NonNull ChannelDetail channel) {
        BSTransferManager.put(DetailsActivity.class, channel);
        startActivity(DetailsActivity.class);
    }

    /**
     * Method which provide the action when user do a long click on channel
     *
     * @param channel channel object
     */
    @Override
    public void onChannelLongClick(@NonNull ChannelDetail channel) {

    }
}
