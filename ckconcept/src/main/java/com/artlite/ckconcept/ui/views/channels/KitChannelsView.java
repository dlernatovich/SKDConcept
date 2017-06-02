package com.artlite.ckconcept.ui.views.channels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.artlite.ckconcept.mvp.contracts.KitChannelsContract;
import com.artlite.ckconcept.mvp.impl.channels.KitDefaultChannelsPresenter;
import com.artlite.ckconcept.ui.abs.channels.KitBaseChannelsView;


/**
 * <h1>How to use</h1>
 * <code>
 *
 * @Override protected void onCreateActivity(@Nullable Bundle bundle) {
 * setTitle("Conversations");
 * channelsView.setOnChannelClickListener(this);
 * }
 * ...
 * @Override protected void onResume() {
 * super.onResume();
 * channelsView.onResumeView();
 * }
 * ...
 * @Override protected void onDestroy() {
 * channelsView.onDestroyView();
 * super.onDestroy();
 * }
 * <p>
 * </code>
 * Created by dlernatovich on 11/18/2016.
 */

public class KitChannelsView extends KitBaseChannelsView {

    /**
     * Constructor with context
     *
     * @param context context instance
     */
    public KitChannelsView(Context context) {
        super(context);
    }

    /**
     * Constructor with context and attributes
     *
     * @param context context instance
     * @param attrs   attributes instance
     */
    public KitChannelsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor with context, attributes and styles
     *
     * @param context      context instance
     * @param attrs        attributes instance
     * @param defStyleAttr styles instance
     */
    public KitChannelsView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    protected KitChannelsContract.Presenter createPresenter(@NonNull final KitChannelsContract.View view) {
        return new KitDefaultChannelsPresenter(view);
    }

}
