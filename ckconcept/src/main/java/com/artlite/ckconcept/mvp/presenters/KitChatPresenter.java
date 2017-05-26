package com.artlite.ckconcept.mvp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.mvp.abs.presenter.KitBaseWidgetPresenter;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.artlite.ckconcept.ui.views.chat.KitChatView;

/**
 * Created by Slava on 5/26/17.
 */

public class KitChatPresenter extends KitBaseWidgetPresenter {

    /**
     * Constructor which provide to create the {@link KitBaseWidgetPresenter} from
     * instance of the {@link KitWidgetContract.View}
     *
     * @param view instance of {@link KitWidgetContract.View}
     */
    public KitChatPresenter(@NonNull KitWidgetContract.View view) {
        super(view);
    }

    /**
     * Method which provide the getting of the server data
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     * @return
     */
    @Nullable
    @Override
    public void getServerData(@NonNull final Context context,
                              final int offset,
                              @Nullable final OnKitActionCallback callback) {
        if (offset > 0) {
            return;
        }

        // TODO: Implement this later
    }

    /**
     * Methd which provide the getting of the {@link View} class
     *
     * @return instance of the {@link View} class
     */
    @NonNull
    @Override
    public Class getViewClass() {
        return KitChatView.class;
    }
}
