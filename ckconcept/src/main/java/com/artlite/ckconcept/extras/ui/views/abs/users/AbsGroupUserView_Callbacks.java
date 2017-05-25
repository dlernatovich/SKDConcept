package com.artlite.ckconcept.extras.ui.views.abs.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;

import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredBaseCallback;
import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredPagingCallback;
import com.artlite.adapteredrecyclerview.callbacks.OnAdapteredRefreshCallback;
import com.artlite.adapteredrecyclerview.events.RecycleEvent;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleGroup;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleGroupHeader;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleUser;
import com.magnet.max.android.User;
import com.magnet.max.android.api.group.MMXUserGroup;

/**
 * Created by Artli on 15.12.2016.
 */

abstract class AbsGroupUserView_Callbacks extends AbsGroupUserView_Variables {

    public AbsGroupUserView_Callbacks(Context context) {
        super(context);
    }

    public AbsGroupUserView_Callbacks(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsGroupUserView_Callbacks(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //========================================================================================
    //                                      CALLBACKS
    //========================================================================================

    /**
     * Callback which provide the manage clicks for all object list
     */
    protected final OnAdapteredBaseCallback allObjectsCallback = new OnAdapteredBaseCallback() {
        @Override
        public void onItemClick(int i, @NonNull BaseObject baseObject) {
            //===================================================================
            //                          Manage user click
            //===================================================================
            if (baseObject instanceof RecycleUser) {
                onUserPressed((RecycleUser) baseObject);
                //================================================================
                //                          Manage group click
                //================================================================
            } else if (baseObject instanceof RecycleGroup) {
                final MMXUserGroup userGroup = ((RecycleGroup) baseObject).getGroup();
                if ((userGroup != null) && (manageGroupCallback != null)) {
                    manageGroupCallback.onGroupPressed(userGroup);
                }
            }

        }

        @Override
        public void onItemLongClick(int i, @NonNull BaseObject baseObject) {
            //===============================================================
            //                        User long click
            //===============================================================
            if (baseObject instanceof RecycleUser) {
                final User user = ((RecycleUser) baseObject).getUser();
                if ((user != null) && (onUserClickListener != null)) {
                    onUserClickListener.onUserLongClick(user);
                }
            }
        }

        @Override
        public void onActionReceived(@NonNull RecycleEvent recycleEvent,
                                     int i, @NonNull BaseObject baseObject) {
            //===================================================================
            //                          Manage group click
            //===================================================================
            if (recycleEvent.equals(RecycleGroupHeader.K_MANAGE_EVENT) == true) {
                if (manageGroupCallback != null) {
                    manageGroupCallback.onManageGroupsPressed();
                }
            }
        }
    };

    /**
     * Callback which provide the paging callback for all object list
     */
    protected final OnAdapteredPagingCallback allPagingCallback = new OnAdapteredPagingCallback() {
        @Override
        public void onNextPage(int i) {
            if (isNeedDataRecieving == true) {
                presenter.onReceiveData(userList.size(), searchQuery);
            }
        }
    };

    /**
     * Callback which provide the refresh functional
     */
    protected final OnAdapteredRefreshCallback allRefreshCallback = new OnAdapteredRefreshCallback() {
        @Override
        public void onRefreshData() {
            groupList.clear();
            userList.clear();
            isNeedDataRecieving = true;
            presenter.onReceiveData(0, searchQuery);
        }
    };

    //========================================================================================
    //                                     INNER CALLBACK
    //========================================================================================

    /**
     * Callback which provide the listening of the manage groups callback
     */
    public interface OnManageGroupCallback {
        /**
         * Method which provide the action when user press the manage group button
         */
        void onManageGroupsPressed();

        /**
         * Method which provide the action when user press on the {@link MMXUserGroup}
         *
         * @param group group which pressed
         */
        void onGroupPressed(@NonNull final MMXUserGroup group);
    }

    /**
     * Method which provide the setting of the {@link OnManageGroupCallback}
     *
     * @param manageGroupCallback callback for set
     */
    public void setManageGroupCallback(OnManageGroupCallback manageGroupCallback) {
        this.manageGroupCallback = manageGroupCallback;
    }

    /**
     * Method which provide the setting of the user click listener
     *
     * @param onUserClickListener user click listener
     */
    public void setUserClickListener(@Nullable final GroupContactContract.OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    //========================================================================================
    //                                     SEARCH CALLBACKS
    //========================================================================================

    /**
     * Class which provide the listening when user close search
     */
    protected final SearchView.OnCloseListener searchCloseListener = new SearchView.OnCloseListener() {
        @Override
        public boolean onClose() {
            searchQuery = null;
            if (allRefreshCallback != null) {
                allRefreshCallback.onRefreshData();
            }
            return false;
        }
    };

    /**
     * Class which provide listening when user press search button
     */
    protected final SearchView.OnQueryTextListener searchSubmitListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchQuery = query;
            if (allRefreshCallback != null) {
                allRefreshCallback.onRefreshData();
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    /**
     * Method which provide the action when user pressed
     *
     * @param recycleUser {@link RecycleUser} object
     */
    protected abstract void onUserPressed(@Nullable final RecycleUser recycleUser);

}
