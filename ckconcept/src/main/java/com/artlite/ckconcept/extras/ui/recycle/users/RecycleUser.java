package com.artlite.ckconcept.extras.ui.recycle.users;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.extras.helpers.UserNameHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.magnet.max.android.User;
import com.magnet.max.android.helpers.api.user.main.MaxCoreUserHelper;

import java.lang.ref.WeakReference;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dlernatovich on 10/10/16.
 */

public class RecycleUser extends BaseObject {
    @LayoutRes
    private final int layoutID;
    private final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod;
    private final WeakReference<User> user;
    private final boolean isNeedBlockedIcon;
    private boolean isSelected = false;

    /**
     * Constructor which provide the creating user with show of the blocking icon
     *
     * @param layoutID     layout id
     * @param user         user
     * @param avatarMethod update avatar method
     */
    public RecycleUser(@LayoutRes int layoutID,
                       @NonNull final User user,
                       @NonNull final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod) {
        this(layoutID, user, false, avatarMethod, true);
    }

    /**
     * Constructor which provide the creating user with defining of the blocking icon
     *
     * @param layoutID          layout id
     * @param user              user
     * @param avatarMethod      update avatar method
     * @param isNeedBlockedIcon is need blocking icon
     */
    public RecycleUser(@LayoutRes int layoutID,
                       @NonNull final User user,
                       @NonNull final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod,
                       boolean isNeedBlockedIcon) {
        this(layoutID, user, false, avatarMethod, isNeedBlockedIcon);
    }

    /**
     * Constructor which provide the creating user with defining
     * of the blocking icon and selecting and selecting user
     *
     * @param layoutID          layout ID
     * @param user              user
     * @param isSelected        is selected
     * @param avatarMethod      avatar update method
     * @param isNeedBlockedIcon is need blocked icon
     */
    public RecycleUser(@LayoutRes int layoutID,
                       @NonNull final User user,
                       boolean isSelected,
                       @NonNull final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod,
                       boolean isNeedBlockedIcon) {
        this.layoutID = layoutID;
        this.user = new WeakReference<User>(user);
        this.isSelected = isSelected;
        this.avatarMethod = avatarMethod;
        this.isNeedBlockedIcon = isNeedBlockedIcon;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new ItemView(context);
    }

    /**
     * Method which provide the user getting
     *
     * @return user for return
     */
    @Nullable
    public User getUser() {
        if (user != null) {
            return user.get();
        }
        return null;
    }

    /**
     * Method which provide the checking if user is blocked
     *
     * @return if user blocked
     */
    protected boolean isUserBlocked() {
        if (!isNeedBlockedIcon) {
            return false;
        }
        final User user = getUser();
        return (user != null) ? user.isBlocked() : false;
    }

    /**
     * Method which provide the selecting cell value
     *
     * @return is selected cell
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * View class
     */
    private final class ItemView extends BaseRecyclerItem<RecycleUser> {

        private View container;
        private View selectedContainer;
        private AppCompatTextView avatar;
        private AppCompatTextView labelUseName;
        private CircleImageView circleImageView;
        private ImageView imageBlocked;

        public ItemView(Context context) {
            super(context);
        }

        @Override
        public void setUp(@NonNull RecycleUser recycleUser) {
            final User user = recycleUser.user.get();
            String fistrName = "Not";
            String lastName = "Available";
            String url = null;
            setUserSelected(recycleUser.isSelected);
            if (user != null) {
                if (user.getFirstName() != null) {
                    fistrName = user.getFirstName();
                }
                if (user.getLastName() != null) {
                    lastName = user.getLastName();
                }
                url = user.generateAvatarUrl(user, avatarMethod);
            }
            String userName = String.format("%s <b>%s</b>", fistrName, lastName);
            labelUseName.setText(Html.fromHtml(userName));
            avatar.setText(UserNameHelper.getShortName(String.format("%s %s", fistrName, lastName)));

            if (url != null) {
                Glide.with(getContext())
                        .load(url)
                        .centerCrop()
                        .fitCenter()
                        .listener(glideCallback)
                        .into(circleImageView);
            }

            if (recycleUser.isUserBlocked()) {
                imageBlocked.setVisibility(VISIBLE);
            } else {
                imageBlocked.setVisibility(GONE);
            }

        }

        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        @Override
        protected void onLinkInterface() {
            container = findViewById(R.id.container);
            selectedContainer = findViewById(R.id.container_selected);
            avatar = (AppCompatTextView) findViewById(R.id.label_short_name);
            labelUseName = (AppCompatTextView) findViewById(R.id.label_user_name);
            circleImageView = (CircleImageView) findViewById(R.id.image_avatar);
            imageBlocked = (ImageView) findViewById(R.id.image_blocked);
        }

        @Override
        protected void onCreateView() {
        }

        /**
         * Method which provide the getting of the clicked ID
         *
         * @return clicked ID
         */
        @Override
        protected int getClickedID() {
            return R.id.container;
        }

        /**
         * Method which provide on item click action
         *
         * @param object object
         */
        @Override
        protected void onItemClick(@NonNull final RecycleUser object) {
            if (object != null) {
                object.isSelected = !object.isSelected;
                setUserSelected(object.isSelected);
            }
        }

        /**
         * Method which provide the setting of the selecting user
         *
         * @param isSelected is selected
         */
        private void setUserSelected(boolean isSelected) {
            if (isSelected == true) {
                selectedContainer.setVisibility(VISIBLE);
            } else {
                selectedContainer.setVisibility(GONE);
            }
        }

        /**
         * Callback which provide to loading of the image into the channel image view
         */
        private final RequestListener<String, GlideDrawable>
                glideCallback = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model,
                                       Target<GlideDrawable> target, boolean isFirstResource) {
                if (e != null) {
                    Log.e("ChannelRecyclerItem", e.toString());
                }
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model,
                                           Target<GlideDrawable> target,
                                           boolean isFromMemoryCache,
                                           boolean isFirstResource) {
                if (circleImageView != null && resource != null) {
                    circleImageView.setImageDrawable(resource);
                }
                return false;
            }
        };
    }
}
