package com.artlite.ckconcept.extras.ui.recycle.channels;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.extras.helpers.ChannelDetailsHelper;
import com.artlite.ckconcept.extras.managers.ChannelsCacheManager;
import com.magnet.mmx.client.api.ChannelDetail;

import java.lang.ref.WeakReference;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Class which provide the
 * {@link com.artlite.adapteredrecyclerview.models.BaseObject}
 * Created by dlernatovich on 11/17/2016.
 */

public class RecycleChannel extends BaseObject {

    //Required data
    @LayoutRes
    private final int layoutID;
    @StringRes
    private final int photoText;
    @StringRes
    private final int locationText;
    @StringRes
    private final int noMessagesText;
    @StringRes
    final int poolMessage;
    @StringRes
    final int answerMessage;
    @StringRes
    final int checklistMessage;
    @DrawableRes
    final int messageRes;
    @DrawableRes
    final int locationRes;
    @DrawableRes
    final int photoRes;
    @DrawableRes
    final int poolRes;
    @DrawableRes
    final int voteRes;
    @DrawableRes
    final int checklistRes;

    //Cached data
    @NonNull
    protected final String channelName;
    @NonNull
    protected final String channelShortName;
    protected final boolean isGroup;
    @Nullable
    protected String latestMessage;
    @Nullable
    protected Drawable typeDrawable;


    private final WeakReference<ChannelDetail> channel;

    /**
     * Default constructor
     *
     * @param layoutID         layout ID
     * @param photoText        photo text
     * @param locationText     location text
     * @param noMessagesText   no message text
     * @param poolMessage      pool text
     * @param answerMessage    answer text
     * @param checklistMessage checklist text
     * @param messageRes       message res
     * @param locationRes      loaction res
     * @param photoRes         photo res
     * @param poolRes          pool res
     * @param voteRes          vote res
     * @param checklistRes     checklist res
     * @param channel          channel object
     */
    public RecycleChannel(@LayoutRes int layoutID,
                          @StringRes int photoText,
                          @StringRes int locationText,
                          @StringRes int noMessagesText,
                          @StringRes int poolMessage,
                          @StringRes int answerMessage,
                          @StringRes int checklistMessage,
                          @DrawableRes int messageRes,
                          @DrawableRes int locationRes,
                          @DrawableRes int photoRes,
                          @DrawableRes int poolRes,
                          @DrawableRes int voteRes,
                          @DrawableRes int checklistRes,
                          @NonNull final ChannelDetail channel) {
        this.layoutID = layoutID;
        this.photoText = photoText;
        this.locationText = locationText;
        this.noMessagesText = noMessagesText;
        this.poolMessage = poolMessage;
        this.answerMessage = answerMessage;
        this.checklistMessage = checklistMessage;
        this.messageRes = messageRes;
        this.locationRes = locationRes;
        this.photoRes = photoRes;
        this.poolRes = poolRes;
        this.voteRes = voteRes;
        this.checklistRes = checklistRes;
        this.channel = new WeakReference<ChannelDetail>(channel);
        //Get required data
        this.channelName = ChannelDetailsHelper.getChannelName(channel);
        this.channelShortName = ChannelDetailsHelper.getChannelShortName(channel);
        this.isGroup = ChannelDetailsHelper.isGroupChat(channel);
    }

    /**
     * Method which provide the getting the view for RecycleChannel
     *
     * @param context instance of {@link Context}
     * @return channel view
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new ObjectView(context);
    }

    /**
     * Method which provide the getting of the channel name
     *
     * @return channel name
     */
    @NonNull
    protected String getChannelName() {
        return channelName;
    }

    /**
     * Method which provide the getting of the channel short name
     *
     * @return channel short name
     */
    @NonNull
    protected String getChannelShortName() {
        return channelShortName;
    }

    /**
     * Method which provide the getting of the channel date as {@link String}
     *
     * @return channel date string
     */
    @NonNull
    protected String getChannelDate() {
        return ChannelDetailsHelper.getDate(channel.get());
    }

    /**
     * Method which provide the getting of the latest message
     *
     * @param context context
     * @return latest message text
     */
    @NonNull
    protected String getLastMessage(@NonNull final Context context) {
        if (TextUtils.isEmpty(latestMessage)) {
            latestMessage = ChannelDetailsHelper.getLastMessage(context,
                    channel.get(),
                    noMessagesText,
                    locationText,
                    poolMessage,
                    photoText,
                    answerMessage,
                    checklistMessage);
        }
        return latestMessage;
    }

    /**
     * Method which provide the getting {@link ChannelDetail}
     *
     * @return instance of the {@link ChannelDetail}
     */
    @Nullable
    public ChannelDetail getChannel() {
        return channel.get();
    }

    /**
     * Method which provide the checking if current conversation is group
     *
     * @return is group
     */
    protected boolean isGroup() {
        return isGroup;
    }

    /**
     * Method which provide the setting of the channel avatar
     *
     * @param context   context
     * @param imageView image view
     */
    protected void setChannelAvatar(@Nullable final Context context,
                                    @Nullable final ImageView imageView) {
        ChannelDetailsHelper.setChannelAvatar(context, imageView, getChannel());
    }

    /**
     * Method which provide the getting of the last message drawable
     *
     * @param context context
     * @return last message type drawable
     */
    @Nullable
    protected Drawable getLastMessageTypeDrawable(@Nullable final Context context) {
        if (typeDrawable == null) {
            typeDrawable = ChannelDetailsHelper.getLastMessageTypeDrawable(context, getChannel(),
                    messageRes, locationRes, photoRes, poolRes, voteRes, checklistRes);
        }
        return typeDrawable;
    }

    /**
     * Method which provide the getting of the unread messages
     *
     * @return is have unread messages
     */
    protected boolean isHaveUnread() {
        return ChannelDetailsHelper.isHaveUnreadMessages(getChannel());
    }

    /**
     * Method which provide the clearing of the unread messages count
     */
    protected void clearUnread(@NonNull final ChannelDetail detail) {
        if ((detail != null) && (detail.getChannel() != null)) {
            ChannelsCacheManager.getInstance().clearUnreadMessages(detail);
            detail.getChannel().setUnreadCount(new Long(0));
        }
    }

    /**
     * View class
     */
    private final class ObjectView extends BaseRecyclerItem<RecycleChannel> {

        private ImageView imageGroup;
        private ImageView imageType;
        private View circleNameView;
        private AppCompatTextView labelShortName;
        private CircleImageView imageAvatar;

        private AppCompatTextView labelName;
        private AppCompatTextView labelTime;
        private AppCompatTextView labelDescription;

        private View unreadView;

        /**
         * Constructor which provide the create view from context
         *
         * @param context context
         */
        public ObjectView(Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up the UI
         *
         * @param recycleChannel object
         */
        @Override
        public void setUp(@NonNull final RecycleChannel recycleChannel) {
            //Get values
            final String channelName = recycleChannel.getChannelName();
            final String channelShortName = recycleChannel.getChannelShortName();
            final String channelDate = recycleChannel.getChannelDate();
            final String latestMessage = recycleChannel.getLastMessage(getContext());
            final boolean isGroup = recycleChannel.isGroup();
            final Drawable type = recycleChannel.getLastMessageTypeDrawable(getContext());
            final boolean isHaveUnread = recycleChannel.isHaveUnread();
            //Set values
            switchUnreadView(isHaveUnread);
            imageType.setImageDrawable(type);
            labelName.setText(channelName);
            labelTime.setText(channelDate);
            labelDescription.setText(latestMessage);
            if (isGroup == true) {
                circleNameView.setVisibility(GONE);
            } else {
                circleNameView.setVisibility(VISIBLE);
                labelShortName.setText(channelShortName);
                recycleChannel.setChannelAvatar(getContext(), imageAvatar);
            }
        }

        /**
         * Method which provide the getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        /**
         * Method which provide the interface linking
         */
        @Override
        protected void onLinkInterface() {
            imageType = (ImageView) findViewById(R.id.image_type);
            circleNameView = findViewById(R.id.view_circle_name);
            labelShortName = (AppCompatTextView) findViewById(R.id.label_short_name);
            imageAvatar = (CircleImageView) findViewById(R.id.image_channel_avatar);
            imageGroup = (ImageView) findViewById(R.id.image_group);
            labelName = (AppCompatTextView) findViewById(R.id.label_channel_name);
            labelTime = (AppCompatTextView) findViewById(R.id.label_date);
            labelDescription = (AppCompatTextView) findViewById(R.id.label_last_message);
            unreadView = (View) findViewById(R.id.view_unread);
        }

        /**
         * Method which provide the action when view created
         */
        @Override
        protected void onCreateView() {
        }

        /**
         * Method which provide the getting of the clicked ID
         *
         * @return clicked ID
         */
        @Override
        protected int getClickedID() {
            return R.id.container;
        }

        /**
         * Method which provide the action when user press on item
         *
         * @param object object
         */
        @Override
        protected void onItemClick(@NonNull RecycleChannel object) {
            clearUnread(object.getChannel());
            switchUnreadView(false);
        }

        /**
         * Method which provide the switching of the unread count view
         *
         * @param isHaveUnread is have unread messages
         */
        protected void switchUnreadView(boolean isHaveUnread) {
            if (unreadView != null) {
                unreadView.setVisibility((isHaveUnread == false) ? INVISIBLE : VISIBLE);
            }
        }
    }
}
