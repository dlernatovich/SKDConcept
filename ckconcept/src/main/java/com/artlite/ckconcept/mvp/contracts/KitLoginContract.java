package com.artlite.ckconcept.mvp.contracts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.magnet.max.android.User;

/**
 * Contract which provide the logging functional
 */

public interface KitLoginContract {

    /**
     * View interface for the {@link KitLoginContract}
     */
    interface View {
        /**
         * Method which provide the show of the progress
         */
        void showProgress();

        /**
         * Method which provide the hide of the progress
         */
        void hideProgress();

        /**
         * Method which provide the functional when login is successed
         *
         * @param user instance of {@link User}
         */
        void onLoginSuccess(@Nullable final User user);

        /**
         * Method which provide the action when login error
         *
         * @param throwable instance of {@link Throwable}
         */
        void onLoginError(@NonNull final Throwable throwable);

        /**
         * Method which provide the getting of the login {@link EditText}
         *
         * @return instance of the {@link EditText}
         */
        @NonNull
        EditText getLoginEdit();

        /**
         * Method which provide the getting of password {@link EditText}
         *
         * @return instance of the {@link EditText}
         */
        @NonNull
        EditText getPasswordEdit();

        /**
         * Method which provide to clear of the password {@link EditText}
         */
        void clear();
    }

    /**
     * Presenter interface for the {@link KitLoginContract}
     */
    interface Presenter {

        /**
         * Method which provide the resume session of the max server
         */
        void resume();

        /**
         * Method which provide the sign in to the max server
         */
        void signIn();

        /**
         * Method which provide the show of the progress
         */
        void showProgress();

        /**
         * Method which provide the hide of the progress
         */
        void hideProgress();

        /**
         * Method which provide the getting of the instance of the {@link View}
         *
         * @return instance of the {@link View}
         */
        @Nullable
        View getView();

    }

}
