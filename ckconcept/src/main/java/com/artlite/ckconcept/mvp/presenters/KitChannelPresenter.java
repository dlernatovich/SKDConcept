package com.artlite.ckconcept.mvp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlernatovich on 5/15/2017.
 */

public final class KitChannelPresenter extends KitWidgetBasePresenter {

    /**
     * Constructor which provide to create the {@link KitWidgetBasePresenter} from
     *
     * @param view instance of {@link KitWidgetContract.View}
     */
    public KitChannelPresenter(@NonNull KitWidgetContract.View view) {
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
        final List<BaseObject> objects = new ArrayList<>();
        MMXChannel.getAllSubscriptions(new MMXChannel.OnFinishedListener<List<MMXChannel>>() {
            @Override
            public void onSuccess(List<MMXChannel> result) {
                for (MMXChannel channel : result) {
                    final BaseObject object = getObject(getType(channel), channel);
                    if (object != null) {
                        objects.add(object);
                    }
                }
                if (callback != null) {
                    callback.onDataReceived(context, offset, objects);
                }
            }

            @Override
            public void onFailure(MMXChannel.FailureCode code, Throwable throwable) {
                if (callback != null) {
                    callback.onDataReceived(context, offset, objects);
                }
            }
        });
    }

    /**
     * Method which provide the getting of the class type from the {@link Object}
     *
     * @param object instance of {@link Object}
     * @return {@link String} value of the type
     */
    @Nullable
    @Override
    public String getType(@NonNull Object object) {
        if (object instanceof MMXChannel) {
            return KitWidgetType.CHANNEL.getValue();
        }
        return null;
    }
}
