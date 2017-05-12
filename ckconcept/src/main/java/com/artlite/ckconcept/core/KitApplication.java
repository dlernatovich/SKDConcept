package com.artlite.ckconcept.core;

import android.app.Application;

import com.artlite.bslibrary.core.BSInstance;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckconcept.managers.KitWidgetManager;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public class KitApplication extends Application {

    /**
     * Method which prvide the action when the {@link KitApplication} is created
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //Init base library
        BSInstance.init(this);
        //Init widget manager
        KitWidgetManager.init(this);
        BSLogHelper.log(this, "onCreate", null, "Application: KitApplication is initialized");
    }
}
