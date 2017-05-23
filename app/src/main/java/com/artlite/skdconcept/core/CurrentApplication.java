package com.artlite.skdconcept.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.core.KitApplication;
import com.artlite.ckconcept.managers.KitWidgetManager;
import com.artlite.ckwidgets.factories.KitFactoryChannel;
import com.artlite.skdconcept.R;
import com.magnet.max.android.Max;
import com.magnet.max.android.config.MaxAndroidPropertiesConfig;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public class CurrentApplication extends KitApplication {

    /**
     * Method which provide the registering of the widgets for the current {@link Application}
     */
    @Override
    protected void registerWidgets() {
        KitWidgetManager.register(KitWidgetType.CHANNEL.getValue(), new KitFactoryChannel());
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
