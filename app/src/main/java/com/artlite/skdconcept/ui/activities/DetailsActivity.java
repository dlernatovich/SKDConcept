package com.artlite.skdconcept.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.managers.BSTransferManager;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.ckconcept.ui.views.chat.KitChatView;
import com.artlite.skdconcept.R;
import com.magnet.mmx.client.api.ChannelDetail;

public class DetailsActivity extends BSActivity {

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
