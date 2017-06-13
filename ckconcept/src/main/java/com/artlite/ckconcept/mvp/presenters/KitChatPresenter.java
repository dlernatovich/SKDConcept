package com.artlite.ckconcept.mvp.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.callbacks.OnKitMessagesCallback;
import com.artlite.ckconcept.comparators.KitComparatorMessages;
import com.artlite.ckconcept.helpers.callback.KitCallbackHelper;
import com.artlite.ckconcept.helpers.message.KitMessageHelper;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.mvp.abs.presenter.KitBaseWidgetPresenter;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.artlite.ckconcept.ui.views.chat.KitChatView;
import com.magnet.mmx.client.api.ListResult;
import com.magnet.mmx.client.api.MMXChannel;
import com.magnet.mmx.client.api.MMXMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class which provide the presenter functional for the {@link KitChatView}
 */
public class KitChatPresenter extends KitBaseWidgetPresenter {

    /**
     * {@link Integer} constant of the {@link MMXMessage} limit
     */
    private static final int K_MESSAGE_LIMIT = 30;

    /**
     * Instance of the {@link MMXChannel}
     */
    private MMXChannel channel;

    /**
     * {@link List} of the {@link MMXMessage}
     */
    private Set<MMXMessage> messages = new HashSet<>();

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
     * Method which provide the setting of the {@link MMXChannel} id
     *
     * @param channelId {@link String} value of the {@link MMXChannel} Id
     * @param context   instance of {@link Context}
     * @param callback  instance of {@link OnKitActionCallback}
     */
    public void setChannelId(@Nullable final Context context,
                             @Nullable final String channelId,
                             @Nullable final OnKitActionCallback callback) {
        onReceiveChannel(context, channelId, callback);
    }

    /**
     * Method which provide the setting of the instance of the {@link MMXChannel}
     *
     * @param channel  instance of the {@link MMXChannel}
     * @param callback instance of {@link OnKitActionCallback}
     * @param context  instance of {@link Context}
     */
    public void setChannel(@Nullable final Context context,
                           @Nullable final MMXChannel channel,
                           @Nullable final OnKitActionCallback callback) {
        this.channel = channel;
        if (this.channel != null) {
            final String methodName = "void setChannelInactive()";
            this.channel.setActive(new MMXChannel.OnFinishedListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    BSLogHelper.log(KitChatPresenter.this, methodName, null, channel + " - active");
                    onReceiveMessages(context, channel, 0, callback);
                }

                @Override
                public void onFailure(MMXChannel.FailureCode failureCode, Throwable throwable) {
                    BSLogHelper.log(KitChatPresenter.this, methodName, null,
                            channel + " - active (error)");
                    onReceiveMessages(context, channel, 0, callback);
                }
            });
        }
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
        //FactoryManager.getCustomFactory()
        if (channel == null) {
            hideProgress();
        } else {
            onReceiveMessages(context, channel, offset, callback);
        }
    }

    /**
     * Method which provide the getting of the {@link List} of the {@link MMXMessage}
     *
     * @param context  instance of {@link Context}
     * @param channel  instance of the {@link MMXChannel}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     */
    private void onReceiveMessages(@NonNull final Context context,
                                   @NonNull final MMXChannel channel,
                                   final int offset,
                                   @Nullable final OnKitActionCallback callback) {
        if (BSValidationHelper.validateNull(context, channel)) {
            showProgress();
            channel.getMessages(null, null, K_MESSAGE_LIMIT, offset, false,
                    new MMXChannel.OnFinishedListener<ListResult<MMXMessage>>() {
                        @Override
                        public void onSuccess(ListResult<MMXMessage> result) {
                            onReceiveListItems(context,
                                    (result == null) ? new ArrayList<MMXMessage>() : result.items,
                                    offset,
                                    callback);
                        }

                        @Override
                        public void onFailure(MMXChannel.FailureCode failureCode,
                                              Throwable throwable) {
                            KitCallbackHelper.onError(callback,
                                    context, offset, throwable);
                            hideProgress();
                        }
                    });
        } else {
            KitCallbackHelper.onError(callback, context, offset, new Throwable("Channel is empty"));
        }
    }

    /**
     * Method which provide the receiving of the list items
     *
     * @param context  instance of {@link Context}
     * @param messages {@link List} of the {@link MMXMessage}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     */
    protected void onReceiveListItems(@NonNull final Context context,
                                      @NonNull final List<MMXMessage> messages,
                                      final int offset,
                                      @Nullable final OnKitActionCallback callback) {
        final List<KitBaseListObject> items = new ArrayList<>();
        BSThreadManager.execute(new BSThreadManager.OnExecutionCallback() {
            @Override
            public void onBackground() {
                final List<MMXMessage> listObjects = getMessages(offset, messages);
                for (MMXMessage message : listObjects) {
                    final KitBaseListObject object = getObject(message);
                    if (object != null) {
                        items.add(object);
                    }
                }
            }

            @Override
            public void onMain() {
                KitCallbackHelper.onSuccess(callback, context, offset, items, false);
                hideProgress();
            }
        });
    }

    /**
     * Method which provide the getting of the {@link List} of the {@link MMXMessage}
     *
     * @param offset   {@link Integer} value of the offset
     * @param messages {@link List} of the {@link MMXMessage}
     * @return {@link List} of the {@link MMXMessage}
     */
    protected List<MMXMessage> getMessages(int offset,
                                           @Nullable final List<MMXMessage> messages) {
        this.messages.addAll(messages);
        final List<MMXMessage> result = new ArrayList<>((offset == 0) ? this.messages : messages);
        Collections.sort(result, Collections.reverseOrder(new KitComparatorMessages()));
        return result;
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

    /**
     * Method which provide the setting the channel in active
     */
    public void setChannelInactive() {
        final String methodName = "void setChannelInactive()";
        if (channel != null) {
            channel.setInActive(new MMXChannel.OnFinishedListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    BSLogHelper.log(KitChatPresenter.this, methodName, null,
                            channel + " - inactive");
                }

                @Override
                public void onFailure(MMXChannel.FailureCode failureCode, Throwable throwable) {
                    BSLogHelper.log(KitChatPresenter.this, methodName, null,
                            channel + " - inactive (error)");
                }
            });
        }
    }

    //==============================================================================================
    //                                  GET DATA METHODS
    //==============================================================================================

    /**
     * Method which provide the recieving of the {@link MMXChannel} by it Id
     *
     * @param channelId {@link String} value of the {@link MMXChannel} Id
     */
    protected void onReceiveChannel(@Nullable final Context context,
                                    @Nullable final String channelId,
                                    @Nullable final OnKitActionCallback callback) {
        if (channelId != null) {
            showProgress();
            MMXChannel.getChannelById(channelId, new MMXChannel.OnFinishedListener<MMXChannel>() {
                @Override
                public void onSuccess(MMXChannel mmxChannel) {
                    setChannel(context, mmxChannel, callback);
                }

                @Override
                public void onFailure(MMXChannel.FailureCode failureCode,
                                      Throwable throwable) {
                    hideProgress();
                }
            });
        }
    }

    //==============================================================================================
    //                                     SEND MESSAGE
    //==============================================================================================

    /**
     * Method which provide the text message sending
     *
     * @param message {@link String} value of the message
     */
    public void sendMessage(@Nullable String message) {
        KitMessageHelper.sendMessage(channel, message);
    }

    /**
     * Method which provide the text message sending
     *
     * @param bitmap {@link String} value of the message
     */
    public void sendMessage(@Nullable Bitmap bitmap) {
        KitMessageHelper.sendMessage(channel, bitmap);
    }

    /**
     * Method which provide the sending of the text message
     *
     * @param location {@link String} value of the message
     */
    public void sendMessage(@Nullable final Location location) {
        KitMessageHelper.sendMessage(channel, location);
    }

    //==============================================================================================
    //                                      CALLBACKS
    //==============================================================================================

    /**
     * Instance of the {@link OnKitMessagesCallback}
     */
    private final OnKitMessagesCallback messagesCallback =
            new OnKitMessagesCallback() {
                @Override
                public boolean onMessageReceived(MMXMessage message) {
                    final KitWidgetContract.View view = getView();
                    if (BSValidationHelper.validateNull(channel, message, view)) {
                        final String channelId = channel.getId();
                        final String otherChannelId = message.getChannel().getId();
                        if (BSValidationHelper.validateEmpty(channelId, otherChannelId)) {
                            if (channelId.equalsIgnoreCase(otherChannelId)) {
                                messages.add(message);
                                view.reloadData();
                            }
                        }
                    }
                    return false;
                }
            };


}
