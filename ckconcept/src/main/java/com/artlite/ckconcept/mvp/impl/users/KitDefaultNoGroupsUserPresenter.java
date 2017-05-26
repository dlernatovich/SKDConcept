package com.artlite.ckconcept.mvp.impl.users;

import android.support.annotation.NonNull;

import com.artlite.ckconcept.mvp.contracts.KitGroupContactContract;

/**
 * Created by dlernatovich on 10/20/2016.
 */

public class KitDefaultNoGroupsUserPresenter extends KitDefaultGroupUserPresenter {

    /**
     * Default constructor
     *
     * @param view view
     */
    public KitDefaultNoGroupsUserPresenter(@NonNull KitGroupContactContract.View view) {
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
