package com.artlite.skdconcept;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckwidgets.core.KitWidgetApplication;
import com.magnet.max.android.Max;
import com.magnet.max.android.config.MaxAndroidPropertiesConfig;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public class CurrentApplication extends KitWidgetApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Max.init(this, new MaxAndroidPropertiesConfig(this, R.raw.iapprove));
        BSLogHelper.log(this, "onCreate", null, "Application: CurrentApplication is initialized");
    }
}
