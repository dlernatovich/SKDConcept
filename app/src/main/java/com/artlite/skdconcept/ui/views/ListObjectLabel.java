package com.artlite.skdconcept.ui.views;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.bslibrary.ui.fonted.BSTextView;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.skdconcept.R;
import com.artlite.skdconcept.models.LabelObject;

/**
 * Created by dlernatovich on 14.06.2017.
 */

public final class ListObjectLabel extends KitBaseListObject<LabelObject> {

    /**
     * {@link String} value of text
     */
    private String text;

    /**
     * Constructor which provide the create the {@link KitBaseListObject} from the instance of the
     * {@link Object}
     *
     * @param object instance of the {@link Object}
     */
    public ListObjectLabel(Parcelable object) {
        super(object);
    }

    /**
     * Constructor which provide the create {@link KitBaseListObject} from {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public ListObjectLabel(Parcel source) {
        super(source);
    }

    /**
     * Method which provide the performing the action when the {@link Parcelable} was set
     *
     * @param object instance of the {@link Object}
     */
    @Override
    public void onPerformInitialize(@Nullable LabelObject object) {
        this.text = object.description;
    }

    /**
     * Method which provide the getting of the instance of the {@link ClassLoader}
     *
     * @return instance of the {@link ClassLoader}
     */
    @NonNull
    @Override
    protected ClassLoader getClassLoader() {
        return LabelObject.class.getClassLoader();
    }

    /**
     * Method which provide the getting of the {@link BaseRecyclerItem}
     *
     * @param context
     * @return
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleView(context);
    }

    /**
     * Instance of the {@link RecycleView}
     */
    private static class RecycleView extends BaseRecyclerItem<ListObjectLabel> {

        /**
         * Instance of the {@link BSTextView}
         */
        private BSTextView labelText;

        /**
         * Constructor which provide to create the {@link RecycleView} from the instance of the
         * {@link Context}
         *
         * @param context instance of the {@link Context}
         */
        public RecycleView(@NonNull Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up {@link RecycleView} fro the instance of the
         * {@link ListObjectLabel}
         *
         * @param object instance of the {@link ListObjectLabel}
         */
        @Override
        public void setUp(@NonNull ListObjectLabel object) {
            this.labelText.setText(object.text);
        }

        /**
         * Method which provide the getting of the {@link Integer} value of the layout Id
         *
         * @return {@link Integer} value of the layout Id
         */
        @Override
        protected int getLayoutId() {
            return R.layout.recycle_label_object;
        }

        /**
         * Method which provide the interface linking
         */
        @Override
        protected void onLinkInterface() {
            labelText = (BSTextView) findViewById(R.id.label_text);
        }

        /**
         * Method which provide the view creating functional
         */
        @Override
        protected void onCreateView() {

        }
    }
}
