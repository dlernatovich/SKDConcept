package com.artlite.ckconcept.ui.views.channels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.callbacks.OnKitChannelsCallback;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.artlite.ckconcept.mvp.presenters.KitChannelPresenter;
import com.artlite.ckconcept.mvp.abs.view.KitBaseWidgetView;
import com.magnet.mmx.client.api.ChannelDetail;

/**
 * Class which provide the interface or channels list
 */

public class KitChannelsView extends KitBaseWidgetView {

    /**
     * Instance of the {@link OnKitChannelsCallback}
     */
    private OnKitChannelsCallback channelsCallback;

    /**
     * Instance of the {@link KitChannelPresenter}
     */
    private KitChannelPresenter presenter;

    /**
     * Instance of {@link AdapteredView}
     */
    private AdapteredView recycleView;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public KitChannelsView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public KitChannelsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public KitChannelsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_channels_view;
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        this.recycleView = (AdapteredView) findViewById(R.id.view_ck_recycle_channels);
        super.onCreateView();
    }

    /**
     * Method which provide the getting of the {@link AdapteredView}
     *
     * @return instance of the {@link AdapteredView}
     */
    @NonNull
    @Override
    public AdapteredView getAdapteredView() {
        return this.recycleView;
    }

    /**
     * Method which provide the getting of the instance of the {@link KitWidgetContract.Presenter}
     *
     * @return instance of the {@link KitWidgetContract.Presenter}
     */
    @NonNull
    @Override
    public KitWidgetContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = new KitChannelPresenter(this);
        }
        return presenter;
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
        return null;
    }

    /**
     * Method which provide the getting of the {@link RecyclerView.LayoutManager}
     *
     * @return instance of the {@link RecyclerView.LayoutManager}
     */
    @NonNull
    @Override
    public RecyclerView.LayoutManager getLayoutManager(@NonNull Context context) {
        return new GridLayoutManager(context, 1);
    }

    /**
     * Method which provide the defining if {@link RecyclerView} need
     * of the swipe down to refresh
     *
     * @return if {@link RecyclerView} need of the swipe down to refresh
     */
    @Override
    public boolean isNeedSwipeRefresh() {
        return true;
    }

    /**
     * method which provide the creating of the {@link Integer} value of the create button Id
     *
     * @return {@link Integer} value of the create button Id
     */
    @Nullable
    @Override
    public Integer getCreateButtonId() {
        return null;
    }

    /**
     * Method which provide the getting of the getting of the {@link Class} for inheritance
     * objects
     *
     * @return instance of {@link Class}
     */
    @NonNull
    @Override
    public Class getCurrentClass() {
        return KitChannelsView.class;
    }

    /**
     * Method which provide the getting of the {@link Integer} value for dropdown {@link View}
     *
     * @return
     */
    @Nullable
    @Override
    public Integer getViewDropdown() {
        return R.id.view_anchor;
    }

    /**
     * Method which provide the performing of the error when the data received
     *
     * @param context instance of {@link Context}
     * @param offset  {@link Integer} value of the offset
     * @param error   instance of {@link Throwable}
     */
    @Override
    public void onServerError(@NonNull Context context, int offset, @NonNull Throwable error) {

    }

    //==============================================================================================
    //                                      ACTIONS/CLICKS
    //==============================================================================================

    /**
     * Method which provide the action when {@link Event} received
     *
     * @param context instance of {@link Context}
     * @param view    instance of the {@link BSView}
     * @param event   instance of the {@link Event}
     */
    @Override
    public void onCreateEventReceived(@NonNull Context context,
                                      @NonNull BSView view,
                                      @NonNull Event event) {
        BSLogHelper.log(this, "void onCreateEventReceived(@NonNull Context context,\n" +
                        "                                      @NonNull BSView view,\n" +
                        "                                      @NonNull Event event)",
                null, event);
        super.onCreateEventReceived(context, view, event);
    }

    /**
     * Method which provide the action when user press on the channel object
     *
     * @param index  current index
     * @param object current object
     */
    @Override
    public void onItemClick(int index, @NonNull BaseObject object) {
        final String methodName = "onItemClick(int index, @NonNull BaseObject object)";
        BSLogHelper.log(this, methodName, null, object);
        try {
            KitBaseListObject listObject = (KitBaseListObject) object;
            ChannelDetail detail = (ChannelDetail) listObject.getObject();
            channelsCallback.onChannelClick(index, detail);
        } catch (Exception ex) {
            BSLogHelper.log(this, methodName, ex, null);
            super.onItemClick(index, object);
        }
    }

    /**
     * Method which provide the action when user doing the long press on item
     *
     * @param index  index
     * @param object object
     */
    @Override
    public void onItemLongClick(int index, @NonNull BaseObject object) {
        final String methodName = "onItemLongClick(int index, @NonNull BaseObject object)";
        BSLogHelper.log(this, methodName, null, object);
        try {
            KitBaseListObject listObject = (KitBaseListObject) object;
            ChannelDetail detail = (ChannelDetail) listObject.getObject();
            channelsCallback.onChannelLongClick(index, detail);
        } catch (Exception ex) {
            BSLogHelper.log(this, methodName, ex, null);
            super.onItemLongClick(index, object);
        }
    }

    /**
     * Method which provide the action listening
     *
     * @param recycleEvent event
     * @param index        index
     * @param object       object
     */
    @Override
    public void onActionReceived(@NonNull RecycleEvent recycleEvent,
                                 int index,
                                 @NonNull BaseObject object) {
        final String methodName = "onActionReceived(recycleEvent, index, object)";
        BSLogHelper.log(this, methodName, null, object);
        try {
            KitBaseListObject listObject = (KitBaseListObject) object;
            ChannelDetail detail = (ChannelDetail) listObject.getObject();
            channelsCallback.onActionReceived(recycleEvent, index, detail);
        } catch (Exception ex) {
            BSLogHelper.log(this, methodName, ex, null);
            super.onActionReceived(recycleEvent, index, object);
        }
    }

    /**
     * Method which provide the action when menu item was press
     *
     * @param object instance of the {@link KitMenuModel}
     */
    @Override
    public void onMenuItemClick(@NonNull KitMenuModel object) {
        BSLogHelper.log(this, "void onMenuItemClick(@NonNull KitMenuModel object)",
                null, object);
        super.onMenuItemClick(object);
    }

    //==============================================================================================
    //                                      GET/SET
    //==============================================================================================

    /**
     * Method which provide the setting of the {@link OnKitChannelsCallback}
     *
     * @param callback instance of the {@link OnKitChannelsCallback}
     */
    public void setChannelsCallback(@Nullable final OnKitChannelsCallback callback) {
        this.channelsCallback = callback;
    }
}
