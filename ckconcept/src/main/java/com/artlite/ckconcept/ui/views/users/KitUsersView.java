package com.artlite.ckconcept.ui.views.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.artlite.ckconcept.mvp.contracts.KitGroupContactContract;
import com.artlite.ckconcept.mvp.impl.users.KitDefaultNoGroupsUserPresenter;
import com.artlite.ckconcept.ui.abs.users.KitBaseGroupUserView;


/**
 * Created by dlernatovich on 10/20/2016.
 */

public class KitUsersView extends KitBaseGroupUserView {
    public KitUsersView(Context context) {
        super(context);
    }

    public KitUsersView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KitUsersView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the presenter creating
     *
     * @param view view for presenter
     * @return presenter
     */
    @Override
    protected KitGroupContactContract.Presenter onCreatePresenter(@NonNull KitGroupContactContract.ViewClass view) {
        return new KitDefaultNoGroupsUserPresenter(view);
    }
}
