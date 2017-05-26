package com.artlite.ckconcept.ui.views.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.artlite.ckconcept.mvp.contracts.KitGroupContactContract;
import com.artlite.ckconcept.mvp.impl.users.KitDefaultGroupUserPresenter;
import com.artlite.ckconcept.ui.abs.users.KitBaseGroupUserView;


/**
 * Created by dlernatovich on 10/7/16.
 */

public class KitGroupView extends KitBaseGroupUserView {

    public KitGroupView(Context context) {
        super(context);
    }

    public KitGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KitGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected KitGroupContactContract.Presenter onCreatePresenter(@NonNull KitGroupContactContract.ViewClass view) {
        return new KitDefaultGroupUserPresenter(view);
    }

}
