package com.artlite.ckconcept.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.artlite.bslibrary.container.BSResourceContainer;
import com.artlite.bslibrary.core.BSInstance;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.managers.KitWidgetManager;
import com.magnet.max.android.Max;
import com.magnet.max.android.config.MaxAndroidPropertiesConfig;

/**
 * Class which provide the {@link Application} container
 */

public abstract class KitApplication extends Application {

    /**
     * Method which provide the action when the {@link Application} is created
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //Init base library
        BSInstance.init(getContext());
        BSResourceContainer.create(R.id.class);
        BSLogHelper.log(this, "onCreate", null, "Application: KitApplication is initialized");
        //Init widget manager
        KitWidgetManager.init(getContext());
        Max.init(getContext(), new MaxAndroidPropertiesConfig(this, getMagnetConfiguration()));
        registerWidgets();
    }

    /**
     * Method which provide the registering of the widgets for the current {@link Application}
     */
    protected abstract void registerWidgets();

    /**
     * Method which provide the getting of the instance of the {@link Context}
     *
     * @return instance of the {@link Context}
     */
    @NonNull
    public abstract Context getContext();

    /**
     * Method which provide the getting of the configuration for the Magnet Max server
     *
     * @return {@link Integer} value of the configuration ID
     */
    @RawRes
    @NonNull
    protected abstract int getMagnetConfiguration();
}
