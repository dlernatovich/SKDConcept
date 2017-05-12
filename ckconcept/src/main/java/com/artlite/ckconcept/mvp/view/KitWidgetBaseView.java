package com.artlite.ckconcept.mvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;

import java.util.List;

/**
 * {@link View} which provide the widget functional
 */

public abstract class KitWidgetBaseView extends BSView implements KitWidgetContract.View,
        OnKitActionCallback {
    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public KitWidgetBaseView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public KitWidgetBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public KitWidgetBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        getAdapteredView().showRefresh();
        getPresenter().getServerData(getContext(), this);
    }

    /**
     * Method which provide the setting of the {@link List} of the {@link BaseRecyclerItem}
     *
     * @param items {@link List} of the {@link BaseRecyclerItem}
     */
    @Override
    public void setItems(@NonNull List<BaseRecyclerItem> items) {
        final AdapteredView view = getAdapteredView();
        if (view != null) {
            view.set(items);
        }
    }

    /**
     * Method which provide the show of the progress
     */
    @Override
    public void showProgress() {
        final AdapteredView adapteredView = getAdapteredView();
        if (adapteredView != null) {
            adapteredView.showRefresh();
        }
    }

    /**
     * Method which provide the progress hiding
     */
    @Override
    public void hideProgress() {
        final AdapteredView adapteredView = getAdapteredView();
        if (adapteredView != null) {
            adapteredView.hideRefresh();
        }
    }

    /**
     * Method which provide the action when data is received
     *
     * @param context instance of {@link Context}
     * @param items
     */
    @Override
    public void onDataReceived(@NonNull Context context, @NonNull List<BaseRecyclerItem> items) {
        getAdapteredView().hideRefresh();
        setItems(items);
    }
}
