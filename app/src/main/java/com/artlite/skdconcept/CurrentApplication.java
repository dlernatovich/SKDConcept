package com.artlite.skdconcept;

import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckconcept.callbacks.OnKitCreatorCallback;
import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.core.KitApplication;
import com.artlite.ckconcept.managers.KitWidgetManager;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckwidgets.widgets.KitWidgetChannel;
import com.magnet.max.android.Max;
import com.magnet.max.android.config.MaxAndroidPropertiesConfig;
import com.magnet.mmx.client.api.MMXChannel;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public class CurrentApplication extends KitApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Max.init(this, new MaxAndroidPropertiesConfig(this, R.raw.iapprove));
        BSLogHelper.log(this, "onCreate", null, "Application: CurrentApplication is initialized");
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
