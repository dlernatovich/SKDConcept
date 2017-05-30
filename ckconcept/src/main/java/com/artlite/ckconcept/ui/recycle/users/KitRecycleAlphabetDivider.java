package com.artlite.ckconcept.ui.recycle.users;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;

/**
 * Class which provide the recycle representation of the alphabetic divider
 */
public class KitRecycleAlphabetDivider extends BaseObject {

    /**
     * {@link Integer} value of the layout ID
     */
    private final int layoutID;

    /**
     * {@link String} value of letter
     */
    private final String letter;

    /**
     * Constructor which provide the create {@link KitRecycleAlphabetDivider} from instance of the
     * {@link Context} and {@link String} value of the letter
     *
     * @param layoutID {@link Integer} value of the layout Id
     * @param letter   {@link String} value of the letter
     */
    public KitRecycleAlphabetDivider(int layoutID,
                                     @NonNull final String letter) {
        this.layoutID = layoutID;
        this.letter = letter;
    }

    /**
     * Constructor which provide the create the {@link KitRecycleAlphabetDivider} from the instance
     * of the {@link Parcel}
     *
     * @param parcel instance of the {@link Parcel}
     */
    protected KitRecycleAlphabetDivider(Parcel parcel) {
        super(parcel);
        this.layoutID = parcel.readInt();
        this.letter = parcel.readString();
    }

    /**
     * Method which provide the getting of the {@link BaseRecyclerItem} from the instance of the
     * {@link Context}
     *
     * @param context instance of the {@link Context}
     * @return instance of the {@link BaseRecyclerItem}
     */
    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleItemView(context);
    }


    /**
     * Method which provide the writing of the {@link KitRecycleAlphabetDivider} to parcel
     *
     * @param parcel instance of the {@link Parcel}
     * @param flags  {@link Integer} value of the flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeInt(this.layoutID);
        parcel.writeString(this.letter);
    }

    /**
     * Instance of the {@link Creator}
     */
    public static final Creator<KitRecycleAlphabetDivider> CREATOR =
            new Creator<KitRecycleAlphabetDivider>() {
                @Override
                public KitRecycleAlphabetDivider createFromParcel(Parcel source) {
                    return new KitRecycleAlphabetDivider(source);
                }

                @Override
                public KitRecycleAlphabetDivider[] newArray(int size) {
                    return new KitRecycleAlphabetDivider[size];
                }
            };


    /**
     * Class which provide the recycle representation for the {@link KitRecycleAlphabetDivider}
     */
    private final class RecycleItemView extends BaseRecyclerItem<KitRecycleAlphabetDivider> {

        /**
         * Instance of the {@link TextView}
         */
        private TextView labelText;

        /**
         * Constructor which provide the create of the {@link RecycleItemView} from the
         * instance of the {@link Context}
         *
         * @param context instance of the {@link Context}
         */
        public RecycleItemView(Context context) {
            super(context);
        }

        /**
         * Method which provide the setting up of the {@link RecycleItemView} from the instance
         * of the {@link KitRecycleAlphabetDivider}
         *
         * @param divider instance of the {@link KitRecycleAlphabetDivider}
         */
        @Override
        public void setUp(@NonNull KitRecycleAlphabetDivider divider) {
            labelText.setText(divider.letter);
        }

        /**
         * Method which provide the getting of the {@link Integer} value of the layout Id
         *
         * @return {@link Integer} value of the layout Id
         */
        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        /**
         * Method which provide the linking of the interface
         */
        @Override
        protected void onLinkInterface() {
            labelText = (TextView) findViewById(R.id.label_text);
        }

        /**
         * Method which provide the actions of the when view was create
         */
        @Override
        protected void onCreateView() {

        }
    }
}
