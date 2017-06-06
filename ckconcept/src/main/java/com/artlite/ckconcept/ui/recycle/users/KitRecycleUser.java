package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.helpers.user.KitUserHelper;
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

public class KitRecycleUser extends BaseObject {

    /**
     * {@link Integer} value of the layout Id
     */
    @LayoutRes
    private final int layoutID;

    /**
     * Instance of the {@link MaxCoreUserHelper.UpdateAvatarMethod}
     */
    private final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod;

    /**
     * {@link WeakReference} of the {@link User}
     */
    private final WeakReference<User> user;

    /**
     * {@link Boolean} value if need blocked user icon
     */
    private final boolean isNeedBlockedIcon;

    /**
     * {@link Boolean} value if the {@link User} is selected
     */
    private boolean isSelected = false;

    /**
     * Constructor which provide the creating user with show of the blocking icon
     *
     * @param layoutID     layout id
     * @param user         user
     * @param avatarMethod update avatar method
     */
    public KitRecycleUser(@LayoutRes int layoutID,
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
    public KitRecycleUser(@LayoutRes int layoutID,
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
    public KitRecycleUser(@LayoutRes int layoutID,
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

    /**
     * Constructor which provide the create the {@link KitRecycleUser} from the instance
     * of the {@link Parcel}
     *
     * @param parcel instance of the {@link Parcel}
     */
    protected KitRecycleUser(Parcel parcel) {
        super(parcel);
        this.layoutID = parcel.readInt();
        int tmpAvatarMethod = parcel.readInt();
        this.avatarMethod = tmpAvatarMethod == -1
                ? MaxCoreUserHelper.UpdateAvatarMethod.ALL :
                MaxCoreUserHelper.UpdateAvatarMethod.values()[tmpAvatarMethod];
        final User user = parcel.readParcelable(User.class.getClassLoader());
        if (user != null) {
            this.user = new WeakReference<User>(user);
        } else {
            this.user = null;
        }
        this.isNeedBlockedIcon = parcel.readByte() != 0;
        this.isSelected = parcel.readByte() != 0;
    }

    /**
     * Method which provide the getting of the {@link BaseRecyclerItem}
     * for the {@link KitRecycleUser}
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleItemView(context);
    }

    /**
     * Method which provide the user getting
     *
     * @return instance of the {@link User}
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
     * Method which provide the writing of the {@link KitRecycleUser} to parcel
     *
     * @param parcel instance of the {@link Parcel}
     * @param flags  {@link Integer} value of the flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeInt(this.layoutID);
        parcel.writeInt(this.avatarMethod == null ? -1 : this.avatarMethod.ordinal());
        parcel.writeParcelable(getUser(), flags);
        parcel.writeByte(this.isNeedBlockedIcon ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    /**
     * Instance of the {@link Creator}
     */
    public static final Creator<KitRecycleUser> CREATOR = new Creator<KitRecycleUser>() {
        @Override
        public KitRecycleUser createFromParcel(Parcel source) {
            return new KitRecycleUser(source);
        }

        @Override
        public KitRecycleUser[] newArray(int size) {
            return new KitRecycleUser[size];
        }
    };

    /**
     * Class which provide the recycle logic for the {@link KitRecycleUser}
     */
    private final class RecycleItemView extends BaseRecyclerItem<KitRecycleUser> {

        /**
         * Instance of the {@link View}
         */
        private View container;

        /**
         * Instance of the selected {@link View}
         */
        private View selectedContainer;

        /**
         * Instance of the {@link TextView}
         */
        private TextView avatar;

        /**
         * Instance of the {@link TextView}
         */
        private TextView labelUseName;

        /**
         * Instance of the {@link CircleImageView}
         */
        private CircleImageView circleImageView;

        /**
         * Instance of the {@link ImageView}
         */
        private ImageView imageBlocked;

        /**
         * Constructor which provide the create {@link RecycleItemView} from the instance
         * of the {@link Context}
         *
         * @param context instance of {@link Context}
         */
        public RecycleItemView(Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up of the {@link RecycleItemView}
         *
         * @param recycleUser instance of the {@link KitRecycleUser}
         */
        @Override
        public void setUp(@NonNull KitRecycleUser recycleUser) {
            final User user = recycleUser.user.get();
            String firstName = "Not";
            String lastName = "Available";
            String url = null;
            setUserSelected(recycleUser.isSelected);
            if (user != null) {
                if (user.getFirstName() != null) {
                    firstName = user.getFirstName();
                }
                if (user.getLastName() != null) {
                    lastName = user.getLastName();
                }
                url = user.generateAvatarUrl(user, avatarMethod);
            }
            String userName = String.format("%s <b>%s</b>", firstName, lastName);
            labelUseName.setText(Html.fromHtml(userName));
            avatar.setText(KitUserHelper.getShortName(String.format("%s %s", firstName, lastName)));

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

        /**
         * Method which provide the {@link Integer} value of the layout Id for the
         * instance of the {@link RecycleItemView}
         *
         * @return {@link Integer} value of the layout Id
         */
        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        /**
         * Method which provide the interface linking
         */
        @Override
        protected void onLinkInterface() {
            container = findViewById(R.id.container);
            selectedContainer = findViewById(R.id.container_selected);
            avatar = (TextView) findViewById(R.id.label_short_name);
            labelUseName = (TextView) findViewById(R.id.label_user_name);
            circleImageView = (CircleImageView) findViewById(R.id.image_avatar);
            imageBlocked = (ImageView) findViewById(R.id.image_blocked);
        }

        /**
         * Method which provide the action for create view action
         */
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
        protected void onItemClick(@NonNull final KitRecycleUser object) {
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
