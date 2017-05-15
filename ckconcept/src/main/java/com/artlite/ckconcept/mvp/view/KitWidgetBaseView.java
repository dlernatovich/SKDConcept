package com.artlite.ckconcept.mvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredPagingCallback;
import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredRefreshCallback;
import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.managers.KitWidgetManager;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.artlite.ckconcept.ui.views.KitCreateWidgetView;

import java.util.List;

/**
 * {@link View} which provide the widget functional
 */

public abstract class KitWidgetBaseView extends BSView implements KitWidgetContract.View,
        OnKitActionCallback, KitCreateWidgetView.OnMenuClickListener {

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
        showProgress();
        BSThreadManager.main(new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                getAdapteredView().init(getLayoutManager(getContext()),
                        KitWidgetBaseView.this, refreshCallback, pagingCallback);
                getAdapteredView().setIsNeedResfresh(isNeedSwipeRefresh());
                getPresenter().getServerData(getContext(), 0, KitWidgetBaseView.this);
                if (getCreateButtonId() != null) {
                    findViewById(getCreateButtonId()).setOnClickListener(createWidgetCallback);
                }
            }
        });
    }

    /**
     * Method which provide the setting of the {@link List} of the {@link BaseRecyclerItem}
     *
     * @param items {@link List} of the {@link BaseRecyclerItem}
     */
    @Override
    public void setItems(@NonNull List<BaseObject> items) {
        final AdapteredView view = getAdapteredView();
        if (view != null) {
            view.set(items);
        }
    }

    /**
     * Method which provide the adding of the {@link List} of the {@link BaseRecyclerItem}
     *
     * @param items {@link List} of the {@link BaseRecyclerItem}
     */
    public void addItems(@NonNull List<BaseObject> items) {
        final AdapteredView view = getAdapteredView();
        if (view != null) {
            view.add(items);
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
     * @param items   {@link List} of the {@link BaseRecyclerItem}
     */
    @Override
    public void onDataReceived(@NonNull Context context,
                               int offset,
                               @NonNull List<BaseObject> items) {
        hideProgress();
        if (offset == 0) {
            setItems(items);
        } else {
            addItems(items);
        }
    }

    //==============================================================================================
    //                                      ADAPTERED CALLBACK
    //==============================================================================================

    /**
     * Instance of the {@link OnAdapteredRefreshCallback}
     */
    private final OnAdapteredRefreshCallback refreshCallback =
            new OnAdapteredRefreshCallback() {

                /**
                 * Method which provide the swipe down to refresh listening
                 */
                @Override
                public void onRefreshData() {
                    final KitWidgetContract.Presenter presenter = getPresenter();
                    if (presenter != null) {
                        presenter.getServerData(getContext(), 0, KitWidgetBaseView.this);
                    }
                }
            };

    /**
     * Instance of {@link OnAdapteredPagingCallback}
     */
    private final OnAdapteredPagingCallback pagingCallback =
            new OnAdapteredPagingCallback() {

                /**
                 * Method which provide the notifying about end of list
                 *
                 * @param listSize list size
                 */
                @Override
                public void onNextPage(int listSize) {
                    final KitWidgetContract.Presenter presenter = getPresenter();
                    if (presenter != null) {
                        presenter.getServerData(getContext(), listSize, KitWidgetBaseView.this);
                    }
                }
            };

    /**
     * Callback which provide the add on click functional to button for create widget
     */
    private final OnClickListener createWidgetCallback = new OnClickListener() {
        @Override
        public void onClick(View view) {
            final KitCreateWidgetView createWidgetView = new KitCreateWidgetView(getContext(),
                    KitWidgetBaseView.this);
            final List<KitMenuModel> objects = KitWidgetManager.getCreateMenus(getCurrentClass());
            final Integer dropdownId = getViewDropdown();
            createWidgetView.setObjects(objects);
            if (!objects.isEmpty()) {
                if (dropdownId != null) {
                    final View dropdown = findViewById(dropdownId);
                    if (dropdown != null) {
                        createWidgetView.showAsDropdown(dropdown);
                        return;
                    }
                }
                createWidgetView.showAsDialog();
            }
        }
    };
}
