package com.artlite.ckconcept.extras.mvp.impl.users;

import android.support.annotation.NonNull;

import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;

/**
 * Created by dlernatovich on 10/20/2016.
 */

public class DefaultNoGroupsUserPresenter extends DefaultGroupUserPresenter {

    /**
     * Default constructor
     *
     * @param view view
     */
    public DefaultNoGroupsUserPresenter(@NonNull GroupContactContract.View view) {
        super(view);
    }

    /**
     * Method which provide the defining of the needing in the user groups
     *
     * @return is need groups
     */
    @Override
    public boolean isNeedGroups() {
        return false;
    }
}
