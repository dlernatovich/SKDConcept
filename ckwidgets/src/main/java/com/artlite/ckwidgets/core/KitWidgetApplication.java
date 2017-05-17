package com.artlite.ckwidgets.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.core.KitApplication;
import com.artlite.ckconcept.managers.KitWidgetManager;
import com.artlite.ckwidgets.factories.KitFactoryChannel;

/**
 * Class which provide the {@link Application} container
 */

public class KitWidgetApplication extends KitApplication {


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
        return getBaseContext();
    }
}
