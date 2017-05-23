package com.artlite.ckconcept.mvp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.comparators.KitComparatorChannels;
import com.artlite.ckconcept.constants.KitChannelFilter;
import com.artlite.ckconcept.helpers.KitCallbackHelper;
import com.artlite.ckconcept.helpers.KitChannelHelper;
import com.artlite.ckconcept.mvp.abs.presenter.KitBaseWidgetPresenter;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.artlite.ckconcept.ui.views.channels.KitChannelsView;
import com.magnet.max.android.callbacks.MaxCoreActionCallback;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.ChannelDetailOptions;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dlernatovich on 5/15/2017.
 */

public final class KitChannelPresenter extends KitBaseWidgetPresenter {

    /**
     * Constructor which provide to create the {@link KitBaseWidgetPresenter} from
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
        if (offset > 0) {
            return;
        }
        getSubscription(context, offset, callback);
    }

    /**
     * Methd which provide the getting of the {@link View} class
     *
     * @return instance of the {@link View} class
     */
    @NonNull
    @Override
    public Class getViewClass() {
        return KitChannelsView.class;
    }

    /**
     * Method which provide the getting of the user subscription
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     */
    private void getSubscription(@NonNull final Context context,
                                 final int offset,
                                 @Nullable final OnKitActionCallback callback) {
        MMXChannel.getAllSubscriptions(new MMXChannel.OnFinishedListener<List<MMXChannel>>() {
            @Override
            public void onSuccess(List<MMXChannel> result) {
                final List<MMXChannel> channels = (result == null)
                        ? new ArrayList<MMXChannel>() : result;
                getMessageUnreadCount(context, offset, callback, channels);
            }

            @Override
            public void onFailure(MMXChannel.FailureCode code, Throwable throwable) {
                KitCallbackHelper.onError(callback, context, offset, throwable);
            }
        });
    }

    /**
     * Method which provide the getting of the {@link MMXChannel} unread count
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     * @param channels {@link List} of the {@link ChannelDetail}
     */
    private void getMessageUnreadCount(@NonNull final Context context,
                                       final int offset,
                                       @Nullable final OnKitActionCallback callback,
                                       @NonNull final List<MMXChannel> channels) {
        MMXChannel.getUnreadCount(channels, new MaxCoreActionCallback<List<MMXChannel>>() {
            @Override
            public void onResult(boolean isSuccess, Throwable error, List<MMXChannel> result) {
                if (isSuccess) {
                    getChannelsDetail(context, offset, callback, channels);
                } else {
                    KitCallbackHelper.onError(callback, context, offset, error);
                }
            }
        });
    }

    /**
     * Method which provide the getting of the user subscription
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     */
    private void getChannelsDetail(@NonNull final Context context,
                                   final int offset,
                                   @Nullable final OnKitActionCallback callback,
                                   @NonNull List<MMXChannel> channels) {
        MMXChannel.getChannelDetail(channels, getChannelDetailOptions(),
                new MMXChannel.OnFinishedListener<List<ChannelDetail>>() {
                    @Override
                    public void onSuccess(List<ChannelDetail> result) {
                        final List<ChannelDetail> details = (result == null)
                                ? new ArrayList<ChannelDetail>() : result;
                        processChannels(context, offset, callback, details);
                    }

                    @Override
                    public void onFailure(MMXChannel.FailureCode code, Throwable throwable) {
                        KitCallbackHelper.onError(callback, context, offset, throwable);
                    }
                });
    }

    /**
     * Method which provide the channels processing (sorting and filtering)
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     */
    private void processChannels(@NonNull final Context context,
                                 final int offset,
                                 @Nullable final OnKitActionCallback callback,
                                 @NonNull final List<ChannelDetail> channels) {
        final List<ChannelDetail> result = new ArrayList<>();
        BSThreadManager.execute(new BSThreadManager.OnExecutionCallback() {
            @Override
            public void onBackground() {
                //Filtering channels
                for (ChannelDetail detail : channels) {
                    if (!KitChannelHelper.isContainKeyword(detail, KitChannelFilter.APPROVAL)) {
                        result.add(detail);
                    }
                }

                //Sort channels
                Collections.sort(result,
                        Collections.reverseOrder(new KitComparatorChannels()));
            }

            @Override
            public void onMain() {
                getListObjects(context, offset, callback, result);
            }
        });

    }

    /**
     * Method which provide the getting of the {@link List} of the {@link BaseObject} for displaying
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     * @param channels {@link List} of the {@link ChannelDetail}
     */
    private void getListObjects(@NonNull final Context context,
                                final int offset,
                                @Nullable final OnKitActionCallback callback,
                                @NonNull final List<ChannelDetail> channels) {
        final List<BaseObject> objects = new ArrayList<>();
        BSThreadManager.execute(new BSThreadManager.OnExecutionCallback() {
            @Override
            public void onBackground() {
                for (ChannelDetail channel : channels) {
                    final BaseObject object = getObject(getType(channel), channel);
                    if (object != null) {
                        objects.add(object);
                    }
                }
            }

            @Override
            public void onMain() {
                KitCallbackHelper.onSuccess(callback, context, offset, objects);
            }
        });
    }

    /**
     * Method which provide the getting of the {@link ChannelDetailOptions}
     *
     * @return instance of the {@link ChannelDetailOptions}
     */
    private ChannelDetailOptions getChannelDetailOptions() {
        return new ChannelDetailOptions.Builder()
                .numOfMessages(1)
                .numOfSubcribers(Integer.MAX_VALUE)
                .build();
    }
}
