package com.artlite.ckconcept.mvp.contracts;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Class which provide the extras constants
 * Created by dlernatovich on 10/7/16.
 */

public interface KitBaseExtrasContract {

    /**
     * Base view interface
     */
    interface BaseView {
        /**
         * Method which provide the action with on create view
         */
        void onCreateView();

        /**
         * Method which provide the action when view resumed
         */
        void onResumeView();

        /**
         * Method which provide the action when pause view
         */
        void onPauseView();

        /**
         * Method which provide the action when view destroyed
         */
        void onDestroyView();

        /**
         * Method which provide the getting of the current context
         *
         * @return current {@link Context}
         */
        @NonNull
        Context getCurrentContext();

    }

    /**
     * Base View class
     */
    abstract class BaseViewClass implements BaseView {

        /**
         * Method which provide the action with on create view
         */
        @Override
        public void onCreateView() {

        }

        /**
         * Method which provide the action when view resumed
         */
        @Override
        public void onResumeView() {

        }

        /**
         * Method which provide the action when pause view
         */
        @Override
        public void onPauseView() {

        }

        /**
         * Method which provide the action when view destroyed
         */
        @Override
        public void onDestroyView() {

        }

    }

    /**
     * Base presenter
     *
     * @param <T> type
     */
    interface BasePresenter<T extends BaseView> {

    }


}
