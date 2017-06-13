package com.artlite.ckconcept.managers.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.callbacks.BSPermissionCallback;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.permission.BSPermissionHelper;
import com.artlite.bslibrary.managers.BSContextManager;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Manager which provide the location monitoring
 */

public final class KitLocationManager implements OnSuccessListener<Location> {

    /**
     * Instance of the {@link KitLocationManager}
     */
    private static KitLocationManager instance;

    /**
     * Instance of the {@link LocationManager}
     */
    private final LocationManager locationManager;

    /**
     * Instance of the {@link Location}
     */
    private Location location;

    /**
     * Method which provide the getting of the instance of the {@link KitLocationManager}
     *
     * @return instance of the {@link KitLocationManager}
     */
    public static KitLocationManager getInstance() {
        if (instance == null) {
            instance = new KitLocationManager();
        }
        return instance;
    }

    /**
     * Default constructor
     */
    private KitLocationManager() {
        this.locationManager = (LocationManager) BSContextManager.getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
    }


    /**
     * Method which provide the action when location getting successfully
     *
     * @param location instance of the {@link Location}
     */
    @Override
    public void onSuccess(Location location) {
        this.location = location;
    }

    /**
     * Method which provide the getting of the instance of the {@link Location}
     *
     * @return instance of the {@link Location}
     */
    @Nullable
    public Location getLocation() {
        final String methodName = "Location getLocation()";
        try {
            this.location = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            BSLogHelper.log(this, methodName, ex, locationManager);
        }

        try {
            if (this.location == null) {
                this.location = this.locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        } catch (Exception ex) {
            BSLogHelper.log(this, methodName, ex, locationManager);
        }
        return location;
    }

    /**
     * Method which provide the starting of the {@link Location} monitoring
     *
     * @param activity instance of the {@link Activity}
     */
    public void startLocationMonitoring(@Nullable final Activity activity) {
        BSPermissionHelper.requestPermissions(activity, new BSPermissionCallback() {
            @Override
            public void onPermissionGranted() {
            }
        }, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

}
