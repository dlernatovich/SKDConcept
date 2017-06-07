package com.artlite.ckconcept.helpers.map;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

/**
 * Class which provide the helper for the Google Map
 */

public final class KitMapHelper extends BSBaseHelper {

    private static final String K_PREVIEW_LINK_FORMAT =
            "https://maps.googleapis.com/maps/api/staticmap" +
                    "?center=%s,%s" +
                    "&markers=color:red%%7Clabel:C%%7C%s,%s" +
                    "&size=%dx%d" +
                    "&zoom=%d";


    /**
     * Method which provide the of the preview image for the Google Map
     *
     * @param latitude  {@link String} value of the latitude
     * @param longitude {@link String} value of the longitude
     * @return {@link String} url value for the preview
     */
    @Nullable
    public static String getPreviewSmallMap(@Nullable final String latitude,
                                            @Nullable final String longitude) {
        return getPreviewMap(latitude, longitude, 250, 250, 18);
    }

    /**
     * Method which provide the of the preview image for the Google Map
     *
     * @param latitude  {@link String} value of the latitude
     * @param longitude {@link String} value of the longitude
     * @return {@link String} url value for the preview
     */
    @Nullable
    public static String getPreviewLargeMap(@Nullable final String latitude,
                                            @Nullable final String longitude) {
        return getPreviewMap(latitude, longitude, 600, 600, 18);
    }

    /**
     * Method which provide the of the preview image for the Google Map
     *
     * @param latitude  {@link String} value of the latitude
     * @param longitude {@link String} value of the longitude
     * @param width     {@link Integer} value of the width
     * @param height    {@link Integer} value of the height
     * @param zoom      {@link Integer} value of the zoom
     * @return {@link String} url value for the preview
     */
    @Nullable
    public static String getPreviewMap(@Nullable final String latitude,
                                       @Nullable final String longitude,
                                       @IntRange(from = 200, to = 600) int width,
                                       @IntRange(from = 200, to = 600) int height,
                                       @IntRange(from = 1, to = 30) int zoom) {
        if (!isEmpty(latitude, longitude)) {
            return String.format(K_PREVIEW_LINK_FORMAT,
                    latitude,
                    longitude,
                    latitude,
                    longitude,
                    width,
                    height,
                    zoom);
        }
        return null;
    }

}
