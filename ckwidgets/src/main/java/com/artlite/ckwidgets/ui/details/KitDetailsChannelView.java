package com.artlite.ckwidgets.ui.details;

import android.content.Context;

import com.artlite.bslibrary.ui.view.BSView;
import com.magnet.mmx.client.api.MMXChannel;

/**
 * Created by dlernatovich on 5/16/2017.
 */

public class KitDetailsChannelView extends BSView {

    private final Object object;
    private int layoutId;

    public KitDetailsChannelView(Context context, Object object, Integer layoutId) {
        super(context);
        this.object = object;
        this.layoutId = layoutId;
    }

    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void onCreateView() {
        if (object != null) {
            if (object instanceof MMXChannel) {

            }
        }
    }
}
