package com.artlite.skdconcept;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.callbacks.OnKitViewCallback;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.ui.views.channels.KitChannelsView;

public class MainActivity extends BSActivity {

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
            channelsView.showCreateList();
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
        setTitle("Channels");
//        channelsView.setOnViewCallback(new OnKitViewCallback() {
//            @Override
//            public void onCreateEventReceived(@NonNull Context context,
//                                              @NonNull BSView view,
//                                              @NonNull BSView.Event event) {
//
//            }
//
//            @Override
//            public void onItemClick(int index, @NonNull BaseObject object) {
//                startActivity(DetailsActivity.class);
//            }
//
//            @Override
//            public void onItemLongClick(int index, @NonNull BaseObject object) {
//
//            }
//
//            @Override
//            public void onActionReceived(@NonNull RecycleEvent recycleEvent,
//                                         int index,
//                                         @NonNull BaseObject object) {
//
//            }
//
//            @Override
//            public void onMenuItemClick(@NonNull KitMenuModel object) {
//
//            }
//        });
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


}
