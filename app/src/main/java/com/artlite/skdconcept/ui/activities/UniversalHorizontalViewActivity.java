package com.artlite.skdconcept.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.ui.activity.BSActivity;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.ui.views.universal.KitUniversalWidgetView;
import com.artlite.skdconcept.R;
import com.artlite.skdconcept.models.LabelObject;
import com.artlite.skdconcept.ui.views.ListObjectHorizontalLabel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlernatovich on 14.06.2017.
 */

public final class UniversalHorizontalViewActivity extends BSActivity {

    /**
     * Instance of the {@link KitUniversalWidgetView}
     */
    @FindViewBy(id = R.id.view_universal)
    private KitUniversalWidgetView widgetView;

    /**
     * Method which provide the getting of the layout ID for the current Activity
     *
     * @return layout ID for the current Activity
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_universal_horizontal;
    }

    /**
     * Method which provide the action when Activity is created
     *
     * @param bundle instance of the {@link Bundle}
     */
    @Override
    protected void onCreateActivity(@Nullable Bundle bundle) {
        setTitle(getString(R.string.text_universal));
        this.widgetView = (KitUniversalWidgetView) findViewById(R.id.view_universal);
        this.widgetView.showProgress();
        final List<KitBaseListObject> objects = new ArrayList<>();
        BSThreadManager.background(new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                for (int i = 0; i < 100; i++) {
                    final LabelObject object = new LabelObject();
                    final KitBaseListObject listObject = new ListObjectHorizontalLabel(object);
                    objects.add(listObject);
                }
                widgetView.setItems(objects);
                widgetView.hideProgress();
            }
        });
    }

    /**
     * Method which provide the defining if need to override of the transition animation
     *
     * @return defining results
     */
    @Override
    protected boolean isOverrideTransitionAnimation() {
        return true;
    }

    /**
     * Method which provide the checking if need back button into {@link ActionBar}
     *
     * @return checking if need back button into {@link ActionBar}
     */
    @Override
    protected boolean isNeedBackButton() {
        return true;
    }
}
