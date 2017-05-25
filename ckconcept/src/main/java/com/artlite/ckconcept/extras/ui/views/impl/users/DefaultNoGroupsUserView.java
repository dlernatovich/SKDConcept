package com.artlite.ckconcept.extras.ui.views.impl.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;
import com.artlite.ckconcept.extras.mvp.impl.users.DefaultNoGroupsUserPresenter;
import com.artlite.ckconcept.extras.ui.views.abs.users.AbsGroupUserView;


/**
 * Created by dlernatovich on 10/20/2016.
 */

public class DefaultNoGroupsUserView extends AbsGroupUserView {
    public DefaultNoGroupsUserView(Context context) {
        super(context);
    }

    public DefaultNoGroupsUserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultNoGroupsUserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the presenter creating
     *
     * @param view view for presenter
     * @return presenter
     */
    @Override
    protected GroupContactContract.Presenter onCreatePresenter(@NonNull GroupContactContract.ViewClass view) {
        return new DefaultNoGroupsUserPresenter(view);
    }
}
