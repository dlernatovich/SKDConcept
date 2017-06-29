package com.artlite.ckconcept.mvp.abs.presenter;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.helpers.name.KitNameHelper;
import com.artlite.ckconcept.managers.widget.KitWidgetManager;
import com.artlite.ckconcept.models.define.KitBaseDefiner;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;

import java.util.List;

/**
 * Class which provide the default functional for the {@link KitWidgetContract.Presenter}
 */

public abstract class KitBaseWidgetPresenter implements KitWidgetContract.Presenter {

    /**
     * Instance of the {@link KitWidgetContract.View}
     */
    private final KitWidgetContract.View view;

    /**
     * Constructor which provide to create the {@link KitBaseWidgetPresenter} from
     *
     * @param view instance of {@link KitWidgetContract.View}
     */
    public KitBaseWidgetPresenter(@NonNull final KitWidgetContract.View view) {
        this.view = view;
    }

    /**
     * Method which provide the getting of the instance of the {@link KitWidgetContract.View}
     *
     * @return instance of the {@link KitWidgetContract.View}
     */
    @NonNull
    @Override
    public KitWidgetContract.View getView() {
        return this.view;
    }

    /**
     * Method which provide the getting of the {@link String} type from
     *
     * @param aClass instance of {@link Class}
     * @return {@link String} value of type
     */
    @Nullable
    @Override
    public String getType(@Nullable Class aClass) {
        return KitNameHelper.getClassType(aClass);
    }

    /**
     * Method which provide to getting of the {@link BaseRecyclerItem} by type
     *
     * @param object instance of {@link Object}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    @Nullable
    public KitBaseListObject getObject(@Nullable final Object object) {
        return KitWidgetManager.getViewList(object);
    }

    /**
     * Method which provide to getting of the {@link BaseRecyclerItem} by type
     *
     * @param type   {@link String} value of type
     * @param object instance of {@link Object}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    @Nullable
    public KitBaseListObject getObject(@Nullable final String type,
                                       @Nullable final Object object) {
        return KitWidgetManager.getViewList(type, object);
    }

    /**
     * Method which provide the show of the progress
     */
    @Override
    public void showProgress() {
        final KitWidgetContract.View view = getView();
        if (view != null) {
            final AdapteredView adapteredView = view.getAdapteredView();
            if (adapteredView != null) {
                adapteredView.showRefresh();
            }
        }
    }

    /**
     * Method which provide the progress hiding
     */
    @Override
    public void hideProgress() {
        final KitWidgetContract.View view = getView();
        if (view != null) {
            final AdapteredView adapteredView = view.getAdapteredView();
            if (adapteredView != null) {
                adapteredView.hideRefresh();
            }
        }
    }
}
