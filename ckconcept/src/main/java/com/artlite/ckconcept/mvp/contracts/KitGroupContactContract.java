package com.artlite.ckconcept.mvp.contracts;

import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.ckconcept.ui.recycle.users.KitRecycleAlphabetDivider;
import com.artlite.ckconcept.ui.recycle.users.KitRecycleGroup;
import com.magnet.max.android.User;
import com.magnet.max.android.api.group.MMXUserGroup;
import com.magnet.max.android.callbacks.MaxCoreActionCallback;
import com.magnet.max.android.helpers.api.user.group.MaxCoreUserGroupHelper;
import com.magnet.max.android.helpers.api.user.main.MaxCoreUserHelper;
import com.magnet.max.android.rest.options.CacheOptions;

import java.util.List;

/**
 * Contract for Contact contract with User Group
 * Created by dlernatovich on 10/7/16.
 */

public interface KitGroupContactContract {

    /**
     * Listener which provide the user long click functional
     */
    interface OnUserClickListener {
        /**
         * Method which provide the action when user long clicked
         *
         * @param user user object
         */
        void onUserLongClick(@NonNull final User user);
    }

    /**
     * View for contact view with group
     */
    interface View extends KitBaseExtrasContract.BaseView {
        String K_IGNORED_USER = "serveruser";

        /**
         * Method which provide the setting of the selected users
         *
         * @param users users for add
         */
        void setSelectedUsers(@Nullable final List<User> users);

        /**
         * Method which provide the setting of the recieved data
         *
         * @param users  users
         * @param groups groups
         */
        void setReceivedData(@NonNull final List<User> users,
                             @NonNull final List<MMXUserGroup> groups);

        /**
         * Method which provide the setting of the list cells
         *
         * @param users  users for cells
         * @param groups groups for cells
         */
        void setListCells(@NonNull final List<User> users,
                          @NonNull final List<MMXUserGroup> groups);

        /**
         * Method which provide the getting of the {@link MMXUserGroup} cells
         *
         * @param groups groups list
         * @return MMXUserGroup cells
         */
        @NonNull
        List<BaseObject> getGroupCells(@NonNull final List<MMXUserGroup> groups);

        /**
         * Method which provide the getting of the {@link User} cells
         *
         * @param users users list
         * @return user cells
         */
        @NonNull
        List<BaseObject> getUserCells(@NonNull final List<User> users);

        /**
         * Method which provide the progress showing
         */
        void showProgress();

        /**
         * Method which provide the progress hiding
         */
        void hideProgress();

        /**
         * Method which provide the search customization
         *
         * @param textColor text color for search
         * @param hintColor text color for hint
         * @param textSize  text size for hint and search
         */
        void onSearchCustomize(@ColorRes int textColor, @ColorRes int hintColor,
                               @DimenRes int textSize);

        /**
         * Method which provide the initializing of the ignored users
         */
        void onInitIgnoreUsers();

    }

    /**
     * Class for contact view with group
     */
    abstract class ViewClass extends KitBaseExtrasContract.BaseViewClass implements View {
    }

    /**
     * Presenter for contact view with group
     */
    interface Presenter extends KitBaseExtrasContract.BasePresenter<View> {

        /**
         * Default cache time
         *
         * @see Presenter#getCacheOptions()
         */
        int K_DEFAULT_CACHE_TIME = 60;

        /**
         * Method which provide the getting the group header layout
         *
         * @return group header view
         * REQUIREMENT IDS AND TYPES:
         * @+id/button_manage_header as {@link android.widget.Button}
         */
        int getGroupHeaderLayout();

        /**
         * Method which provide the getting of the no groups layout id
         *
         * @return layout ID
         */
        int getNoGroupsLayout();

        /**
         * Method which provide the getting of the user layout
         *
         * @return user layout ID
         * REQUIREMENT IDS AND TYPES:
         * @+id/container as {@link android.view.View}
         * @+id/container_selected as {@link android.view.View}
         * @+id/label_short_name as {@link android.support.v7.widget.AppCompatTextView}
         * @+id/label_user_name as {@link android.support.v7.widget.AppCompatTextView}
         * @+id/image_avatar as {@link de.hdodenhof.circleimageview.CircleImageView}
         * @+id/image_blocked as {@link android.widget.ImageView}
         */
        int getUserLayout();

        /**
         * Method which provide the getting layout for
         * {@link KitRecycleAlphabetDivider}
         *
         * @return layout ID
         * REQUIREMENT IDS AND TYPES:
         * @+id/label_text as {@link android.support.v7.widget.AppCompatTextView}
         */
        int getAlphabeticDividerLayout();

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
        int getSelectedUserLayout();

        /**
         * Method which provide the getting of the avatar update method
         */
        MaxCoreUserHelper.UpdateAvatarMethod getUpdateAvatarMethod();

        /**
         * Method which provide the getting of the layout for
         * {@link KitRecycleGroup}
         *
         * @return layout ID
         * REQUIREMENT IDS AND TYPES:
         * @+id/container as {@link android.view.View}
         * @+id/label_group_name as {@link android.support.v7.widget.AppCompatTextView}
         */
        int getGroupLayout();

        /**
         * Method which provide the getting of the search text size
         *
         * @return search text size
         */
        @DimenRes
        int getSearchTextSize();

        /**
         * Method which provide the getting of the color for search hint
         *
         * @return search hint color
         */
        @ColorRes
        int getSearchHintColor();

        /**
         * Method which provide the getting of the search text color
         *
         * @return search text color
         */
        @ColorRes
        int getSearchTextColor();

        /**
         * Method which provide the getting of the refresh color
         *
         * @return color resource
         */
        @ColorRes
        int getRefreshColor();

        /**
         * Method which provide the getting of the refresh background color
         *
         * @return refresh background color
         */
        @ColorRes
        int getRefreshBackgroundColor();

        /**
         * Method which provide the getting of the
         * {@link com.magnet.max.android.helpers.api.user.group.MaxCoreUserGroupHelper_Constants.GroupType}
         *
         * @return group type
         */
        @NonNull
        MaxCoreUserGroupHelper.GroupType getGroupType();

        /**
         * Method which provide the recieving of the server data
         *
         * @param offset      offset
         * @param searchQuery search query
         */
        void onReceiveData(int offset, @Nullable final String searchQuery);

        /**
         * Method which provide the receiving of the groups
         *
         * @param searchQuery search query
         */
        void onReceiveGroups(@Nullable final String searchQuery);

        /**
         * Method which provide the getting of the groups
         *
         * @param groups      groups
         * @param offset      offset
         * @param searchQuery search query
         */
        void onReceiveUsers(@NonNull final List<MMXUserGroup> groups, int offset,
                            @Nullable final String searchQuery);

        /**
         * Method which provide the defining of the needing in the user groups
         *
         * @return is need groups
         */
        boolean isNeedGroups();

        /**
         * Method which provide the checking if need block user icon
         *
         * @return if is need of the block user icon
         */
        boolean isNeedBlockedIcon();


        /**
         * Method which provide the block user
         *
         * @param user user for block
         */
        void switchUserBlocking(@Nullable final User user,
                                @Nullable final MaxCoreActionCallback<User> callback);

        /**
         * Method which provide the getting of the {@link CacheOptions}
         *
         * @return instance of {@link CacheOptions}
         */
        @NonNull
        CacheOptions getCacheOptions();

    }

}
