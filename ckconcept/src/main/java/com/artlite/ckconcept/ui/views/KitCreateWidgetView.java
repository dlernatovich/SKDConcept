package com.artlite.ckconcept.ui.views;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredBaseCallback;
import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.bslibrary.annotations.FindLibraryViewBy;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.models.menu.KitMenuModel;

import java.util.List;

/**
 * Class which provide the displaying of the list for create widget
 */

public final class KitCreateWidgetView extends BSView {

    /**
     * Constant of the {@link RecycleEvent} for the click functional
     */
    public static final RecycleEvent K_EVENT_CLICK = new RecycleEvent("KitCreateWidgetView:Click");

    /**
     * Instance of {@link AdapteredView}
     */
    @FindLibraryViewBy(name = "view_create_widget_recycle")
    private AdapteredView recycleView;

    /**
     * Instance of the {@link OnMenuClickListener}
     */
    private OnMenuClickListener listener;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    protected KitCreateWidgetView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context  instance of {@link Context}
     * @param listener instance of the {@link OnMenuClickListener}
     */
    public KitCreateWidgetView(Context context,
                               @Nullable final OnMenuClickListener listener) {
        super(context);
        setOnMenuClickListener(listener);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_create_widget_view;
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        setOnClickListeners(R.id.button_cancel);
        BSThreadManager.main(new BSThreadManager.OnThreadCallback() {
            @Override
            public void onExecute() {
                recycleView.init(new GridLayoutManager(getContext(), 1), listCallback);
            }
        });
    }

    /**
     * Method which provide the setting of the {@link OnMenuClickListener}
     *
     * @param listener instance of the {@link OnMenuClickListener}
     */
    public void setOnMenuClickListener(OnMenuClickListener listener) {
        this.listener = listener;
    }

    /**
     * Method which provide the setting of the list of the {@link KitMenuModel}
     *
     * @param objects {@link List} of the {@link KitMenuModel}
     */
    public void setObjects(@Nullable final List<KitMenuModel> objects) {
        setObjects(objects, null);
    }

    /**
     * Method which provide the setting of the list of the {@link KitMenuModel}
     *
     * @param objects  {@link List} of the {@link KitMenuModel}
     * @param layoutId {@link Integer} value of the layout ID
     */
    public void setObjects(@Nullable final List<KitMenuModel> objects,
                           @LayoutRes int layoutId) {
        setObjects(objects, Integer.valueOf(layoutId));
    }

    /**
     * Method which provide the setting of the list of the {@link KitMenuModel}
     *
     * @param objects  {@link List} of the {@link KitMenuModel}
     * @param layoutId {@link Integer} value of the layout ID
     */
    protected void setObjects(@Nullable final List<KitMenuModel> objects,
                              @Nullable @LayoutRes Integer layoutId) {
        if (recycleView != null) {
            if (layoutId != null) {
                for (KitMenuModel model : objects) {
                    model.setRecycleLayout(layoutId);
                }
            }
            recycleView.set(objects);
        }
    }

    /**
     * Method which provide the on click functional
     *
     * @param view instance of {@link View}
     */
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.button_cancel) {
            dismiss();
        }
    }

    //==============================================================================================
    //                                      CALLBACK
    //==============================================================================================

    private final OnAdapteredBaseCallback listCallback =
            new OnAdapteredBaseCallback<KitMenuModel>() {

                /**
                 * Method which provide the action when user press on the channel object
                 *
                 * @param index  current index
                 * @param object current object
                 */
                @Override
                public void onItemClick(int index, @NonNull KitMenuModel object) {
                    if (listener != null) {
                        if (!listener.onShowCreateView(object)) {
                            listener.onMenuItemClick(object);
                        }
                        dismiss();
                    }
                }

                /**
                 * Method which provide the action when user doing the long press on item
                 *
                 * @param index  index
                 * @param object object
                 */
                @Override
                public void onItemLongClick(int index, @NonNull KitMenuModel object) {

                }

                /**
                 * Method which provide the action listening
                 *
                 * @param recycleEvent event
                 * @param index        index
                 * @param object       object
                 */
                @Override
                public void onActionReceived(@NonNull RecycleEvent recycleEvent,
                                             int index,
                                             @NonNull KitMenuModel object) {
                    if (recycleEvent.equals(K_EVENT_CLICK)) {
                        if (listener != null) {
                            if (!listener.onShowCreateView(object)) {
                                listener.onMenuItemClick(object);
                            }
                            dismiss();
                        }
                    }
                }
            };

    /**
     * Listener which provide the action when user press one of the list item
     */
    public interface OnMenuClickListener {

        /**
         * Method which provide the action when menu item was press
         *
         * @param object instance of the {@link KitMenuModel}
         */
        boolean onShowCreateView(@NonNull KitMenuModel object);

        /**
         * Method which provide the action when menu item was press
         *
         * @param object instance of the {@link KitMenuModel}
         */
        void onMenuItemClick(@NonNull KitMenuModel object);
    }
}
