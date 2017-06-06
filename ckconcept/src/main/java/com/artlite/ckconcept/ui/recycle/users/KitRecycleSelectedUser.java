package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
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
 * Class which provide the recycle representation for the no groups item
 */

public class KitRecycleSelectedUser extends BaseObject {

    /**
     * {@link Integer} value of the layout Id
     */
    private final int layoutID;

    /**
     * Instance of the {@link WeakReference} of the {@link User}
     */
    private final WeakReference<User> user;

    /**
     * Instance of the {@link MaxCoreUserHelper.UpdateAvatarMethod}
     */
    private final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod;

    /**
     * Constructor which provide the creating object with layout id and user object
     *
     * @param layoutID layout ID
     * @param user     user object
     */
    public KitRecycleSelectedUser(int layoutID,
                                  @NonNull final User user,
                                  @NonNull final MaxCoreUserHelper.UpdateAvatarMethod method) {
        this.layoutID = layoutID;
        this.user = new WeakReference<User>(user);
        this.avatarMethod = method;
    }

    /**
     * Constructor which provide the create the {@link KitRecycleSelectedUser} from the instance
     * of the {@link Parcel}
     *
     * @param parcel instance of the {@link Parcel}
     */
    protected KitRecycleSelectedUser(Parcel parcel) {
        super(parcel);
        this.layoutID = parcel.readInt();
        final User user = parcel.readParcelable(User.class.getClassLoader());
        if (user != null) {
            this.user = new WeakReference<User>(user);
        } else {
            this.user = null;
        }
        int tmpAvatarMethod = parcel.readInt();
        this.avatarMethod = (tmpAvatarMethod == -1)
                ? MaxCoreUserHelper.UpdateAvatarMethod.ALL
                : MaxCoreUserHelper.UpdateAvatarMethod.values()[tmpAvatarMethod];
    }

    /**
     * Method which provide the getting of the {@link BaseRecyclerItem}
     * for the {@link KitRecycleSelectedUser}
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleItemView(context);
    }

    /**
     * Method which provide to getting of the instance of the {@link User}
     *
     * @return instance of the {@link User}
     */
    @Nullable
    protected User getUser() {
        return (user != null)
                ? user.get() : null;
    }

    /**
     * Method which provide the writing of the {@link KitRecycleSelectedUser} to parcel
     *
     * @param parcel instance of the {@link Parcel}
     * @param flags  {@link Integer} value of the flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeInt(this.layoutID);
        parcel.writeParcelable(getUser(), flags);
        parcel.writeInt(this.avatarMethod == null ? -1 : this.avatarMethod.ordinal());
    }

    /**
     * Instance of the {@link Creator}
     */
    public static final Creator<KitRecycleSelectedUser> CREATOR = new Creator<KitRecycleSelectedUser>() {
        @Override
        public KitRecycleSelectedUser createFromParcel(Parcel source) {
            return new KitRecycleSelectedUser(source);
        }

        @Override
        public KitRecycleSelectedUser[] newArray(int size) {
            return new KitRecycleSelectedUser[size];
        }
    };

    /**
     * Class which provide the recycle logic for the {@link KitRecycleSelectedUser}
     */
    private final class RecycleItemView extends BaseRecyclerItem<KitRecycleSelectedUser> {

        /**
         * Instance of the {@link TextView}
         */
        private TextView avatar;

        /**
         * Instance of the {@link TextView}
         */
        private TextView name;

        /**
         * Instance of the {@link CircleImageView}
         */
        private CircleImageView circleImageView;

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
         * @param selectedUser instance of the {@link KitRecycleSelectedUser}
         */
        @Override
        public void setUp(@NonNull KitRecycleSelectedUser selectedUser) {
            final User user = selectedUser.user.get();
            String firstName = "Not";
            String lastName = "Available";
            String url = null;
            if (user != null) {
                if (user.getFirstName() != null) {
                    firstName = user.getFirstName();
                }
                if (user.getLastName() != null) {
                    lastName = user.getLastName();
                }
                url = user.generateAvatarUrl(user, avatarMethod);
            }
            name.setText(Html.fromHtml(String.format("%s<br/><b>%s</b>", firstName, lastName)));
            avatar.setText(KitUserHelper.getShortName(String.format("%s %s", firstName, lastName)));

            if (url != null) {
                Glide.with(getContext())
                        .load(url)
                        .centerCrop()
                        .fitCenter()
                        .listener(glideCallback)
                        .into(circleImageView);
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
            avatar = (TextView) findViewById(R.id.label_short_name);
            name = (TextView) findViewById(R.id.label_user_name);
            circleImageView = (CircleImageView) findViewById(R.id.image_avatar);
        }

        /**
         * Method which provide the action for create view action
         */
        @Override
        protected void onCreateView() {
        }

        /**
         * Callback which provide to loading of the image into the channel image view
         */
        private final RequestListener<String, GlideDrawable> glideCallback =
                new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        if (e != null) {
                            Log.e("ChannelRecyclerItem", e.toString());
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource,
                                                   String model,
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
