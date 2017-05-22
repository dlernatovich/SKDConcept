package com.artlite.ckconcept.constants;

import android.support.annotation.NonNull;

/**
 * Channel filter enumerator
 */

public enum KitChannelFilter {
    APPROVAL("MMXApproval"),
    NONE("");

    private final String filterKeyword;

    /**
     * Default constructor
     *
     * @param filterKeyword filter keyword
     */
    KitChannelFilter(@NonNull final String filterKeyword) {
        this.filterKeyword = filterKeyword;
    }

    /**
     * Get filter keyword
     *
     * @return method which provide the getting of the keyword filtering
     */
    @NonNull
    public String getFilterKeyword() {
        return filterKeyword;
    }
}
