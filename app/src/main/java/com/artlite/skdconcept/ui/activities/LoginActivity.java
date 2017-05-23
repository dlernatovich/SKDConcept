package com.artlite.skdconcept.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.ckconcept.callbacks.OnKitLoginCallback;
import com.artlite.ckconcept.ui.views.login.KitLoginView;
import com.artlite.skdconcept.R;
import com.magnet.max.android.User;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public final class LoginActivity extends BSActivity implements OnKitLoginCallback {

    /**
     * Instance of the {@link KitLoginView}
     */
    @FindViewBy(id = R.id.view_login)
    private KitLoginView loginView;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * Method which provide the action when Activity is created
     *
     * @param bundle
     */
    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        setTitle("Login");
        loginView.setOnKitLoginCallback(this);
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
     * Method which provide the functional when login is successed
     *
     * @param user instance of {@link User}
     */
    @Override
    public void onLoginSuccess(@Nullable User user) {
        startActivity(MainActivity.class);
    }

    /**
     * Method which provide the action when login error
     *
     * @param throwable instance of {@link Throwable}
     */
    @Override
    public void onLoginError(@NonNull Throwable throwable) {
        loginView.clear();
    }
}
