package com.artlite.ckconcept.extras.ui.views.impl.channels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.artlite.ckconcept.extras.mvp.contract.ChannelsContract;
import com.artlite.ckconcept.extras.mvp.impl.channels.DefaultChannelsPresenter;
import com.artlite.ckconcept.extras.ui.views.abs.channels.AbsChannelsView;


/**
 * Created by dlernatovich on 11/18/2016.
 */

public class DefaultChannelsView extends AbsChannelsView {

    /**
     * Constructor with context
     *
     * @param context context instance
     */
    public DefaultChannelsView(Context context) {
        super(context);
    }

    /**
     * Constructor with context and attributes
     *
     * @param context context instance
     * @param attrs   attributes instance
     */
    public DefaultChannelsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor with context, attributes and styles
     *
     * @param context      context instance
     * @param attrs        attributes instance
     * @param defStyleAttr styles instance
     */
    public DefaultChannelsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the presenter
     *
     * @param view current view
     * @return presenter
     */
    @NonNull
    @Override
    protected ChannelsContract.Presenter createPresenter(@NonNull final ChannelsContract.View view) {
        return new DefaultChannelsPresenter(view);
    }

}
