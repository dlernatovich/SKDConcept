package com.artlite.ckconcept.widget;

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

public final class KitListNotSupport extends KitBaseListObject<Parcelable> {

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     */
    public KitListNotSupport() {
        super((Parcelable) null);
    }

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    public KitListNotSupport(Parcelable object) {
        super(object);
    }

    /**
     * Constructor which provide the create {@link KitBaseListObject} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitListNotSupport(Parcel source) {
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
     * Instance of {@link Parcelable.Creator}
     */
    public static final Parcelable.Creator<KitListNotSupport> CREATOR =
            new Parcelable.Creator<KitListNotSupport>() {
                @Override
                public KitListNotSupport createFromParcel(Parcel source) {
                    return new KitListNotSupport(source);
                }

                @Override
                public KitListNotSupport[] newArray(int size) {
                    return new KitListNotSupport[size];
                }
            };

    /**
     * Inner recycle view class
     */
    private static final class RecycleView extends BaseRecyclerItem<KitListNotSupport> {

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
        public void setUp(@NonNull KitListNotSupport support) {

        }

        /**
         * Method which provide to getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return R.layout.recycle_ck_not_support;
        }

        /**
         * Method which provide the action when view will create
         */
        @Override
        protected void onCreateView() {

        }
    }
}
