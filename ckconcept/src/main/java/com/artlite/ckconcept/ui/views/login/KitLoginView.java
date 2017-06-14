package com.artlite.ckconcept.ui.views.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.artlite.bslibrary.helpers.preference.BSSharedPreferenceHelper;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.callbacks.OnKitLoginCallback;
import com.artlite.ckconcept.mvp.contracts.KitLoginContract;
import com.artlite.ckconcept.mvp.presenters.KitLoginPresenter;
import com.magnet.max.android.User;

/**
 * View which provide the logging functional
 */

public class KitLoginView extends BSView implements KitLoginContract.View {

    /**
     * {@link String} constants of the {@link android.content.SharedPreferences} key
     */
    private static final String K_SP_LOGIN_KEY = KitLoginView.class.getSimpleName() + ": [SP] LOGIN";

    /**
     * Instance of the {@link EditText}
     */
    private EditText loginEdit;

    /**
     * Instance of the {@link EditText}
     */
    private EditText passwordEdit;

    /**
     * Instance of the {@link View}
     */
    private View progressView;

    /**
     * Instance of the {@link OnKitLoginCallback}
     */
    private OnKitLoginCallback loginCallback;

    /**
     * Instance of the {@link KitLoginContract.Presenter}
     */
    private KitLoginContract.Presenter presenter;

    /**
     * Constructor which provide to create of the {@link KitLoginView} from
     * the instance of the {@link Context}
     *
     * @param context instance of the {@link Context}
     */
    public KitLoginView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide to create of the {@link KitLoginView} from
     * the instance of the {@link Context} and attributes
     *
     * @param context instance of the {@link Context}
     * @param attrs   instance of the {@link AttributeSet}
     */
    public KitLoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide to create of the {@link KitLoginView} from
     * the instance of the {@link Context} and attributes
     *
     * @param context      instance of the {@link Context}
     * @param attrs        instance of the {@link AttributeSet}
     * @param defStyleAttr {@link Integer} value of the default attribute style
     */
    public KitLoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the layout ID
     *
     * @return {@link Integer} value of the layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_login;
    }

    /**
     * Method which provide the action when view is create
     */
    @Override
    protected void onCreateView() {
        final String login = BSSharedPreferenceHelper.getString(getContext(), K_SP_LOGIN_KEY);
        loginEdit = (EditText) findViewById(R.id.edit_login);
        passwordEdit = (EditText) findViewById(R.id.edit_password);
        progressView = findViewById(R.id.view_progress);
        if ((login != null) && (!login.equalsIgnoreCase("null"))) {
            getLoginEdit().setText(login);
        }
        setOnClickListeners(R.id.button_sign_in);
        presenter = new KitLoginPresenter(this);
        presenter.resume();
    }

    /**
     * Method which provide the show of the progress
     */
    @Override
    public void showProgress() {
        progressView.setVisibility(VISIBLE);
    }

    /**
     * Method which provide the hide of the progress
     */
    @Override
    public void hideProgress() {
        progressView.setVisibility(GONE);
    }

    /**
     * Method which provide the functional when login is successed
     *
     * @param user instance of {@link User}
     */
    @Override
    public void onLoginSuccess(@Nullable User user) {
        BSSharedPreferenceHelper.save(getContext(), getLoginEdit().getText().toString().trim(),
                K_SP_LOGIN_KEY);
        if (loginCallback != null) {
            loginCallback.onLoginSuccess(user);
        }
    }

    /**
     * Method which provide the action when login error
     *
     * @param throwable instance of {@link Throwable}
     */
    @Override
    public void onLoginError(@NonNull Throwable throwable) {
        if (loginCallback != null) {
            loginCallback.onLoginError(throwable);
        }
    }

    /**
     * Method which provide the getting of the login {@link EditText}
     *
     * @return instance of the {@link EditText}
     */
    @NonNull
    @Override
    public EditText getLoginEdit() {
        return loginEdit;
    }

    /**
     * Method which provide the getting of password {@link EditText}
     *
     * @return instance of the {@link EditText}
     */
    @NonNull
    @Override
    public EditText getPasswordEdit() {
        return passwordEdit;
    }

    /**
     * Method which provide to clear of the password {@link EditText}
     */
    @Override
    public void clear() {
        if (getPasswordEdit() != null) {
            getPasswordEdit().setText("");
        }
    }

    /**
     * Method which provide the on click functional
     *
     * @param view instance of {@link View}
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_sign_in) {
            if (presenter != null) {
                presenter.signIn();
            }
        }
    }

    /**
     * Method which provide the setting of the user name
     *
     * @param name {@link String} value of the user name
     */
    public void setUserName(@Nullable final String name) {
        loginEdit.setText(name);
    }

    /**
     * Method which provide the setting of the password
     *
     * @param password {@link String} value of the password
     */
    public void setPassword(@Nullable final String password) {
        passwordEdit.setText(password);
    }

    /**
     * Method which provide the setting of the {@link OnKitLoginCallback}
     *
     * @param callback instance of the {@link OnKitLoginCallback}
     */
    public void setOnKitLoginCallback(@Nullable final OnKitLoginCallback callback) {
        this.loginCallback = callback;
    }


}
