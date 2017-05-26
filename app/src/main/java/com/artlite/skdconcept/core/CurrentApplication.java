package com.artlite.skdconcept.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.ckconcept.core.KitApplication;
import com.artlite.skdconcept.R;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public class CurrentApplication extends KitApplication {

    /**
     * Method which provide the registering of the widgets for the current {@link Application}
     */
    @Override
    protected void registerWidgets() {
        // TODO: 26.05.2017 Leave this as example
//        KitWidgetManager.register(KitWidgetType.CHANNEL.getValue(), new KitFactoryChannel());
    }

    /**
     * Method which provide the getting of the instance of the {@link Context}
     *
     * @return instance of the {@link Context}
     */
    @NonNull
    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Method which provide the getting of the configuration for the Magnet Max server
     *
     * @return {@link Integer} value of the configuration ID
     */
    @NonNull
    @Override
    protected int getMagnetConfiguration() {
        return R.raw.iapprove;
    }
}
