package com.artlite.ckconcept.ui.abs;

import android.content.Context;

import com.artlite.bslibrary.ui.view.BSView;

/**
 * Created by dlernatovich on 5/17/2017.
 */

public class KitBaseCreateView extends BSView {

    /**
     * Constructor which provide the
     * @param context
     */
    public KitBaseCreateView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreateView() {

    }
}
