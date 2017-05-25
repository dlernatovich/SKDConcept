package com.artlite.ckconcept.extras.ui.recycle.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.ckconcept.R;

/**
 * Created by Artli on 11.10.2016.
 */

public class RecycleAlphabetDivider extends BaseObject {

    private final int layoutID;
    private final String letter;

    public RecycleAlphabetDivider(int layoutID, @NonNull final String letter) {
        this.layoutID = layoutID;
        this.letter = letter;
    }

    @Override
    public BaseRecyclerItem getRecyclerItem(@NonNull Context context) {
        return new RecycleIemView(context);
    }

    /**
     * View class
     */
    private final class RecycleIemView extends BaseRecyclerItem<RecycleAlphabetDivider> {

        private AppCompatTextView labelText;

        public RecycleIemView(Context context) {
            super(context);
        }

        @Override
        public void setUp(@NonNull RecycleAlphabetDivider recycleAlphabetDivider) {
            labelText.setText(recycleAlphabetDivider.letter);
        }

        @Override
        protected int getLayoutId() {
            return layoutID;
        }

        @Override
        protected void onLinkInterface() {
            labelText = (AppCompatTextView) findViewById(R.id.label_text);
        }

        @Override
        protected void onCreateView() {

        }
    }
}
