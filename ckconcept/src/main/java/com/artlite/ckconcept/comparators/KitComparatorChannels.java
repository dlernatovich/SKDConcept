package com.artlite.ckconcept.comparators;

import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;

import java.util.Comparator;

/**
 * Comparartor which provide the sorting of the
 */

public class KitComparatorChannels implements Comparator<ChannelDetail> {
    
    @Override
    public int compare(ChannelDetail lhs, ChannelDetail rhs) {
        final MMXChannel lch = lhs.getChannel();
        final MMXChannel rch = rhs.getChannel();
        if (lch.getLastTimeActive() == null || rch.getLastTimeActive() == null) {
            return 0;
        }
        return lch.getLastTimeActive().compareTo(rch.getLastTimeActive());
    }
}


