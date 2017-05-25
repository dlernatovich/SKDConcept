package com.artlite.ckconcept.extras.ui.views.abs.users;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;

import com.artlite.adapteredrecyclerview.helpers.ValidationHelper;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleAlphabetDivider;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleGroup;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleSelectedUser;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleUser;
import com.magnet.max.android.User;
import com.magnet.max.android.api.group.MMXUserGroup;
import com.magnet.max.android.helpers.api.user.main.MaxCoreUserHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artli on 15.12.2016.
 */

abstract class AbsGroupUserView_View extends AbsGroupUserView_Callbacks {

    public AbsGroupUserView_View(Context context) {
        super(context);
    }

    public AbsGroupUserView_View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsGroupUserView_View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * GroupContactContract.ViewClass object
     */
    protected final GroupContactContract.ViewClass view = new GroupContactContract.ViewClass() {

        /**
         * Method which provide the setting of the selected users
         *
         * @param users users for add
         */
        @Override
        public void setSelectedUsers(@Nullable List<User> users) {
            final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod = presenter.getUpdateAvatarMethod();
            if (users.size() > 0) {
                selectedAdapteredList.setVisibility(VISIBLE);
                final List<BaseObject> recycleUsers = new ArrayList<>();
                for (User user : users) {
                    recycleUsers.add(new RecycleSelectedUser(presenter.getSelectedUserLayout(),
                            user, avatarMethod));
                }
                selectedAdapteredList.set(recycleUsers);
            } else {
                selectedAdapteredList.clear();
                selectedAdapteredList.setVisibility(GONE);
            }

        }

        /**
         * Method which provide the setting of the recieved data
         *
         * @param users  users
         * @param groups groups
         */
        @Override
        public void setReceivedData(@NonNull final List<User> users,
                                    @NonNull final List<MMXUserGroup> groups) {
            previousLetter = null;
            //Disable data recieving
            if ((users.size() == 0) && (groups.size() == 0)) {
                isNeedDataRecieving = false;
            }
            //Post processing
            for (final MMXUserGroup group : groups) {
                if (!groupList.contains(group)) {
                    groupList.add(group);
                }
            }
            for (final User user : users) {
                if (!userList.contains(user)) {
                    userList.add(user);
                }
            }
            setListCells(userList, groupList);
        }

        /**
         * Method which provide the setting of the list cells
         *
         * @param users  users for cells
         * @param groups groups for cells
         */
        @Override
        public void setListCells(@NonNull List<User> users,
                                 @NonNull List<MMXUserGroup> groups) {
            objects.clear();
            if (presenter.isNeedGroups() == true) {
                objects.addAll(getGroupCells(groups));
            }
            objects.addAll(getUserCells(users));
            allAdapteredView.set(objects);
        }

        /**
         * Method which provide the getting of the {@link MMXUserGroup} cells
         *
         * @param groups groups list
         * @return MMXUserGroup cells
         */
        @NonNull
        public List<BaseObject> getGroupCells(@NonNull final List<MMXUserGroup> groups) {
            List<BaseObject> cells = new ArrayList<>();
            cells.add(groupHeader);
            if (groups.size() > 0) {
                for (MMXUserGroup userGroup : groups) {
                    cells.add(new RecycleGroup(presenter.getGroupLayout(),
                            userGroup));
                }
            } else {
                cells.add(noGroupsCell);
            }
            return cells;
        }

        /**
         * Method which provide the getting of the {@link User} cells
         *
         * @param users users list
         * @return user cells
         */
        @NonNull
        public List<BaseObject> getUserCells(@NonNull final List<User> users) {
            final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod = presenter.getUpdateAvatarMethod();
            List<BaseObject> cells = new ArrayList<>();
            for (User user : userList) {
                final String userID = user.getUserIdentifier();
                final String lastName = user.getLastName();
                String userName = user.getUserName();
                boolean isContainIgnored = false;

                if (userName == null) {
                    userName = "";
                }

                //Check if user contain in ignored
                isContainIgnored = ignoredUsers.contains(userName) || ignoredUsers.contains(userID);

                //Add last name letter divider
                if ((lastName != null)
                        && (lastName.isEmpty() == false)
                        && (isContainIgnored == false)) {
                    final String letter = lastName.substring(0, 1);
                    if ((previousLetter == null) ||
                            (previousLetter.equalsIgnoreCase(letter) == false)) {
                        previousLetter = letter;
                        cells.add(new RecycleAlphabetDivider(presenter.getAlphabeticDividerLayout(),
                                letter));
                    }
                }
                //Added objects selected/unselected and ignored server user
                if (isContainIgnored == false) {
                    if ((userID != null) && (selectedUserMap.containsKey(userID) == true)) {
                        cells.add(new RecycleUser(presenter.getUserLayout(),
                                user, true, avatarMethod, presenter.isNeedBlockedIcon()));
                    } else {
                        cells.add(new RecycleUser(presenter.getUserLayout(),
                                user, avatarMethod, presenter.isNeedBlockedIcon()));
                    }
                }
            }
            return cells;
        }

        /**
         * Method which provide the progress showing
         */
        @Override
        public void showProgress() {
            allAdapteredView.showRefresh();
        }

        /**
         * Method which provide the progress hiding
         */
        @Override
        public void hideProgress() {
            allAdapteredView.hideRefresh();

        }

        /**
         * Method which provide the search customization
         *
         * @param textColor text color for search
         * @param hintColor text color for hint
         * @param textSize  text size for hint and search
         */
        @Override
        public void onSearchCustomize(@ColorRes int textColor,
                                      @ColorRes int hintColor,
                                      @DimenRes int textSize) {
            if (searchView != null) {
                float size = getResources().getDimension(textSize);
                final View searchEditView = searchView
                        .findViewById(android.support.v7.appcompat.R.id.search_src_text);
                if (searchEditView instanceof EditText) {
                    EditText searchEditText = (EditText) searchEditView;
                    searchEditText.setTextColor(getResources().getColor(textColor));
                    searchEditText.setHintTextColor(getResources().getColor(hintColor));
                    searchEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                }
            }
        }

        /**
         * Method which provide the initializing of the ignored users
         */
        @Override
        public void onInitIgnoreUsers() {
            final User currentUser = User.getCurrentUser();
            if (currentUser != null) {
                final String currentUserName = User.getCurrentUser().getUserName();
                if ((currentUserName != null)
                        && (currentUserName.isEmpty() == false)) {
                    ignoredUsers.add(currentUserName);
                }
            }
            ignoredUsers.add(K_IGNORED_USER);
        }

        /**
         * Method which provide the action with on create view
         */
        @Override
        public void onCreateView() {
            //Init ignored users
            onInitIgnoreUsers();
            selectedAdapteredList.setVisibility(GONE);
            //Add initial data
            if (presenter.isNeedGroups() == true) {
                objects.add(groupHeader);
                objects.add(noGroupsCell);
            } else {
                presenter.onReceiveData(0, searchQuery);
            }
            allAdapteredView.set(objects);
            allAdapteredView.setRefreshColoursRes(presenter.getRefreshBackgroundColor(),
                    presenter.getRefreshColor());
        }

        /**
         * Method which provide the getting of the current context
         *
         * @return current {@link Context}
         */
        @NonNull
        @Override
        public Context getCurrentContext() {
            return getContext();
        }
    };

    /**
     * Method which provide the reload cells
     *
     * @see AbsGroupUserView#addIgnoringUsers(Collection)
     */
    protected final void reloadCells() {
        if (ValidationHelper.nullValidate(view, presenter)) {
            view.setListCells(userList, groupList);
        }
    }
}
