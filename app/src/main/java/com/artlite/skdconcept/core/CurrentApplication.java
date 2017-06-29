package com.artlite.skdconcept.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.core.KitApplication;
import com.artlite.ckconcept.factories.OnKitListObjectFactory;
import com.artlite.ckconcept.managers.widget.KitWidgetManager;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckwidgets.factories.KitCreatorMessageLocation;
import com.artlite.ckwidgets.factories.KitCreatorMessagePhoto;
import com.artlite.ckwidgets.factories.KitCreatorMessageText;
import com.artlite.skdconcept.R;
import com.artlite.skdconcept.widget.WidgetJSONLabel;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public class CurrentApplication extends KitApplication {

    /**
     * Method which provide the registering of the widgets for the current {@link Application}
     */
    @Override
    protected void registerWidgets() {
        //Register the message with type - text
        // TODO: 6/9/2017 Move name to the creator
        KitWidgetManager.register(new KitCreatorMessageText());
        //Register the message with type - location
        KitWidgetManager.register(new KitCreatorMessageLocation());
        //Register the message with type - photo
        KitWidgetManager.register(new KitCreatorMessagePhoto());
        //Register JSON widget
        KitWidgetManager.register(new WidgetJSONLabel.Creator());
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

    /**
     * Method which provide the getting of the list item for widgets that isn't support
     *
     * @return instance of the {@link KitBaseListObject}
     */
    @Nullable
    @Override
    protected OnKitListObjectFactory getWidgetUnsupported() {
        return null;
    }

    /**
     * Method which provide the getting of the list item for my message widgets that isn't support
     *
     * @return instance of the {@link KitBaseListObject}
     */
    @Nullable
    @Override
    protected OnKitListObjectFactory getMessageUnsupportedMy() {
        return null;
    }

    /**
     * Method which provide the getting of the list item for other message widgets that
     * isn't support
     *
     * @return instance of the {@link KitBaseListObject}
     */
    @Nullable
    @Override
    protected OnKitListObjectFactory getMessageUnsupportedOther() {
        return null;
    }
}
