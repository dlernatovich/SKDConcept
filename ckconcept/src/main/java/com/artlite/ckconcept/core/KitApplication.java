package com.artlite.ckconcept.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;

import com.artlite.bslibrary.container.BSResourceContainer;
import com.artlite.bslibrary.core.BSInstance;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.factories.OnKitListObjectFactory;
import com.artlite.ckconcept.managers.widget.KitWidgetManager;
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
        //Register unsupported message views
        KitWidgetManager.setUnsupported(getWidgetUnsupported());
        KitWidgetManager.setMessageUnsupportedMy(getMessageUnsupportedMy());
        KitWidgetManager.setMessageUnsupportedOther(getMessageUnsupportedOther());
        //Init Magnet Max
        Max.init(getContext(), new MaxAndroidPropertiesConfig(this, getMagnetConfiguration()));
        //Register the widgets
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

    /**
     * Method which provide the getting of the list item for widgets that isn't support
     *
     * @return instance of the {@link OnKitListObjectFactory}
     */
    @Nullable
    protected abstract OnKitListObjectFactory getWidgetUnsupported();

    /**
     * Method which provide the getting of the list item for my message widgets that isn't support
     *
     * @return instance of the {@link OnKitListObjectFactory}
     */
    @Nullable
    protected abstract OnKitListObjectFactory getMessageUnsupportedMy();

    /**
     * Method which provide the getting of the list item for other message widgets that
     * isn't support
     *
     * @return instance of the {@link OnKitListObjectFactory}
     */
    @Nullable
    protected abstract OnKitListObjectFactory getMessageUnsupportedOther();
}
