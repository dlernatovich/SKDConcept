package com.artlite.ckconcept.extras.ui.views.abs.users;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.ckconcept.extras.abs.BaseExtrasView;
import com.artlite.ckconcept.extras.mvp.contract.GroupContactContract;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleGroupHeader;
import com.artlite.ckconcept.extras.ui.recycle.users.RecycleNoGroups;
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

abstract class AbsGroupUserView_Variables extends BaseExtrasView {

    //UI
    protected AdapteredView selectedAdapteredList;
    protected AdapteredView allAdapteredView;
    //All cells
    protected final List<BaseObject> objects = new ArrayList<>();
    //Presenter
    protected GroupContactContract.Presenter presenter;
    //User group header
    protected RecycleGroupHeader groupHeader;
    protected RecycleNoGroups noGroupsCell;
    //Data for create of the list
    protected final Map<String, User> selectedUserMap = new HashMap<>();
    protected final List<User> userList = new ArrayList<>();
    protected final List<MMXUserGroup> groupList = new ArrayList<>();
    //Paging variables
    protected boolean isNeedDataRecieving = true;
    //Group management callback
    protected AbsGroupUserView.OnManageGroupCallback manageGroupCallback;
    //Search
    protected SearchView searchView;
    protected String searchQuery;
    //Letter divider functional variable
    protected String previousLetter;
    //Ignored users
    protected final Set<String> ignoredUsers = new HashSet<>();
    //User long click callback
    protected GroupContactContract.OnUserClickListener onUserClickListener;

    public AbsGroupUserView_Variables(Context context) {
        super(context);
    }

    public AbsGroupUserView_Variables(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsGroupUserView_Variables(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
