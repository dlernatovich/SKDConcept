package com.artlite.ckconcept.extras.mvp.impl.users;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;
import com.magnet.max.android.User;
import com.magnet.max.android.api.group.MMXUserGroup;
import com.magnet.max.android.callbacks.MaxCoreActionCallback;
import com.magnet.max.android.helpers.api.user.group.MaxCoreUserGroupHelper;
import com.magnet.max.android.rest.options.CacheOptions;
import com.magnet.mmx.client.helpers.user.MMXUserHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Artli on 11.10.2016.
 */

public abstract class AbstractGroupUserPresenter implements GroupContactContract.Presenter {

    protected final GroupContactContract.View view;

    /**
     * Constructor which provide the creating of the object from instance of the
     * {@link GroupContactContract.View}
     *
     * @param view instance of the View
     */
    public AbstractGroupUserPresenter(@NonNull final GroupContactContract.View view) {
        this.view = view;
        view.onSearchCustomize(getSearchTextColor(), getSearchHintColor(), getSearchTextSize());
    }

    /**
     * Method which provide the recieving of the server data
     *
     * @param offset      offset
     * @param searchQuery search query
     */
    @Override
    public void onReceiveData(final int offset, @Nullable final String searchQuery) {
        if (view != null) {
            view.showProgress();
        }
        final List<User> usersList = new ArrayList<>();
        //Get groups
        if ((offset == 0) && (searchQuery == null)) {
            onReceiveGroups(searchQuery);
        } else {
            onReceiveUsers(new ArrayList<MMXUserGroup>(), offset, searchQuery);
        }
    }

    /**
     * Method which provide the recieving of the groups
     *
     * @param searchQuery search query
     */
    @Override
    public void onReceiveGroups(@Nullable final String searchQuery) {
        final List<MMXUserGroup> groups = new ArrayList<>();
        MMXUserGroup.getAllGroups(getGroupType(), getCacheOptions(),
                new MaxCoreActionCallback<List<MMXUserGroup>>() {
                    @Override
                    public void onResult(boolean isSuccess,
                                         @Nullable Throwable error,
                                         @Nullable List<MMXUserGroup> result) {
                        if (result != null) {
                            groups.addAll(result);
                        }
                        onReceiveUsers(groups, 0, searchQuery);
                    }
                });
    }

    /**
     * Method which provide the getting of the groups
     *
     * @param groups      groups
     * @param offset      offset
     * @param searchQuery search query
     */
    @Override
    public void onReceiveUsers(@NonNull final List<MMXUserGroup> groups, int offset,
                               @Nullable final String searchQuery) {
        MMXUserHelper.getAllUsers(offset, searchQuery, getCacheOptions(),
                new MaxCoreActionCallback<List<User>>() {
                    @Override
                    public void onResult(boolean isSuccess,
                                         @Nullable Throwable error,
                                         @Nullable List<User> result) {
                        if (view != null) {
                            view.setReceivedData(result, groups);
                            view.hideProgress();
                        }
                    }
                });
    }

    /**
     * Method which provide the getting of the refresh color
     *
     * @return color resource
     */
    @ColorRes
    public int getRefreshColor() {
        return android.R.color.black;
    }

    /**
     * Method which provide the getting of the refresh background color
     *
     * @return refresh background color
     */
    @ColorRes
    public int getRefreshBackgroundColor() {
        return android.R.color.white;
    }

    /**
     * Method which provide the checking if need block user icon
     *
     * @return if is need of the block user icon
     */
    @Override
    public boolean isNeedBlockedIcon() {
        return true;
    }

    /**
     * Method which provide the getting of the
     * {@link com.magnet.max.android.helpers.api.user.group.MaxCoreUserGroupHelper_Constants.GroupType}
     *
     * @return group type
     */
    @NonNull
    public MaxCoreUserGroupHelper.GroupType getGroupType() {
        return MaxCoreUserGroupHelper.GroupType.MY;
    }

    /**
     * Method which provide the block user
     *
     * @param user user for block
     */
    @Override
    public void switchUserBlocking(@Nullable final User user,
                                   @Nullable final MaxCoreActionCallback<User> callback) {
        //Create callback
        final MaxCoreActionCallback<List<User>> helperCallback =
                new MaxCoreActionCallback<List<User>>() {
                    @Override
                    public void onResult(boolean isSuccess, Throwable error, List<User> result) {
                        User blockedUser = null;
                        if (isSuccess == true) {
                            if (result.size() > 0) {
                                blockedUser = result.get(0);
                            }
                        }
                        if (callback != null) {
                            callback.onResult(isSuccess, error, user);
                        }
                    }
                };

        //Do block/unblock performing
        if (user != null) {
            if (user.isBlocked()) {
                MMXUserHelper.unblockUsers(Arrays.asList(user), helperCallback);
            } else {
                MMXUserHelper.blockUsers(Arrays.asList(user), helperCallback);
            }
        }
    }

    /**
     * Method which provide the getting of the {@link CacheOptions}
     *
     * @return instance of {@link CacheOptions}
     */
    @NonNull
    @Override
    public CacheOptions getCacheOptions() {
        return new CacheOptions
                .Builder()
                .alwaysUseCacheIfOffline(true)
                .maxCacheAge(K_DEFAULT_CACHE_TIME)
                .build();
    }
}
