package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;

/**
 * Created by dlernatovich on 10/10/16.
 */

public class KitRecycleNoGroups extends BaseObject {

    private final int layoutID;

    public KitRecycleNoGroups(int layoutID) {
        this.layoutID = layoutID;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new View(context);
    }

    /**
     * View class
     */
    private final class View extends BaseRecyclerItem<KitRecycleNoGroups> {

        public View(Context context) {
            super(context);
        }

        @Override
        public void setUp(@NonNull KitRecycleNoGroups recycleNoGroups) {

        }

        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        @Override
        protected void onLinkInterface() {

        }

        @Override
        protected void onCreateView() {

        }
    }
}
