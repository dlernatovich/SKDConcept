package com.artlite.ckconcept.mvp.presenters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSContextManager;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.mvp.contracts.KitLoginContract;
import com.magnet.max.android.ApiCallback;
import com.magnet.max.android.ApiError;
import com.magnet.max.android.User;

/**
 * Created by dlernatovich on 23.05.2017.
 */

public class KitLoginPresenter implements KitLoginContract.Presenter {

    private final KitLoginContract.View view;

    /**
     * Constructor which provide to create the {@link KitLoginPresenter} from
     * instance of the {@link KitLoginContract.View}
     *
     * @param view instance of the {@link KitLoginContract.View}
     */
    public KitLoginPresenter(@NonNull final KitLoginContract.View view) {
        this.view = view;
    }

    /**
     * Method which provide the sign in to the max server
     */
    @Override
    public void resume() {
        final String methodName = "void resume()";
        showProgress();
        User.resumeSession(new ApiCallback<Boolean>() {
            @Override
            public void success(Boolean aBoolean) {
                if (getView() != null) {
                    getView().onLoginSuccess(User.getCurrentUser());
                }
                hideProgress();
            }

            @Override
            public void failure(ApiError error) {
                hideProgress();
                BSLogHelper.log(this, methodName, error, null);
            }
        });
    }

    /**
     * Method which provide the sign in to the max server
     */
    @Override
    public void signIn() {
        showProgress();
        if (getView() != null) {
            final EditText loginEdit = getView().getLoginEdit();
            final EditText passwordEdit = getView().getPasswordEdit();
            if (BSValidationHelper.validateNull(loginEdit, passwordEdit)) {
                final String login = loginEdit.getText().toString().trim();
                final String password = passwordEdit.getText().toString().trim();
                if (BSValidationHelper.isEmpty(login)) {
                    loginEdit.setError(BSContextManager
                            .getRegisteredContext()
                            .getResources()
                            .getString(R.string.text_ck_error_empty_login));
                    loginEdit.requestFocus();
                    hideProgress();
                    return;
                }

                if (BSValidationHelper.isEmpty(password)) {
                    passwordEdit.setError(BSContextManager
                            .getRegisteredContext()
                            .getResources()
                            .getString(R.string.text_ck_error_empty_password));
                    passwordEdit.requestFocus();
                    hideProgress();
                    return;
                }

                User.login(login, password, true, new ApiCallback<Boolean>() {
                    @Override
                    public void success(Boolean aBoolean) {
                        if (getView() != null) {
                            hideProgress();
                            getView().onLoginSuccess(User.getCurrentUser());
                        }
                    }

                    @Override
                    public void failure(ApiError error) {
                        if (getView() != null) {
                            hideProgress();
                            getView().onLoginError(error);
                        }
                    }
                });
            }
        }
    }

    /**
     * Method which provide the show of the progress
     */
    @Override
    public void showProgress() {
        if (getView() != null) {
            getView().showProgress();
        }
    }

    /**
     * Method which provide the hide of the progress
     */
    @Override
    public void hideProgress() {
        if (getView() != null) {
            getView().hideProgress();
        }
    }

    /**
     * Method which provide the getting of the instance of the {@link KitLoginContract.View}
     *
     * @return instance of the {@link KitLoginContract.View}
     */
    @Nullable
    @Override
    public KitLoginContract.View getView() {
        return view;
    }
}
