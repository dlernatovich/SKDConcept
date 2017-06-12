package com.artlite.ckconcept.mvp.impl.channels;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.helpers.channel.KitChannelDetailsHelper;
import com.artlite.ckconcept.mvp.contracts.KitChannelsContract;
import com.magnet.max.android.callbacks.MaxCoreActionCallback;
import com.magnet.max.android.rest.options.CacheOptions;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dlernatovich on 11/17/2016.
 */

public abstract class KitBaseChannelsPresenter implements KitChannelsContract.Presenter {

    /**
     * Instance of the {@link KitChannelsContract.View}
     */
    private final KitChannelsContract.View view;

    /**
     * Default constructor
     *
     * @param view view
     */
    public KitBaseChannelsPresenter(KitChannelsContract.View view) {
        this.view = view;
    }

    /**
     * Method which provide the channels receiving
     *
     * @param showRefresh is need to show refresh spinner
     */
    @Override
    public void receiveChannels(final boolean showRefresh) {
        receiveChannels(showRefresh, 0);
    }

    /**
     * Method which provide the channels receiving with delay
     *
     * @param delay       delay time
     * @param showRefresh is need to show refresh spinner
     */
    @Override
    public void receiveChannels(final boolean showRefresh, final int delay) {
        final String methodName = "receiveChannels()";
        if (showRefresh == true) {
            switchProgress(true);
        }
        final List<MMXChannel> channels = new ArrayList<>();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                KitChannelDetailsHelper.getAllChannels(getCacheOptions(),
                        new MaxCoreActionCallback<List<ChannelDetail>>() {
                            @Override
                            public void onResult(boolean isSuccess,
                                                 @Nullable Throwable error,
                                                 @Nullable List<ChannelDetail> result) {
                                switchProgress(false);
                                setChannels(filterChannel(result));
                            }
                        });
            }
        }, delay * 1000);
    }

    /**
     * Method which provide the progress switching
     *
     * @param isShow is need show
     */
    @Override
    public void switchProgress(final boolean isShow) {
        final KitChannelsContract.View currentView = view;
        if (currentView != null) {
            if (isShow == true) {
                currentView.showProgress();
            } else {
                currentView.hideProgress();
            }
        }
    }

    /**
     * Method which provide the channels setting
     *
     * @param channels channels details
     */
    @Override
    public void setChannels(@NonNull List<ChannelDetail> channels) {
        final KitChannelsContract.View currentView = view;
        if (currentView != null) {
            currentView.setChannels(channels, false);
        }
    }

    /**
     * Method which provide the channel filtering
     *
     * @param channels channels list
     * @return filtered channels list
     */
    @NonNull
    @Override
    public List<ChannelDetail> filterChannel(@NonNull List<ChannelDetail> channels) {
        final List<ChannelDetail> filteredChannels = new ArrayList<>();
        for (final ChannelDetail detail : channels) {
            if (KitChannelDetailsHelper.isContainKeyword(detail,
                    getChannelFilter()) == false) {
                filteredChannels.add(detail);
            }
        }
        return filteredChannels;
    }

    /**
     * Method which provide the getting of {@link CacheOptions}
     *
     * @return instance of {@link CacheOptions}
     */
    @NonNull
    @Override
    public CacheOptions getCacheOptions() {
        return new CacheOptions
                .Builder()
                .alwaysUseCacheIfOffline(true)
                .maxCacheAge(K_DEFAULT_CACHE_TIME)
                .build();
    }
}
