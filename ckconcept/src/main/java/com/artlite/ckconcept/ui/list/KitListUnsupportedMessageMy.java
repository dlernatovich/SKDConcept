package com.artlite.ckconcept.ui.list;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.models.list.KitBaseListObject;

/**
 * Created by dlernatovich on 6/2/2017.
 */

public final class KitListUnsupportedMessageMy extends KitBaseListObject<Parcelable> {

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     */
    public KitListUnsupportedMessageMy() {
        super((Parcelable) null);
    }

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    public KitListUnsupportedMessageMy(Parcelable object) {
        super(object);
    }

    /**
     * Constructor which provide the create {@link KitBaseListObject} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitListUnsupportedMessageMy(Parcel source) {
        super(source);
    }

    /**
     * Method which provide the getting of the instance of the {@link ClassLoader}
     *
     * @return instance of the {@link ClassLoader}
     */
    @NonNull
    @Override
    protected ClassLoader getClassLoader() {
        return Parcelable.class.getClassLoader();
    }

    /**
     * Method which provide the getting of the current recycler item
     *
     * @param context current context
     * @return current instance for the Recycler item
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleView(context);
    }

    /**
     * Instance of {@link Creator}
     */
    public static final Creator<KitListUnsupportedMessageMy> CREATOR =
            new Creator<KitListUnsupportedMessageMy>() {
                @Override
                public KitListUnsupportedMessageMy createFromParcel(Parcel source) {
                    return new KitListUnsupportedMessageMy(source);
                }

                @Override
                public KitListUnsupportedMessageMy[] newArray(int size) {
                    return new KitListUnsupportedMessageMy[size];
                }
            };

    /**
     * Inner recycle view class
     */
    private static final class RecycleView extends BaseRecyclerItem<KitListUnsupportedMessageMy> {

        /**
         * Default constructor
         *
         * @param context instance of {@link Context}
         */
        public RecycleView(@NonNull Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up for the current recycler item
         *
         * @param support current object
         */
        @Override
        public void setUp(@NonNull KitListUnsupportedMessageMy support) {

        }

        /**
         * Method which provide to getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return R.layout.recycle_ck_message_my_not_support;
        }

        /**
         * Method which provide the action when view will create
         */
        @Override
        protected void onCreateView() {

        }
    }
}
