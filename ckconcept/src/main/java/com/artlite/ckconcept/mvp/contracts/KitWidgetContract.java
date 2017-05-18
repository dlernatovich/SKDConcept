package com.artlite.ckconcept.mvp.contracts;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredBaseCallback;
import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.callbacks.OnKitEventCallback;

import java.util.List;

/**
 * Interface which provide the contract functional
 */

public interface KitWidgetContract {

    /**
     * View for the widget functional
     */
    interface View extends OnAdapteredBaseCallback {

        /**
         * Method which provide the getting of the {@link AdapteredView}
         *
         * @return instance of the {@link AdapteredView}
         */
        @NonNull
        AdapteredView getAdapteredView();

        /**
         * Method which provide the setting of the {@link List} of the {@link BaseRecyclerItem}
         *
         * @param items {@link List} of the {@link BaseRecyclerItem}
         */
        void setItems(@NonNull List<BaseObject> items);

        /**
         * Method which provide the adding of the {@link List} of the {@link BaseRecyclerItem}
         *
         * @param items {@link List} of the {@link BaseRecyclerItem}
         */
        void addItems(@NonNull List<BaseObject> items);

        /**
         * Method which provide the getting of the instance of the {@link Presenter}
         *
         * @return instance of the {@link Presenter}
         */
        @NonNull
        Presenter getPresenter();

        /**
         * Method which provide the show of the progress
         */
        void showProgress();

        /**
         * Method which provide the progress hiding
         */
        void hideProgress();

        /**
         * Method which provide the getting of the {@link Integer} value of the layout ID
         * for create widget list
         *
         * @return {@link Integer} value of the layout ID for create widget list
         * REQUIRED
         * @+id/image_menu as {@link android.widget.ImageView}
         * @+id/label_header as {@link android.widget.TextView}
         * @warning Keep null if you don't need to override the list items interface
         */
        @LayoutRes
        @Nullable
        Integer getCreateWidgetLayout();

        /**
         * Method which provide the getting of the {@link RecyclerView.LayoutManager}
         *
         * @param context instance of {@link Context}
         * @return instance of the {@link RecyclerView.LayoutManager}
         */
        @NonNull
        RecyclerView.LayoutManager getLayoutManager(@NonNull Context context);

        /**
         * Method which provide the defining if {@link RecyclerView} need
         * of the swipe down to refresh
         *
         * @return if {@link RecyclerView} need of the swipe down to refresh
         */
        boolean isNeedSwipeRefresh();

        /**
         * method which provide the creating of the {@link Integer} value of the create button Id
         *
         * @return {@link Integer} value of the create button Id
         */
        @Nullable
        @IdRes
        Integer getCreateButtonId();

        /**
         * Method which provide the getting of the getting of the {@link Class} for inheritance
         * objects
         *
         * @return instance of {@link Class}
         */
        @NonNull
        Class getCurrentClass();

        /**
         * Method which provide the getting of the {@link Integer} value for dropdown {@link View}
         *
         * @return
         */
        @Nullable
        Integer getViewDropdown();

        /**
         * Method which provide the action when {@link BSView.Event} received
         *
         * @param context instance of {@link Context}
         * @param view    instance of the {@link BSView}
         * @param event   instance of the {@link BSView.Event}
         */
        void onCreateEventReceived(@NonNull final Context context,
                                   @NonNull final BSView view,
                                   @NonNull final BSView.Event event);

        /**
         * Method which provide the getting of the instance of the {@link OnKitEventCallback}
         *
         * @return instance of the {@link OnKitEventCallback}
         */
        @Nullable
        BSView.OnDialogCallback getEventCallback();

        /**
         * Method which provide the show of the creation list
         */
        void showCreateList();
    }

    interface Presenter {

        /**
         * Method which provide the getting of the server data
         *
         * @param context  instance of {@link Context}
         * @param offset   {@link Integer} value of the offset
         * @param callback instance of {@link OnKitActionCallback}
         * @return
         */
        @Nullable
        void getServerData(@NonNull Context context,
                           int offset,
                           @Nullable final OnKitActionCallback callback);

        /**
         * Method which provide the getting of the instance of the {@link View}
         *
         * @return instance of the {@link View}
         */
        @NonNull
        View getView();

        /**
         * Method which provide the getting of the class type from the {@link Object}
         *
         * @param object instance of {@link Object}
         * @return {@link String} value of the type
         */
        @Nullable
        String getType(@NonNull final Object object);

        /**
         * Method which provide the getting of the {@link String} type from
         *
         * @param aClass instance of {@link Class}
         * @return {@link String} value of type
         */
        @Nullable
        String getType(@Nullable final Class aClass);

        /**
         * Method which provide to getting of the {@link BaseRecyclerItem} by type
         *
         * @param type   {@link String} value of type
         * @param object instance of {@link Object}
         * @return instance of the {@link BaseRecyclerItem}
         */
        @Nullable
        BaseObject getObject(@Nullable final String type, @Nullable final Parcelable object);

        /**
         * Method which provide the show of the progress
         */
        void showProgress();

        /**
         * Method which provide the progress hiding
         */
        void hideProgress();

        /**
         * Methd which provide the getting of the {@link android.view.View} class
         *
         * @return instance of the {@link android.view.View} class
         */
        @NonNull
        Class getViewClass();
    }
}
