package com.artlite.skdconcept;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.ckconcept.core.KitApplication;
import com.magnet.max.android.Max;
import com.magnet.max.android.config.MaxAndroidPropertiesConfig;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public class CurrentApplication extends KitApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Max.init(this, new MaxAndroidPropertiesConfig(this, R.raw.iapprove));
        BSLogHelper.log(this, "onCreate", null, "Application: CurrentApplication is initialized");
    }
}
