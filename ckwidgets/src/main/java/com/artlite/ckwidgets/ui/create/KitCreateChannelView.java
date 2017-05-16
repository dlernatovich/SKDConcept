package com.artlite.ckwidgets.ui.create;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.ui.views.KitChannelsView;
import com.artlite.ckwidgets.R;

/**
 * Created by dlernatovich on 5/16/2017.
 */

public final class KitCreateChannelView extends BSView {

    @LayoutRes
    final int layoutId;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public KitCreateChannelView(Context context, final int layoutId) {
        super(context);
        this.layoutId = layoutId;
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return this.layoutId;
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        setOnClickListeners(R.id.button_create);
    }

    /**
     * Method which provide the click interface
     *
     * @param view instance of {@link View}
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_create) {
            sendEvent(KitChannelsView.K_EVENT_CREATE_CHANNEL);
            dismiss();
        }
    }
}
