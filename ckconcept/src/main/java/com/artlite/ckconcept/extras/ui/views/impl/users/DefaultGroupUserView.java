package com.artlite.ckconcept.extras.ui.views.impl.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;
import com.artlite.ckconcept.extras.mvp.impl.users.DefaultGroupUserPresenter;
import com.artlite.ckconcept.extras.ui.views.abs.users.AbsGroupUserView;


/**
 * Created by dlernatovich on 10/7/16.
 */

public class DefaultGroupUserView extends AbsGroupUserView {

    public DefaultGroupUserView(Context context) {
        super(context);
    }

    public DefaultGroupUserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultGroupUserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected GroupContactContract.Presenter onCreatePresenter(@NonNull GroupContactContract.ViewClass view) {
        return new DefaultGroupUserPresenter(view);
    }

}
