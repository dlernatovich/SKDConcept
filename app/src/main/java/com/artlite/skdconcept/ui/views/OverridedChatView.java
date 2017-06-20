package com.artlite.skdconcept.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.ui.views.chat.KitChatView;
import com.artlite.skdconcept.R;

/**
 * Created by dlernatovich on 6/20/2017.
 */

public class OverridedChatView extends KitChatView {
    /**
     * Constructor which provide the create {@link BSView} from
     * the instance of the {@link Context}
     *
     * @param context instance of {@link Context}
     */
    public OverridedChatView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     * the instance of the {@link Context} and attributes
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public OverridedChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     * the instance of the {@link Context} and attributes
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public OverridedChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the layout ID
     * for create widget list
     *
     * @return {@link Integer} value of the layout ID for create widget list
     * REQUIRED
     * @+id/image_menu as {@link ImageView}
     * @+id/label_header as {@link TextView}
     * @warning Keep null if you don't need to override the list items interface
     */
    @Nullable
    @Override
    public Integer getCreateWidgetLayout() {
        return R.layout.layout_create_view;
    }
}
