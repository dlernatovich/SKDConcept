package com.artlite.ckconcept.extras.ui.recycle.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.util.Log;

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
 * Created by Artli on 11.10.2016.
 */

public class RecycleSelectedUser extends BaseObject {

    private final int layoutID;
    private final WeakReference<User> user;
    private final MaxCoreUserHelper.UpdateAvatarMethod avatarMethod;

    /**
     * Constructor which provide the creating object with layout id and user object
     *
     * @param layoutID layout ID
     * @param user     user object
     */
    public RecycleSelectedUser(int layoutID, @NonNull final User user,
                               MaxCoreUserHelper.UpdateAvatarMethod avatarMethod) {
        this.layoutID = layoutID;
        this.user = new WeakReference<User>(user);
        this.avatarMethod = avatarMethod;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleItemView(context);
    }

    private final class RecycleItemView extends BaseRecyclerItem<RecycleSelectedUser> {

        private AppCompatTextView avatar;
        private AppCompatTextView name;
        private CircleImageView circleImageView;

        public RecycleItemView(Context context) {
            super(context);
        }

        @Override
        public void setUp(@NonNull RecycleSelectedUser recycleSelectedUser) {
            final User user = recycleSelectedUser.user.get();
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
            avatar.setText(UserNameHelper.getShortName(String.format("%s %s", firstName, lastName)));

            if (url != null) {
                Glide.with(getContext())
                        .load(url)
                        .centerCrop()
                        .fitCenter()
                        .listener(glideCallback)
                        .into(circleImageView);
            }
        }

        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        @Override
        protected void onLinkInterface() {
            avatar = (AppCompatTextView) findViewById(R.id.label_short_name);
            name = (AppCompatTextView) findViewById(R.id.label_user_name);
            circleImageView = (CircleImageView) findViewById(R.id.image_avatar);
        }

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
