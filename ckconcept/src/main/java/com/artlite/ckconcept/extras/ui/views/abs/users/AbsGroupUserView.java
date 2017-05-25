package com.artlite.ckconcept.extras.ui.views.abs.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleGroupHeader;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleNoGroups;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleUser;
import com.magnet.max.android.User;
import com.magnet.max.android.api.group.MMXUserGroup;
import com.magnet.max.android.callbacks.MaxCoreActionCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dlernatovich on 10/7/16.
 */

public abstract class AbsGroupUserView extends AbsGroupUserView_View {

    public AbsGroupUserView(Context context) {
        super(context);
    }

    public AbsGroupUserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsGroupUserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_extras_user_list;
    }

    @Override
    protected void onLinkInterface() {
        selectedAdapteredList = (AdapteredView) findViewById(R.id.selected_user_list);
        allAdapteredView = (AdapteredView) findViewById(R.id.all_user_list);
        searchView = (SearchView) findViewById(R.id.search_view);
    }

    @Override
    protected void onCreateView() {
        //Create presenter
        presenter = onCreatePresenter(view);
        //Create group header
        groupHeader = new RecycleGroupHeader(presenter.getGroupHeaderLayout());
        noGroupsCell = new RecycleNoGroups(presenter.getNoGroupsLayout());
        //Init data
        allAdapteredView.init(new GridLayoutManager(getContext(), 1),
                allObjectsCallback,
                allRefreshCallback,
                allPagingCallback);
        allAdapteredView.setIsNeedResfresh(true);
        selectedAdapteredList.init(new GridLayoutManager(getContext(),
                1, LinearLayoutManager.HORIZONTAL, false));
        //Search
        searchView.setOnCloseListener(searchCloseListener);
        searchView.setOnQueryTextListener(searchSubmitListener);
        //Call default view methods
        view.onCreateView();
    }

    /**
     * Method which provide the action when user pressed
     *
     * @param recycleUser {@link RecycleUser} object
     */
    @Override
    protected void onUserPressed(@NonNull RecycleUser recycleUser) {
        final User user = recycleUser.getUser();
        final boolean isSelected = recycleUser.isSelected();
        if (user != null) {
            final String userID = user.getUserIdentifier();
            if (userID != null) {
                if (isSelected == true) {
                    selectedUserMap.put(userID, user);
                } else {
                    if (selectedUserMap.containsKey(userID) == true) {
                        selectedUserMap.remove(userID);
                    }
                }
                if (view != null) {
                    view.setSelectedUsers(new ArrayList<User>(selectedUserMap.values()));
                }
            }
        }
    }

    /**
     * Method which provide the {@link User} blocking
     *
     * @param user user for block
     */
    public void switchUserBlocking(@Nullable final User user) {
        if ((presenter != null) && (view != null)) {
            presenter.switchUserBlocking(user, new MaxCoreActionCallback<User>() {
                @Override
                public void onResult(boolean isSuccess, Throwable error, User result) {
                    if ((result != null) &&
                            (userList != null) &&
                            (userList.contains(result))) {
                        synchronized (userList) {
                            int index = userList.indexOf(result);
                            userList.get(index).setBlocked(result.isBlocked());
                        }
                        view.setListCells(userList, groupList);
                    }
                }
            });
        }
    }

    /**
     * Method which provide the presenter creating
     *
     * @param view view for presenter
     * @return presenter
     */
    protected abstract GroupContactContract.Presenter onCreatePresenter(@NonNull final GroupContactContract.ViewClass view);

    //========================================================================================
    //                                     GET USERS METHODS
    //========================================================================================

    /**
     * Method which provide the getting of the selected users
     *
     * @return selected users
     */
    @NonNull
    public List<User> getSelectedUsersList() {
        List<User> users = new ArrayList<>();
        if (selectedUserMap != null) {
            if (selectedUserMap.values().isEmpty() == false) {
                users.addAll(new ArrayList<User>(selectedUserMap.values()));
            }
        }
        return users;
    }

    /**
     * Method which provide the getting of the selected users IDs
     *
     * @return selected user IDs
     */
    @NonNull
    public List<String> getSeletcedUserIDs() {
        List<String> userIDs = new ArrayList<>();
        final List<User> users = getSelectedUsersList();
        for (User user : users) {
            if (user.getUserIdentifier() != null) {
                userIDs.add(user.getUserIdentifier());
            }
        }
        return userIDs;
    }

    /**
     * Method which provide the getting of the users groups
     *
     * @return user groups
     */
    @NonNull
    public List<MMXUserGroup> getUserGroups() {
        List<MMXUserGroup> userGroups = new ArrayList<>();
        if (groupList != null) {
            userGroups.addAll(groupList);
        }
        return userGroups;
    }

    //========================================================================================
    //                                     PREVIOUS USERS
    //========================================================================================

    /**
     * Method which provide the previous selected users
     *
     * @param users users
     */
    public void setPreviousSelectedUsers(@Nullable final List<User> users) {
        //Check if users list is empty
        if (users == null) {
            return;
        }

        //Add users for previous selected users map
        for (User user : users) {
            final String userID = user.getUserIdentifier();
            if ((userID != null) && (userID.isEmpty() == false)) {
                selectedUserMap.put(userID, user);
            }
        }

        //Set previous selected users
        if (view != null) {
            view.setSelectedUsers(new ArrayList<User>(selectedUserMap.values()));
        }
    }

    //========================================================================================
    //                                     IGNORED USERS
    //========================================================================================

    /**
     * Method which provide the adding of the ignoring users
     *
     * @param users users for ignore
     */
    public void addIgnoringUsers(@Nullable Collection<User> users) {
        List<String> ids = new ArrayList<>();
        if (users != null) {
            for (final User user : users) {
                final String id = user.getUserIdentifier();
                if (!TextUtils.isEmpty(id)) {
                    ids.add(id);
                }
            }
        }
        addIgnoringUsers(ids);
    }

    /**
     * Method which provide the adding of the ignored users id
     *
     * @param ids ignored users ID
     */
    public void addIgnoringUsers(@Nullable final List<String> ids) {
        if (ids != null) {
            ignoredUsers.addAll(ids);
            reloadCells();
        }
    }
}
