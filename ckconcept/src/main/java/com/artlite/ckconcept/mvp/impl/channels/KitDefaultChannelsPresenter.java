package com.artlite.ckconcept.mvp.impl.channels;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.artlite.ckconcept.R;
import com.artlite.ckconcept.helpers.channel.KitChannelDetailsHelper;
import com.artlite.ckconcept.mvp.contracts.KitChannelsContract;


/**
 * Created by dlernatovich on 11/17/2016.
 */

public class KitDefaultChannelsPresenter extends KitBaseChannelsPresenter {

    /**
     * Default constructor
     *
     * @param view view
     */
    public KitDefaultChannelsPresenter(KitChannelsContract.View view) {
        super(view);
    }

    /**
     * Method which provide the getting of the channel layout
     *
     * @return channel layout
     */
    @Override
    public int getChannelLayout() {
        return R.layout.view_ck_recycle_channel;
    }

    /**
     * Method which provide the getting of the photo description for channel
     *
     * @return photo text
     */
    @Override
    public int getPhotoText() {
        return R.string.text_ck_photo;
    }

    /**
     * Method which provide the getting of the location description for channel
     *
     * @return location description
     */
    @Override
    public int getLocationText() {
        return R.string.text_ck_location;
    }

    /**
     * Method which provide the getting of the no messages description for channel
     *
     * @return no messages text
     */
    @Override
    public int getNoMessageText() {
        return R.string.text_ch_no_messages;
    }

    /**
     * Method which provide the getting of the pool text
     *
     * @return poll text
     */
    @Override
    public int getPoolText() {
        return R.string.text_ck_answer;
    }

    /**
     * Method which provide the getting of the answer text
     *
     * @return answer text
     */
    @Override
    public int getAnswerText() {
        return R.string.text_ck_answer;
    }

    /**
     * Method which provide the getting of the checklist text
     *
     * @return checklist text
     */
    @Override
    public int getChecklistText() {
        return R.string.text_ch_checklist;
    }

    /**
     * Method which provide the getting of the refresh color
     *
     * @return refresh color
     */
    @Override
    @ColorRes
    public int getRefreshColor() {
        return R.color.color_ck_accent;
    }

    /**
     * Get color for refresh background
     *
     * @return color for refresh background
     */
    @Override
    @ColorRes
    public int getRefreshColorBackground() {
        return R.color.color_ck_background;
    }

    /**
     * Method which provide the getting of the message image resource
     *
     * @return message image resource
     */
    @Override
    public int getMessageImage() {
        return R.mipmap.ic_ck_message;
    }

    /**
     * Method which provide the getting of the location image resource
     *
     * @return location image resource
     */
    @Override
    public int getLocationImage() {
        return R.mipmap.ic_ck_map;
    }

    /**
     * Method which provide the getting of the photo image resource
     *
     * @return photo image resource
     */
    @Override
    public int getPhotoImage() {
        return R.mipmap.ic_ck_photo;
    }

    /**
     * Method which provide the getting of the pool image resource
     *
     * @return pool image resource
     */
    @Override
    public int getPoolImage() {
        return R.mipmap.ic_ck_poll;
    }

    /**
     * Method which provide the getting of the vote image resource
     *
     * @return vote image resource
     */
    @Override
    public int getVoteImage() {
        return R.mipmap.ic_ck_poll;
    }

    /**
     * Method which provide the getting of the checklist image resource
     *
     * @return checklist image resource
     */
    @Override
    public int getChecklistImage() {
        return R.drawable.ic_ck_checklist;
    }

    /**
     * Method which provide the getting of the layout manager
     *
     * @param context instance of the {@link Context}
     * @return layout manager
     */
    @NonNull
    @Override
    public RecyclerView.LayoutManager getLayoutManager(@NonNull Context context) {
        return new GridLayoutManager(context, 1);
    }

    /**
     * Method which provide the getting of the channel filter
     *
     * @return channel filter
     */
    @NonNull
    @Override
    public KitChannelDetailsHelper.ChannelFilter getChannelFilter() {
        return KitChannelDetailsHelper.ChannelFilter.APPROVAL;
    }
}
