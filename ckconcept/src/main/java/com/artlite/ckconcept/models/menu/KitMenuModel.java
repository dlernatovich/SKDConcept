package com.artlite.ckconcept.models.menu;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.anotations.FindViewBy;
import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;

/**
 * Class which provide the container for the menu item
 */

public final class KitMenuModel extends BaseObject {
    /**
     * {@link String} value of the text
     */
    private final String text;

    /**
     * Instance of {@link Class}
     */
    private final String ownerClass;

    /**
     * {@link String} value of the caller class name
     */
    private final String callerClassName;

    /**
     * {@link Boolean} value which provide to define if menu is need to show
     */
    private final boolean isNeedShow;

    /**
     * {@link Integer} value of the icon resource
     */
    @DrawableRes
    private final int icon;

    /**
     * {@link Integer} value of the layout resource
     */
    @LayoutRes
    private int layoutRes;


    /**
     * Constructor which provide to create the {@link KitMenuModel} from
     *
     * @param text   {@link String} value of the text
     * @param owner  instance of {@link Class}
     * @param caller instance of {@link Class}
     */
    public KitMenuModel(@Nullable final String text,
                        @DrawableRes final int icon,
                        @NonNull final Class owner,
                        @NonNull final Class caller) {
        this(text, icon, owner.getSimpleName(), caller.getSimpleName());
    }

    /**
     * Constructor which provide to create the {@link KitMenuModel} from
     *
     * @param text   {@link String} value of the text
     * @param owner  {@link String} value of the class simple name
     * @param caller {@link String} value of caller name
     */
    public KitMenuModel(@Nullable final String text,
                        @DrawableRes final int icon,
                        @NonNull final String owner,
                        @NonNull final String caller) {
        this.text = text;
        this.ownerClass = (owner == null) ? "" : owner;
        this.callerClassName = (caller == null) ? "" : caller;
        this.isNeedShow = (text != null);
        this.icon = icon;
        this.layoutRes = R.layout.reycle_ck_menu_item;
    }

    /**
     * Constructor which provide the create {@link KitMenuModel} from the instance of {@link Parcel}
     *
     * @param source instance of {@link Parcel}
     */
    public KitMenuModel(Parcel source) {
        super(source);
        this.text = source.readString();
        this.ownerClass = source.readString();
        this.callerClassName = source.readString();
        this.isNeedShow = (source.readInt() == 1);
        this.icon = source.readInt();
    }

    /**
     * Method which provide to getting of the {@link String} value of the text
     *
     * @return {@link String} value of the text
     */
    @Nullable
    public String getText() {
        return text;
    }

    /**
     * Method which provide to getting of the instance of the owner {@link Class}
     *
     * @return {@link String} value of the class simple name
     */
    @NonNull
    public String getOwnerClass() {
        return ownerClass;
    }

    /**
     * Method which provide the getting of the caller class name
     *
     * @return {@link String} value of the class simple name
     */
    @NonNull
    public String getCallerClass() {
        return callerClassName;
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
     * Method which provide the getting of the layout for the {@link RecycleView}
     *
     * @return {@link Integer} value of the layout ID
     * REQUIRED
     * @+id/image_menu as {@link android.widget.ImageView}
     * @+id/label_header as {@link android.widget.TextView}
     */
    @LayoutRes
    protected int getRecycleLayout() {
        return layoutRes;
    }

    /**
     * Method which provide the setting of the {@link Integer} value of the layout resource
     *
     * @param resource {@link Integer} value of the layout resource
     */
    public void setRecycleLayout(@LayoutRes int resource) {
        this.layoutRes = resource;
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the icon
     *
     * @return {@link Integer} value of the icon
     */
    @DrawableRes
    public int getIcon() {
        return icon;
    }

    //==============================================================================================
    //                                       PARCELABLE
    //==============================================================================================

    /**
     * Method which provide the write {@link BaseObject} to {@link Parcel}
     *
     * @param parcel instance of {@link Parcel}
     * @param flags  flags value
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeString(text);
        parcel.writeString(ownerClass);
        parcel.writeString(callerClassName);
        parcel.writeInt((isNeedShow == true) ? 1 : 0);
        parcel.writeInt(icon);
    }

    /**
     * Creator instance
     */
    public static final Parcelable.Creator<KitMenuModel> CREATOR =
            new Parcelable.Creator<KitMenuModel>() {
                @Override
                public KitMenuModel createFromParcel(Parcel source) {
                    return new KitMenuModel(source);
                }

                @Override
                public KitMenuModel[] newArray(int size) {
                    return new KitMenuModel[size];
                }
            };

    //==============================================================================================
    //                                      RECYCLE VIEW
    //==============================================================================================

    /**
     * Class which provide the interface for the {@link KitMenuModel}
     */
    private class RecycleView extends BaseRecyclerItem<KitMenuModel> {

        /**
         * Instance of {@link TextView}
         */
        private TextView labelHeader;

        /**
         * Instance of the {@link ImageView}
         */
        private ImageView imageMenu;

        /**
         * Default constructor
         *
         * @param context context to set
         */
        public RecycleView(@NonNull Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up for the current recycler item
         *
         * @param baseObject current object
         */
        @Override
        public void setUp(@NonNull KitMenuModel baseObject) {
            this.labelHeader.setText(baseObject.getText());
            this.imageMenu.setImageResource(baseObject.getIcon());
        }

        /**
         * Method which provide to getting of the layout ID
         *
         * @return layout ID
         */
        @Override
        protected int getLayoutId() {
            return getRecycleLayout();
        }

        /**
         * Method which provide the action when view will create
         */
        @Override
        protected void onCreateView() {

        }

        /**
         * Method which provide the getting of the clicked view ID
         *
         * @return clicked view ID
         */
        @Override
        protected int getClickedID() {
            return R.id.content;
        }

        /**
         * Method which provide the interface linking
         *
         * @warning it should be use only for library projects
         * @information for now added the annotation for link interface, use {@link FindViewBy} for
         * injecting view
         */
        @Override
        protected void onLinkInterface() {
            this.labelHeader = (TextView) findViewById(R.id.label_header);
            this.imageMenu = (ImageView) findViewById(R.id.image_menu);
        }
    }
}
