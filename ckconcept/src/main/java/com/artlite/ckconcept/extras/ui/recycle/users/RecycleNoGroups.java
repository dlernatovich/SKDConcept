package com.artlite.ckconcept.extras.ui.recycle.users;

import android.content.Context;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;

/**
 * Created by dlernatovich on 10/10/16.
 */

public class RecycleNoGroups extends BaseObject {

    private final int layoutID;

    public RecycleNoGroups(int layoutID) {
        this.layoutID = layoutID;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new View(context);
    }

    /**
     * View class
     */
    private final class View extends BaseRecyclerItem<RecycleNoGroups> {

        public View(Context context) {
            super(context);
        }

        @Override
        public void setUp(@NonNull RecycleNoGroups recycleNoGroups) {

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
