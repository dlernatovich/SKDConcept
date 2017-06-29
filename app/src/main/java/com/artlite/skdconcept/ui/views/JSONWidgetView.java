package com.artlite.skdconcept.ui.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artlite.adapteredrecyclerview.core.AdapteredView;
import com.artlite.bslibrary.annotations.FindViewBy;
import com.artlite.bslibrary.managers.BSThreadManager;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.callbacks.OnKitActionCallback;
import com.artlite.ckconcept.helpers.callback.KitCallbackHelper;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.mvp.abs.presenter.KitBaseWidgetPresenter;
import com.artlite.ckconcept.mvp.abs.view.KitBaseWidgetView;
import com.artlite.ckconcept.mvp.contracts.KitWidgetContract;
import com.artlite.skdconcept.R;
import com.artlite.skdconcept.models.LabelObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlernatovich on 29.06.2017.
 */

public final class JSONWidgetView extends KitBaseWidgetView {

    /**
     * Instance of the {@link AdapteredView}
     */
    @FindViewBy(id = R.id.recyclerView)
    private AdapteredView adapteredView;

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     */
    public JSONWidgetView(Context context) {
        super(context);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context instance of {@link Context}
     * @param attrs   instance of {@link AttributeSet}
     */
    public JSONWidgetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor which provide the create {@link BSView} from
     *
     * @param context      instance of {@link Context}
     * @param attrs        instance of {@link AttributeSet}
     * @param defStyleAttr attribute style
     */
    public JSONWidgetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Method which provide the getting of the layout id
     *
     * @return {@link Integer} value of the layout id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.view_json_widget;
    }

    /**
     * Method which provide the interface linking
     */
    @Override
    protected void onLinkInterface() {

    }

    /**
     * Method which provide the getting of the {@link AdapteredView}
     *
     * @return instance of the {@link AdapteredView}
     */
    @NonNull
    @Override
    public AdapteredView getAdapteredView() {
        return this.adapteredView;
    }

    /**
     * Method which provide the getting of the instance of the {@link Presenter}
     *
     * @return instance of the {@link Presenter}
     */
    @NonNull
    @Override
    public KitWidgetContract.Presenter getPresenter() {
        return new Presenter(this);
    }

    /**
     * Method which provide the getting of the {@link Integer} value of the layout ID
     * for create widget list
     *
     * @return {@link Integer} value of the layout ID for create widget list
     * REQUIRED
     * @+id/content as any {@link View} for click functional
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
        return new GridLayoutManager(context, 1);
    }

    /**
     * Method which provide the defining if {@link RecyclerView} need
     * of the swipe down to refresh
     *
     * @return if {@link RecyclerView} need of the swipe down to refresh
     */
    @Override
    public boolean isNeedSwipeRefresh() {
        return true;
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
        return JSONWidgetView.class;
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

    /**
     * Class which provide the presentation logic for the {@link JSONWidgetView}
     */
    private static class Presenter extends KitBaseWidgetPresenter {

        /**
         * Constructor which provide to create the {@link KitBaseWidgetPresenter} from
         *
         * @param view instance of {@link KitWidgetContract.View}
         */
        public Presenter(@NonNull KitWidgetContract.View view) {
            super(view);
        }

        /**
         * Method which provide the getting of the server data
         *
         * @param context  instance of {@link Context}
         * @param offset   {@link Integer} value of the offset
         * @param callback instance of {@link OnKitActionCallback}
         * @return
         */
        @Nullable
        @Override
        public void getServerData(@NonNull final Context context, final int offset,
                                  @Nullable final OnKitActionCallback callback) {
            final List<String> jsons = new ArrayList<>();
            final List<KitBaseListObject> objects = new ArrayList<>();
            BSThreadManager.execute(new BSThreadManager.OnExecutionCallback() {
                @Override
                public void onBackground() {
                    for (int i = 0; i < 100; i++) {
                        jsons.add(new LabelObject().toJson());
                    }
                    for (String json : jsons) {
                        final KitBaseListObject listObject = getObject(json);
                        if (listObject != null) {
                            objects.add(listObject);
                        }
                    }
                }

                @Override
                public void onMain() {
                    KitCallbackHelper.onSuccess(callback, context, offset, objects, false);
                }
            });

        }

        /**
         * Methd which provide the getting of the {@link View} class
         *
         * @return instance of the {@link View} class
         */
        @NonNull
        @Override
        public Class getViewClass() {
            return JSONWidgetView.class;
        }
    }
}
