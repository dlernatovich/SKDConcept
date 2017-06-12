package com.artlite.ckconcept.comparators;

import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXMessage;

import java.util.Comparator;
import java.util.Date;

/**
 * Comparartor which provide the sorting of the
 */

public class KitComparatorMessages implements Comparator<MMXMessage> {

    /**
     * Method which provide the comparisons of the objects
     *
     * @param lhs instance of the {@link ChannelDetail}
     * @param rhs instance of the {@link ChannelDetail}
     * @return comparisons result
     */
    @Override
    public int compare(MMXMessage lhs, MMXMessage rhs) {
        final Date lch = lhs.getTimestamp();
        final Date rch = rhs.getTimestamp();
        if (lch == null || rch == null) {
            return 0;
        }
        return lch.compareTo(rch);
    }
}


