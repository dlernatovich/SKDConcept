package com.artlite.ckconcept.ui.views.chat;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.mvp.abs.view.KitBaseWidgetView;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.artlite.ckconcept.mvp.presenters.KitChatPresenter;
import com.artlite.ckconcept.ui.views.chat.input.KitInputMsgView;
import com.magnet.mmx.client.api.ChannelDetail;
import com.magnet.mmx.client.api.MMXChannel;

/**
 * Class which provide the interface of chat
 */

public class KitChatView extends KitBaseWidgetView {

    /**
     * Instance of the {@link KitChatPresenter}
     */
    private KitChatPresenter presenter;

    /**
     * Instance of {@link AdapteredView}
     */
    private AdapteredView recycleView;

    /**
     * Instance of the {@link MMXChannel}
     */
    private MMXChannel channel;

    /**
     * Instance of the {@link KitInputMsgView}
     */
    private KitInputMsgView inputMsgView;

    /**
     * Constructor which provide the create {@link BSView} from
     * the instance of the {@link Context}
     *
     * @param context instance of {@link Context}
     */
    public KitChatView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     * the instance of the {@link Context} and attributes
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public KitChatView(Context context, AttributeSet attrs) {
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
    public KitChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the layout ID
     *
     * @return layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_chat;
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        this.recycleView = (AdapteredView) findViewById(R.id.view_ck_recycle_chat);
        this.inputMsgView = (KitInputMsgView) findViewById(R.id.view_ck_input);
        this.inputMsgView.setOnActionItemClickedListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                showCreateList();
                return true;
            }
        });
        this.inputMsgView.setOnSendClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = inputMsgView.getText();
                sendMessage(text);
                inputMsgView.clearText();
            }
        });
        super.onCreateView();
    }

    /**
     * Method which provide the action when
     */
    public void onDestroyView() {
        if (presenter != null) {
            presenter.setChannelInactive();
        }
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
            presenter = new KitChatPresenter(this);
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
        return new GridLayoutManager(context, 1, VERTICAL, true);
    }

    /**
     * Method which provide the defining if {@link RecyclerView} need
     * of the swipe down to refresh
     *
     * @return if {@link RecyclerView} need of the swipe down to refresh
     */
    @Override
    public boolean isNeedSwipeRefresh() {
        return false;
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
        return KitChatView.class;
    }

    /**
     * Method which provide the getting of the {@link Integer} value for dropdown {@link View}
     *
     * @return
     */
    @Nullable
    @Override
    public Integer getViewDropdown() {
        return null;
    }

    /**
     * Method which provide the performing of the error when the data received
     *
     * @param context instance of {@link Context}
     * @param offset  {@link Integer} value of the offset
     * @param error   instance of {@link Throwable}
     */
    @Override
    public void onServerError(@NonNull Context context, int offset,
                              @NonNull Throwable error) {

    }

    /**
     * Method which provide the setting of the {@link MMXChannel} id
     *
     * @param channelId {@link String} value of the {@link MMXChannel} Id
     */
    public void setChannelId(@Nullable final String channelId) {
        if (presenter != null) {
            presenter.setChannelId(getContext(), channelId, this);
        }
    }

    /**
     * Method which provide the setting of the instance of the {@link MMXChannel}
     *
     * @param channel instance of the {@link MMXChannel}
     */
    public void setChannel(@Nullable final MMXChannel channel) {
        if (presenter != null) {
            presenter.setChannel(getContext(), channel, this);
        }
    }

    /**
     * Method which provide the setting of the instance of the {@link MMXChannel}
     *
     * @param channel instance of the {@link ChannelDetail}
     */
    public void setChannel(@Nullable final ChannelDetail channel) {
        if (channel != null) {
            setChannel(channel.getChannel());
        }
    }

    //==============================================================================================
    //                                     SEND MESSAGE
    //==============================================================================================

    /**
     * Method which provide the sending of the text message
     *
     * @param message {@link String} value of the message
     */
    public void sendMessage(@Nullable final String message) {
        if (presenter != null) {
            presenter.sendMessage(message);
        }
    }

    /**
     * Method which provide the sending of the text message
     *
     * @param bitmap {@link String} value of the message
     */
    public void sendMessage(@Nullable final Bitmap bitmap) {
        if (presenter != null) {
            presenter.sendMessage(bitmap);
        }
    }

    /**
     * Method which provide the sending of the text message
     *
     * @param location {@link String} value of the message
     */
    public void sendMessage(@Nullable final Location location) {
        if (presenter != null) {
            presenter.sendMessage(location);
        }
    }
}
