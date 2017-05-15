package com.artlite.ckconcept.core;

import android.app.Application;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.container.BSResourceContainer;
import com.artlite.bslibrary.core.BSInstance;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.callbacks.OnKitCreatorCallback;
import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.managers.KitWidgetManager;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckconcept.ui.widgets.KitWidgetChannel;
import com.magnet.mmx.client.api.MMXChannel;

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
        BSResourceContainer.create(R.id.class);
        BSLogHelper.log(this, "onCreate", null, "Application: KitApplication is initialized");
        //Init widget manager
        KitWidgetManager.init(this);
        KitWidgetManager.register(KitWidgetType.CHANNEL.getValue(), new OnKitCreatorCallback() {
            @Nullable
            @Override
            public KitWidgetModel create(@Nullable Object object) {
                if (object instanceof MMXChannel) {
                    return new KitWidgetChannel((MMXChannel) object);
                }
                return new KitWidgetChannel();
            }
        });
    }
}
