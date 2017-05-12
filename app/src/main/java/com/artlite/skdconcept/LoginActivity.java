package com.artlite.skdconcept;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.bslibrary.ui.fonted.BSEditText;
import com.magnet.max.android.ApiCallback;
import com.magnet.max.android.ApiError;
import com.magnet.max.android.User;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public final class LoginActivity extends BSActivity {

    @FindViewBy(id = R.id.edit_username)
    private BSEditText editUserName;

    @FindViewBy(id = R.id.edit_password)
    private BSEditText editPassword;

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
        setOnClickListeners(R.id.button_login);
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
     * Method which provide the on click functional
     *
     * @param v instance of {@link View}
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login: {
                final DialogPleaseWait dialogPleaseWait = new DialogPleaseWait(this);
                dialogPleaseWait.showAsDialog(false);
                User.login(editUserName.getStringValue(),
                        editPassword.getStringValue(),
                        false,
                        new ApiCallback<Boolean>() {
                            @Override
                            public void success(Boolean aBoolean) {
                                dialogPleaseWait.dismiss();
                                startActivity(MainActivity.class);
                            }

                            @Override
                            public void failure(ApiError error) {
                                dialogPleaseWait.dismiss();
                            }
                        });
                break;
            }
            default: {
                break;
            }
        }
    }
}
