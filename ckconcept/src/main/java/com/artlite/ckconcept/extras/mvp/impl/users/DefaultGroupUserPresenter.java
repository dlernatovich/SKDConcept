package com.artlite.ckconcept.extras.mvp.impl.users;

import android.support.annotation.NonNull;

import com.artlite.ckconcept.R;
import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleAlphabetDivider;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleGroup;
import com.magnet.max.android.helpers.api.user.main.MaxCoreUserHelper;

/**
 * Created by dlernatovich on 10/7/16.
 */

public class DefaultGroupUserPresenter extends AbstractGroupUserPresenter {


    public DefaultGroupUserPresenter(@NonNull GroupContactContract.View view) {
        super(view);
    }

    /**
     * Method which provide the getting the group header layout
     *
     * @return group header view
     * Requirement ids and types:
     * @+id/button_manage_header as {@link android.widget.Button}
     */
    @Override
    public int getGroupHeaderLayout() {
        return R.layout.view_extras_recycle_group_header;
    }

    /**
     * Method which provide the getting of the no groups layout id
     *
     * @return layout ID
     */
    @Override
    public int getNoGroupsLayout() {
        return R.layout.view_extras_recycle_none_groups;
    }

    /**
     * Method which provide the getting of the user layout
     *
     * @return user layout ID
     * REQUIREMENT IDS AND TYPES:
     * @+id/container as {@link GroupContactContract.View}
     * @+id/container_selected as {@link GroupContactContract.View}
     * @+id/label_short_name as {@link android.support.v7.widget.AppCompatTextView}
     * @+id/label_user_name as {@link android.support.v7.widget.AppCompatTextView}
     * @+id/image_avatar as {@link de.hdodenhof.circleimageview.CircleImageView}
     */
    @Override
    public int getUserLayout() {
        return R.layout.view_extras_recycle_user;
    }

    /**
     * Method which provide the getting layout for
     * {@link RecycleAlphabetDivider}
     *
     * @return layout ID
     * REQUIREMENT IDS AND TYPES:
     * @+id/label_text as {@link android.support.v7.widget.AppCompatTextView}
     */
    @Override
    public int getAlphabeticDividerLayout() {
        return R.layout.view_extras_recycle_alphabetic_divider;
    }

    /**
     * Method which provide the getting of the selected user layout
     *
     * @return layout ID
     * REQUIREMENT IDS AND TYPES:
     * @+id/label_short_name as {@link android.support.v7.widget.AppCompatTextView}
     * @+id/label_user_name as {@link android.support.v7.widget.AppCompatTextView}
     * @+id/image_avatar as {@link de.hdodenhof.circleimageview.CircleImageView}
     * <p>
     * REQUIREMENT MEASUREMENTS
     * HEIGHT: 85dp
     */
    @Override
    public int getSelectedUserLayout() {
        return R.layout.view_extras_recycle_selected_user;
    }

    /**
     * Method which provide the getting of the avatar update method
     */
    @Override
    public MaxCoreUserHelper.UpdateAvatarMethod getUpdateAvatarMethod() {
        return MaxCoreUserHelper.UpdateAvatarMethod.ALL;
    }

    /**
     * Method which provide the getting of the layout for
     * {@link RecycleGroup}
     *
     * @return layout ID
     * REQUIREMENT IDS AND TYPES:
     * @+id/container as {@link android.view.View}
     * @+id/label_group_name as {@link android.support.v7.widget.AppCompatTextView}
     */
    @Override
    public int getGroupLayout() {
        return R.layout.view_extras_recycle_group;
    }

    /**
     * Method which provide the getting of the search text size
     *
     * @return search text size
     */
    @Override
    public int getSearchTextSize() {
        return R.dimen.text_20;
    }

    /**
     * Method which provide the getting of the color for search hint
     *
     * @return search hint color
     */
    @Override
    public int getSearchHintColor() {
        return android.R.color.darker_gray;
    }

    /**
     * Method which provide the getting of the search text color
     *
     * @return search text color
     */
    @Override
    public int getSearchTextColor() {
        return android.R.color.black;
    }

    /**
     * Method which provide the defining of the needing in the user groups
     *
     * @return is need groups
     */
    @Override
    public boolean isNeedGroups() {
        return true;
    }

}
