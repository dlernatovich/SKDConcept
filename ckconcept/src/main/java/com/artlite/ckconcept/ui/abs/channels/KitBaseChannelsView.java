package com.artlite.ckconcept.ui.abs.channels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredBaseCallback;
import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredRefreshCallback;
import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.callbacks.OnKitMessagesCallback;
import com.artlite.ckconcept.managers.channel.KitChannelsCacheManager;
import com.artlite.ckconcept.mvp.contracts.KitChannelsContract;
import com.artlite.ckconcept.ui.recycle.channels.KitRecycleChannel;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;
import com.magnet.mmx.client.api.MMXMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dlernatovich on 11/17/2016.
 */

public abstract class KitBaseChannelsView extends BSView {

    /**
     * {@link List} of the {@link ChannelDetail}
     */
    protected final List<ChannelDetail> channels = new ArrayList<>();

    /**
     * Instance of the {@link AdapteredView}
     */
    protected AdapteredView recycleView;

    /**
     * Instance of the {@link KitChannelsContract.OnChannelClickListener}
     */
    protected KitChannelsContract.OnChannelClickListener onChannelClickListener;

    /**
     * {@link Boolean} value of the updating progress
     */
    protected boolean isUpdatingProgress = false;

    /**
     * Instance of the {@link OnChannelsViewListener}
     */
    protected OnChannelsViewListener channelsViewListener;

    /**
     * Constructor with context
     *
     * @param context context instance
     */
    public KitBaseChannelsView(Context context) {
        super(context);
    }

    /**
     * Constructor with context and attributes
     *
     * @param context context instance
     * @param attrs   attributes instance
     */
    public KitBaseChannelsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor with context, attributes and styles
     *
     * @param context      context instance
     * @param attrs        attributes instance
     * @param defStyleAttr styles instance
     */
    public KitBaseChannelsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide to getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_channels;
    }

    /**
     * Method which provide the interface linking
     */
    protected void onLinkInterface() {
        recycleView = (AdapteredView) findViewById(R.id.recyclerView);
    }

    /**
     * Method which provide the action when view will create
     */
    @Override
    protected void onCreateView() {
        onLinkInterface();
        if (view != null) {
            view.setChannels(getCacheManager().getChannels(),
                    true);
        }
        if (presenter != null) {
            recycleView.init(presenter.getLayoutManager(getContext()),
                    recycleCallback, refreshCallback);
            recycleView.setIsNeedResfresh(true);
            recycleView.setRefreshColoursRes(presenter.getRefreshColorBackground(),
                    presenter.getRefreshColor());
        }
    }

    /**
     * Method which provide the checking if {@link MMXChannel} is exists in list
     *
     * @param channel instance of the {@link MMXChannel}
     * @return checking results
     */
    protected boolean isChannelExists(@Nullable final MMXChannel channel) {
        if ((channel == null) || (channel.getIdentifier() == null)) {
            return false;
        }
        final String channelID = channel.getIdentifier();
        synchronized (channels) {
            for (final ChannelDetail detail : channels) {
                final MMXChannel innerChannel = detail.getChannel();
                if (innerChannel != null) {
                    if (TextUtils.equals(channelID, innerChannel.getIdentifier()) == true) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method which provide the updating of the channels list
     */
    public void updateChannelsList() {
        final boolean showRefresh = recycleView.getListItems().isEmpty();
        if (presenter != null) {
            presenter.receiveChannels(showRefresh);
        }
    }

    /**
     * Method which provide update channels list from cache
     *
     * @see KitChannelsCacheManager
     */
    public void updateChannelsListFromCache() {
        BSThreadManager.background(new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                if (view != null) {
                    view.setChannels(getCacheManager().getChannels());
                }
            }
        });
    }

    /**
     * Method which provide the resuming of the channels
     */
    public void onResumeView() {
        updateChannelsListFromCache();
    }

    /**
     * Method which provide the action when {@link android.view.View} is destroying
     */
    public void onDestroyView() {
        if (eventListener != null) {
            eventListener.unregister();
        }
    }

    /**
     * Method which provide the getting of the presenter
     *
     * @param view current view
     * @return presenter
     */
    @NonNull
    protected abstract KitChannelsContract.Presenter createPresenter(@NonNull final KitChannelsContract.View view);

    /**
     * Method which provide the getting of the channel cache
     *
     * @return channel cache class
     * @see KitChannelsCacheManager
     */
    @NonNull
    protected final KitChannelsCacheManager getCacheManager() {
        return KitChannelsCacheManager.getInstance();
    }

    /**
     * Method which provide the setting of the channel click listener
     *
     * @param onChannelClickListener channel click listener
     */
    public void setOnChannelClickListener(@Nullable final KitChannelsContract.OnChannelClickListener
                                                  onChannelClickListener) {
        this.onChannelClickListener = onChannelClickListener;
    }

    //==============================================================================================
    //                                          MVP
    //==============================================================================================

    /**
     * View class
     *
     * @see KitChannelsContract.ViewClass
     */
    private final KitChannelsContract.ViewClass view = new KitChannelsContract.ViewClass() {

        /**
         * Method which provide the channels setting to the
         * {@link android.support.v7.widget.RecyclerView}
         *
         * @param channels    channels to set
         * @param isFirstInit check if the first initialization
         */
        @Override
        public void setChannels(@Nullable final List<ChannelDetail> channels,
                                final boolean isFirstInit) {
            if (isFirstInit == false) {
                getCacheManager().setChannels(channels);
            } else {
                if (presenter != null) {
                    int delay = 0;
                    final boolean hasElements = (channels.size() > 0);
                    if (hasElements) {
                        delay = 3;
                    }
                    presenter.receiveChannels(!hasElements, delay);
                }
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    setChannels(channels);
                    BSThreadManager.main(new BSThreadManager.OnThreadCallback() {
                        @Override
                        public void onExecute() {
                            if ((channelsViewListener != null)
                                    && (channels != null) &&
                                    (isFirstInit == false)) {
                                channelsViewListener.onSetChannels(channels.size());
                            }
                        }
                    });
                }
            }).start();
        }

        /**
         * Method which provide the setting channels
         *
         * @param channels channels to set
         */
        @Override
        public void setChannels(@Nullable List<ChannelDetail> channels) {
            //Set channels to view
            setViewChannels(channels);
            //Sort channels
            Collections.sort(channels,
                    Collections.reverseOrder(new KitChannelsContract.ChannelsDateComparator()));
            //Set channels
            final List<KitRecycleChannel> items = new ArrayList<KitRecycleChannel>();
            for (final ChannelDetail detail : channels) {
                if (presenter != null) {
                    items.add(new KitRecycleChannel(presenter.getChannelLayout(),
                            presenter.getPhotoText(),
                            presenter.getLocationText(),
                            presenter.getNoMessageText(),
                            presenter.getPoolText(),
                            presenter.getAnswerText(),
                            presenter.getChecklistText(),
                            presenter.getMessageImage(),
                            presenter.getLocationImage(),
                            presenter.getPhotoImage(),
                            presenter.getPoolImage(),
                            presenter.getVoteImage(),
                            presenter.getChecklistImage(),
                            detail));
                }
            }
            if (recycleView != null) {
                recycleView.set(items);
            }
        }

        /**
         * Method which provide the progress showing
         */
        @Override
        public void showProgress() {
            if (recycleView != null) {
                recycleView.showRefresh();
            }
        }

        /**
         * Method which provide the hide progress
         */
        @Override
        public void hideProgress() {
            if (recycleView != null) {
                recycleView.hideRefresh();
            }
        }

        /**
         * Method which provide the getting of the current context
         *
         * @return current {@link Context}
         */
        @NonNull
        @Override
        public Context getCurrentContext() {
            return getContext();
        }
    };

    /**
     * Presenter
     *
     * @see KitChannelsContract.Presenter
     */
    protected final KitChannelsContract.Presenter presenter = createPresenter(view);

    //==============================================================================================
    //                                    ADAPTERED CALLBACKS
    //==============================================================================================

    /**
     * Callback which provide the clicking channel management
     */
    private final OnAdapteredBaseCallback recycleCallback = new OnAdapteredBaseCallback<KitRecycleChannel>() {
        @Override
        public void onItemClick(int i, @NonNull KitRecycleChannel baseObject) {
            final ChannelDetail channelDetail = baseObject.getChannel();
            if ((onChannelClickListener != null) && (channelDetail != null)) {
                onChannelClickListener.onChannelClick(channelDetail);
            }
        }

        @Override
        public void onItemLongClick(int i, @NonNull KitRecycleChannel recycleChannel) {
            final ChannelDetail channelDetail = recycleChannel.getChannel();
            if ((onChannelClickListener != null) && (channelDetail != null)) {
                onChannelClickListener.onChannelLongClick(channelDetail);
            }
        }

        @Override
        public void onActionReceived(@NonNull RecycleEvent recycleEvent,
                                     int i, @NonNull KitRecycleChannel baseObject) {
        }
    };

    /**
     * Callback which provide the swipe down to refresh management
     */
    private final OnAdapteredRefreshCallback refreshCallback = new OnAdapteredRefreshCallback() {
        @Override
        public void onRefreshData() {
            if (presenter != null) {
                presenter.receiveChannels(false);
            }
        }
    };

    /**
     * MMX event listener
     */
    private final OnKitMessagesCallback eventListener = new OnKitMessagesCallback() {
        @Override
        public boolean onMessageReceived(MMXMessage message) {
            if (isUpdatingProgress == false) {
                isUpdatingProgress = true;
                synchronized (message) {
                    if ((message != null) &&
                            (message.getChannel() != null)) {
                        if (isChannelExists(message.getChannel())) {
                            if (presenter != null) {
                                presenter.receiveChannels(false);
                            }
                        }
                    }
                }
            }
            return true;
        }
    };

    //==============================================================================================
    //                                    GETTERS AND SETTERS
    //==============================================================================================

    /**
     * Method which provide the getting of the list of the {@link ChannelDetail} as a copy
     *
     * @return list of {@link ChannelDetail}
     */
    @NonNull
    public List<ChannelDetail> getChannels() {
        synchronized (this.channels) {
            return new ArrayList<>(channels);
        }
    }

    /**
     * Method which provide the channels setting
     *
     * @param channels channels for set
     */
    public void setViewChannels(@Nullable final List<ChannelDetail> channels) {
        isUpdatingProgress = false;
        synchronized (this.channels) {
            if (channels != null) {
                this.channels.clear();
                this.channels.addAll(channels);
            }
        }
    }

    /**
     * Method which provide the setting of channels view listener
     *
     * @param channelsViewListener instance of the {@link OnChannelsViewListener}
     */
    public void setChannelsViewListener(@Nullable final OnChannelsViewListener channelsViewListener) {
        this.channelsViewListener = channelsViewListener;
    }

    //==============================================================================================
    //                                    CALLBACKS
    //==============================================================================================

    /**
     * Channels view listener
     */
    public interface OnChannelsViewListener {
        /**
         * Method which provide the listening when channels will be getting
         * and setting as objects in list
         *
         * @param count channels count
         */
        void onSetChannels(int count);

    }

}
