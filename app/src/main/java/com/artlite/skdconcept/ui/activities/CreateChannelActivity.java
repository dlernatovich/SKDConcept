package com.artlite.skdconcept.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.skdconcept.R;

/**
 * Created by dlernatovich on 23.05.2017.
 */

public final class CreateChannelActivity extends BSActivity {
    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_channel;
    }

    /**
     * Method which provide the action when Activity is created
     *
     * @param bundle instance of {@link Bundle}
     */
    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        setTitle("Create conversation");
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
