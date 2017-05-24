package com.artlite.ckconcept.mvp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.helpers.log.BSLogHelper;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.mvp.abs.presenter.KitBaseWidgetPresenter;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.artlite.ckconcept.ui.views.users.KitUsersView;
import com.magnet.max.android.User;
import com.magnet.max.android.api.group.MMXUserGroup;
import com.magnet.max.android.callbacks.MaxCoreActionCallback;
import com.magnet.max.android.helpers.api.user.group.MaxCoreUserGroupHelper;
import com.magnet.max.android.rest.options.CacheOptions;
import com.magnet.mmx.client.helpers.user.MMXUserHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlernatovich on 5/24/2017.
 */

public class KitUsersPresenter extends KitBaseWidgetPresenter {

    /**
     * Default cache time
     */
    private static int K_DEFAULT_CACHE_TIME = 60;

    /**
     * {@link List} of the {@link MMXUserGroup}
     */
    private final List<MMXUserGroup> groups = new ArrayList<>();

    /**
     * {@link List} of the {@link User}
     */
    private final List<User> users = new ArrayList<>();

    /**
     * Method which provide the create of the {@link KitUsersPresenter} from instance of the
     * {@link KitWidgetContract.Presenter}
     *
     * @param view instance of the {@link KitWidgetContract.Presenter}
     */
    public KitUsersPresenter(KitWidgetContract.View view) {
        super(view);
    }

    /**
     * Method which provide the getting of the server data
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     * @return
     */
    @Nullable
    @Override
    public void getServerData(@NonNull Context context,
                              int offset,
                              @Nullable OnKitActionCallback callback) {
        startDataReceiving(context, offset, callback);
    }

    /**
     * Methd which provide the getting of the {@link View} class
     *
     * @return instance of the {@link View} class
     */
    @NonNull
    @Override
    public Class getViewClass() {
        return KitUsersView.class;
    }

    /**
     * Method which provide the starting of the data receiving
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     */
    protected void startDataReceiving(@NonNull Context context,
                                      int offset,
                                      @Nullable OnKitActionCallback callback) {
        clearData(offset);
        receiveGroups(context, offset, getSearchQuery(), callback);
    }

    /**
     * Method which provide the receiving of the {@link List} of the {@link MMXUserGroup}
     *
     * @param context  instance of {@link Context}
     * @param offset   {@link Integer} value of the offset
     * @param callback instance of {@link OnKitActionCallback}
     */
    protected void receiveGroups(@NonNull final Context context,
                                 final int offset,
                                 @Nullable final String searchQuery,
                                 @Nullable final OnKitActionCallback callback) {
        //If offset != 0 add the than add the objects from cache
        if (offset != 0) {
            receiveUsers(context, offset, searchQuery, callback);
            return;
        }

        //If offset != 0 add the than add the objects from server
        MMXUserGroup.getAllGroups(MaxCoreUserGroupHelper.GroupType.MY,
                new MaxCoreActionCallback<List<MMXUserGroup>>() {
                    @Override
                    public void onResult(boolean isSuccess,
                                         Throwable error,
                                         final List<MMXUserGroup> result) {
                        BSThreadManager.execute(new BSThreadManager.OnExecutionCallback() {
                            @Override
                            public void onBackground() {
                                groups.addAll(result);
                            }

                            @Override
                            public void onMain() {
                                receiveUsers(context, offset, searchQuery, callback);
                            }
                        });
                    }
                });

    }

    /**
     * Method which provide the getting of the groups
     *
     * @param offset      offset
     * @param searchQuery search query
     */
    protected void receiveUsers(@NonNull final Context context,
                                final int offset,
                                @Nullable final String searchQuery,
                                @Nullable final OnKitActionCallback callback) {
        MMXUserHelper.getAllUsers(offset, searchQuery, getCacheOptions(),
                new MaxCoreActionCallback<List<User>>() {
                    @Override
                    public void onResult(boolean isSuccess,
                                         @Nullable Throwable error,
                                         @Nullable final List<User> result) {
                        BSThreadManager.background(new BSThreadManager.OnThreadCallback() {
                            @Override
                            public void onExecute() {
                                users.addAll(result);
                                receiveCells(context, offset, callback);
                            }
                        });
                    }
                });
    }

    /**
     * Method which provide the cells receiving
     *
     * @param callback instance of {@link OnKitActionCallback}
     */
    protected void receiveCells(@NonNull final Context context,
                                final int offset,
                                @Nullable final OnKitActionCallback callback) {
        final List<BaseObject> objects = new ArrayList<>();
        BSThreadManager.execute(new BSThreadManager.OnExecutionCallback() {
            @Override
            public void onBackground() {
                objects.addAll(getGroupCells(groups));
                objects.addAll(getUserCells(users));
            }

            @Override
            public void onMain() {
                if (callback != null) {
                    callback.onDataReceived(context, offset, objects);
                }
            }
        });

    }

    /**
     * Method which provide the clear group
     *
     * @param index {@link Integer} value of the index
     */
    protected void clearData(int index) {
        if (index == 0) {
            groups.clear();
            users.clear();
        }
    }

    /**
     * Method which provide the getting of the {@link CacheOptions}
     *
     * @return instance of {@link CacheOptions}
     */
    @NonNull
    public CacheOptions getCacheOptions() {
        return new CacheOptions
                .Builder()
                .alwaysUseCacheIfOffline(true)
                .maxCacheAge(K_DEFAULT_CACHE_TIME)
                .build();
    }

    //==============================================================================================
    //                                    GET LIST OBJECTS
    //==============================================================================================

    /**
     * Method which provide the getting of the {@link List} of the {@link BaseObject} from the
     * {@link List} of the {@link MMXUserGroup}
     *
     * @return {@link List} of the {@link BaseObject}
     */
    @NonNull
    protected List<BaseObject> getGroupCells(List<MMXUserGroup> groups) {
        final List<BaseObject> objects = new ArrayList<>();
        if (groups != null) {
            for (MMXUserGroup group : groups) {
                final BaseObject object = getObject(getType(group), group);
                if (object != null) {
                    objects.add(object);
                }
            }
        }
        return objects;
    }

    /**
     * Method which provide the getting of the {@link List} of the {@link BaseObject} from the
     * {@link List} of the {@link MMXUserGroup}
     *
     * @return {@link List} of the {@link BaseObject}
     */
    @NonNull
    protected List<BaseObject> getUserCells(List<User> users) {
        final List<BaseObject> objects = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                final BaseObject object = getObject(getType(user), user);
                if (object != null) {
                    objects.add(object);
                }
            }
        }
        return objects;
    }

    //==============================================================================================
    //                                      SEARCH QUERY
    //==============================================================================================

    /**
     * Method which provide the getting of the {@link String} value of the search query
     *
     * @return {@link String} value of the search query
     */
    @Nullable
    protected String getSearchQuery() {
        try {
            return ((KitUsersView) getView()).getSearchQuery();
        } catch (Exception ex) {
            BSLogHelper.log(this, "String getSearchQuery", ex, null);
            return null;
        }
    }


}
