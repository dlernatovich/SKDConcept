package com.artlite.ckconcept.ui.views.universal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.R;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.mvp.abs.view.KitBaseWidgetView;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;

/**
 * View which provide the setting of the {@link KitBaseListObject} independently from the
 * presenter
 */

public final class KitUniversalWidgetView extends KitBaseWidgetView {

    /**
     * {@link Integer} value of the orientation
     */
    private int orientation;

    /**
     * Instance of {@link AdapteredView}
     */
    private AdapteredView recycleView;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public KitUniversalWidgetView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public KitUniversalWidgetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public KitUniversalWidgetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the layout ID
     *
     * @return {@link Integer} value of the layout ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_ck_universal;
    }

    /**
     * Method which provide the {@link AttributeSet} initialize
     *
     * @param attributes instance of the {@link AttributeSet}
     */
    @Override
    protected void onInitAttributes(@NonNull AttributeSet attributes) {
        TypedArray attr = getContext().getTheme().obtainStyledAttributes(attributes,
                R.styleable.KitUniversalWidgetView, 0, 0);
        this.orientation = attr.getInt(R.styleable.KitUniversalWidgetView_listOrientation, 0);
        attr.recycle();
    }

    /**
     * Method which provide the creating of the {@link View}
     */
    @Override
    protected void onCreateView() {
        this.recycleView = (AdapteredView) findViewById(R.id.recycle);
        super.onCreateView();
        hideProgress();
    }

    /**
     * Method which provide the getting of the {@link AdapteredView}
     *
     * @return instance of the {@link AdapteredView}
     */
    @NonNull
    @Override
    public AdapteredView getAdapteredView() {
        return recycleView;
    }

    /**
     * Method which provide the getting of the instance of the {@link KitWidgetContract.Presenter}
     *
     * @return instance of the {@link KitWidgetContract.Presenter}
     */
    @NonNull
    @Override
    public KitWidgetContract.Presenter getPresenter() {
        return null;
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the layout ID
     * for create widget list
     *
     * @return {@link Integer} value of the layout ID for create widget list
     * REQUIRED
     * @+id/image_menu as {@link ImageView}
     * @+id/label_header as {@link TextView}
     * @warning Keep null if you don't need to override the list items interface
     */
    @Nullable
    @Override
    public Integer getCreateWidgetLayout() {
        return null;
    }

    /**
     * Method which provide the getting of the {@link RecyclerView.LayoutManager}
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link RecyclerView.LayoutManager}
     */
    @NonNull
    @Override
    public RecyclerView.LayoutManager getLayoutManager(@NonNull Context context) {
        return new GridLayoutManager(getContext(), 1,
                (this.orientation == 0)
                        ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL,
                false);
    }

    /**
     * Method which provide the defining if {@link RecyclerView} need
     * of the swipe down to refresh
     *
     * @return if {@link RecyclerView} need of the swipe down to refresh
     */
    @Override
    public boolean isNeedSwipeRefresh() {
        return false;
    }

    /**
     * method which provide the creating of the {@link Integer} value of the create button Id
     *
     * @return {@link Integer} value of the create button Id
     */
    @Nullable
    @Override
    public Integer getCreateButtonId() {
        return null;
    }

    /**
     * Method which provide the getting of the getting of the {@link Class} for inheritance
     * objects
     *
     * @return instance of {@link Class}
     */
    @NonNull
    @Override
    public Class getCurrentClass() {
        return KitUniversalWidgetView.class;
    }

    /**
     * Method which provide the getting of the {@link Integer} value for dropdown {@link View}
     *
     * @return
     */
    @Nullable
    @Override
    public Integer getViewDropdown() {
        return null;
    }

    /**
     * Method which provide the performing of the error when the data received
     *
     * @param context instance of {@link Context}
     * @param offset  {@link Integer} value of the offset
     * @param error   instance of {@link Throwable}
     */
    @Override
    public void onServerError(@NonNull Context context, int offset, @NonNull Throwable error) {

    }
}
