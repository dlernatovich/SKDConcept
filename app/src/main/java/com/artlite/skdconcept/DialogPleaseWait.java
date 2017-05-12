package com.artlite.skdconcept;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.artlite.bslibrary.ui.view.BSView;

/**
 * Created by dlernatovich on 12.05.2017.
 */

public final class DialogPleaseWait extends BSView {
    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public DialogPleaseWait(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public DialogPleaseWait(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public DialogPleaseWait(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_please_wait;
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        setOnClickListeners(R.id.button_cancel_logging);
    }

    /**
     * Method which provide the on click functional for {@link View}
     *
     * @param view instance of the {@link View}
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_cancel_logging: {
                dismiss();
                break;
            }
            default: {
                break;
            }
        }
    }
}
