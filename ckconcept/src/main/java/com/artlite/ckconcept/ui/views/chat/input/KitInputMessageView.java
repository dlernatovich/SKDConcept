package com.artlite.ckconcept.ui.views.chat.input;

import android.annotation.TargetApi;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.ckconcept.R;

/* How to used:
 <com...KitInputMsgView
    android:id="@+id/chat_input"
    app:actionMenu="@menu/menu_ck_input_actions"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
 */

/**
 * View providing a text input field, a row of icons for actions (e.g Attach, etc) and a Send button.
 */

public class KitInputMessageView extends FrameLayout {

    public interface OnTypingListener {
        void onTyping();
    }

    @Nullable
    private OnTypingListener onTypingListener;
    @Nullable
    private OnClickListener onSendClickListener;
    private MenuItem.OnMenuItemClickListener onMenuItemClickedListener;

    private EditText editText;
    private FloatingActionButton sendEnabled;
    private FloatingActionButton sendDisabled;
    private ViewGroup actionsContainer;
    private KitInputActionsMenu menu;

    private long lastTypingNotification; // Keep track of last notification time for throttling

    public KitInputMessageView(Context context) {
        super(context);
    }

    public KitInputMessageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
    }

    public KitInputMessageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KitInputMessageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
    }

    /**
     * Sets the listener to be invoked when the user taps the Send button.
     */
    public void setOnSendClickListener(@Nullable OnClickListener listener) {
        onSendClickListener = listener;
    }

    /**
     * Sets the listener to be invoked when the user is entering text in the text field.
     */
    public void setOnTypingListener(@Nullable OnTypingListener listener) {
        onTypingListener = listener;
    }

    /**
     * Sets the listener that will be invoked if an action item is clicked.
     */
    public void setOnActionItemClickedListener(@Nullable MenuItem.OnMenuItemClickListener listener) {
        onMenuItemClickedListener = listener;
    }

    /**
     * Sets the actions to be displayed below the text field. Only the icon and id is used from each menu item.
     */
    public void setActions(@MenuRes int menuRes) {
        menu = new KitInputActionsMenu(getContext());
        new MenuInflater(getContext()).inflate(menuRes, menu);
        populateMenuIfReady();
    }

    /**
     * Returns the text currently entered into the text field.
     *
     * @return the text in the text field
     */
    @NonNull
    public String getText() {
        return editText.getText().toString();
    }

    /**
     * Clears the text in the text field.
     */
    public void clearText() {
        editText.setText("", TextView.BufferType.EDITABLE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        LayoutInflater.from(getContext()).inflate(R.layout.view_ck_input_field, this);
        actionsContainer = (ViewGroup) findViewById(R.id.input_actions_container);
        editText = (EditText) findViewById(R.id.input_edit_text);
        editText.addTextChangedListener(new KitInputTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    return;
                }

                long elapsedTime = SystemClock.elapsedRealtime() - lastTypingNotification;
                if (onTypingListener != null && s.length() > 0 && elapsedTime > 2000) {
                    lastTypingNotification = SystemClock.elapsedRealtime();
                    onTypingListener.onTyping();
                }

                updateSendButtonState(s.length() > 0);
            }
        });

        sendEnabled = (FloatingActionButton) findViewById(R.id.input_send_enabled);
        sendEnabled.setEnabled(true);
        sendEnabled.setVisibility(View.GONE);
        sendEnabled.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSendClickListener != null) {
                    onSendClickListener.onClick(v);
                }
            }
        });
        sendDisabled = (FloatingActionButton) findViewById(R.id.input_send_disabled);
        sendDisabled.setEnabled(false);
        sendDisabled.setVisibility(View.VISIBLE);

        populateMenuIfReady();
    }

    private void parseAttributes(@NonNull AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.KitInputMessageView, 0, 0);

        try {
            int menuRes = a.getResourceId(R.styleable.KitInputMessageView_actionMenu, -1);
            if (menuRes != -1) {
                setActions(menuRes);
            }
        } finally {
            a.recycle();
        }
    }

    private void updateSendButtonState(boolean enable) {
        if ((enable && sendEnabled.getVisibility() == VISIBLE)
                || (!enable && sendDisabled.getVisibility() == VISIBLE)) {
            // Already in correct state
            return;
        }

        if (enable) {
            sendDisabled.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onHidden(FloatingActionButton fab) {
                    sendEnabled.show();
                }
            });
        } else {
            sendEnabled.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onHidden(FloatingActionButton fab) {
                    sendDisabled.show();
                }
            });
        }
    }

    private final OnClickListener mOnActionItemViewClicked = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (onMenuItemClickedListener != null) {
                onMenuItemClickedListener.onMenuItemClick((MenuItem) v.getTag());
            }
        }
    };

    private void populateMenuIfReady() {
        if (menu == null || actionsContainer == null) {
            return;
        }

        final LayoutInflater inflater = LayoutInflater.from(getContext());
        actionsContainer.removeAllViews();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);

            final View itemView = inflater.inflate(R.layout.view_ck_input_item, actionsContainer, false);
            final ImageView itemIcon = (ImageView) itemView.findViewById(R.id.input_action_image);

            itemView.setTag(item);
            itemView.setId(item.getItemId());
            itemIcon.setImageDrawable(item.getIcon());
            itemView.setOnClickListener(mOnActionItemViewClicked);

            actionsContainer.addView(itemView);
        }
    }
}
