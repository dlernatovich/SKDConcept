package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;
import com.magnet.max.android.api.group.MMXUserGroup;

import java.lang.ref.WeakReference;

/**
 * Created by Artli on 12.10.2016.
 */

public class KitRecycleGroup extends BaseObject {

    private final int layoutID;
    private final WeakReference<MMXUserGroup> group;

    /**
     * Constructor which provide the create object with layout ID and {@link MMXUserGroup} object
     *
     * @param layoutID layout ID
     * @param group    {@link MMXUserGroup} object
     */
    public KitRecycleGroup(int layoutID, @NonNull final MMXUserGroup group) {
        this.layoutID = layoutID;
        this.group = new WeakReference<MMXUserGroup>(group);
    }

    /**
     * Method which provide the getting group
     *
     * @return group object
     */
    @Nullable
    public MMXUserGroup getGroup() {
        if (group != null) {
            return group.get();
        }
        return null;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleItemView(context);
    }

    /**
     * View class
     */
    private final class RecycleItemView extends BaseRecyclerItem<KitRecycleGroup> {

        private AppCompatTextView name;

        public RecycleItemView(Context context) {
            super(context);
        }

        @Override
        public void setUp(@NonNull KitRecycleGroup recycleGroup) {
            final MMXUserGroup userGroup = recycleGroup.getGroup();
            if (userGroup != null) {
                name.setText(userGroup.getName());
            }
        }

        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        @Override
        protected int getClickedID() {
            return R.id.container;
        }

        @Override
        protected void onLinkInterface() {
            name = (AppCompatTextView) findViewById(R.id.label_group_name);
        }

        @Override
        protected void onCreateView() {

        }
    }
}
