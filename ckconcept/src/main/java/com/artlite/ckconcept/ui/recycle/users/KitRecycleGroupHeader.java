package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;

/**
 * Created by dlernatovich on 10/7/16.
 */

public class KitRecycleGroupHeader extends BaseObject {

    private final int layoutID;
    public static final RecycleEvent K_MANAGE_EVENT = new RecycleEvent(0b1);

    public KitRecycleGroupHeader(int layoutID) {
        this.layoutID = layoutID;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new View(context);
    }

    /**
     * View class
     */
    private final class View extends BaseRecyclerItem<KitRecycleGroupHeader> {

        private Button manageButton;

        public View(Context context) {
            super(context);
        }

        @Override
        public void setUp(@NonNull KitRecycleGroupHeader extRecycleGroupHeader) {

        }

        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        @Override
        protected void onLinkInterface() {
            manageButton = (Button) findViewById(R.id.button_manage_header);
        }

        @Override
        protected void onCreateView() {
            manageButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    sendEvent(K_MANAGE_EVENT);
                }
            });
        }
    }


}
