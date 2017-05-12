package com.artlite.ckconcept.mvp.contracts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;

import java.util.List;

/**
 * Interface which provide the contract functional
 */

public interface KitWidgetContract {

    /**
     * View for the widget functional
     */
    interface View {

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
        void setItems(@NonNull List<BaseRecyclerItem> items);

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

    }

    interface Presenter {

        /**
         * Method which provide the getting of the server data
         *
         * @param context  instance of {@link Context}
         * @param callback instance of {@link OnKitActionCallback}
         * @return
         */
        @Nullable
        void getServerData(@NonNull Context context,
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
        BaseRecyclerItem getObject(@Nullable final String type, @Nullable final Object object);

        /**
         * Method which provide the show of the progress
         */
        void showProgress();

        /**
         * Method which provide the progress hiding
         */
        void hideProgress();
    }
}
