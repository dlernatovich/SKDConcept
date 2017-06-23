package com.artlite.ckconcept.ui.abs.users;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.ui.view.BSSearchView;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.mvp.contracts.KitGroupContactContract;
import com.artlite.ckconcept.ui.recycle.users.KitRecycleGroupHeader;
import com.artlite.ckconcept.ui.recycle.users.KitRecycleNoGroups;
import com.magnet.max.android.User;
import com.magnet.max.android.api.group.MMXUserGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Artli on 15.12.2016.
 */

abstract class KitBaseGroupUserView_Variables extends BSView {

    //UI
    protected AdapteredView selectedAdapteredList;
    protected AdapteredView allAdapteredView;
    //All cells
    protected final List<BaseObject> objects = new ArrayList<>();
    //Presenter
    protected KitGroupContactContract.Presenter presenter;
    //User group header
    protected KitRecycleGroupHeader groupHeader;
    protected KitRecycleNoGroups noGroupsCell;
    //Data for create of the list
    protected final Map<String, User> selectedUserMap = new HashMap<>();
    protected final List<User> userList = new ArrayList<>();
    protected final List<MMXUserGroup> groupList = new ArrayList<>();
    //Paging variables
    protected boolean isNeedDataRecieving = true;
    //Group management callback
    protected KitBaseGroupUserView.OnManageGroupCallback manageGroupCallback;
    //Search
    protected BSSearchView searchView;
    protected String searchQuery;
    //Letter divider functional variable
    protected String previousLetter;
    //Ignored users
    protected final Set<String> ignoredUsers = new HashSet<>();
    //User long click callback
    protected KitGroupContactContract.OnUserClickListener onUserClickListener;

    public KitBaseGroupUserView_Variables(Context context) {
        super(context);
    }

    public KitBaseGroupUserView_Variables(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KitBaseGroupUserView_Variables(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
