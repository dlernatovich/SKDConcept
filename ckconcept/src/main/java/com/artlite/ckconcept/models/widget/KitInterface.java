package com.artlite.ckconcept.models.widget;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.adapteredrecyclerview.ui.views.AdapteredRecyclerView;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.models.menu.KitMenuModel;

import java.util.List;

/**
 * Class which provide the container functional for the widget
 */

interface KitInterface<T> {

    //==============================================================================================
    //                                    LAYOUT XML
    //==============================================================================================

    /**
     * Method which provide the getting of the layout for the {@link View} with create
     * widget functional
     *
     * @return {@link Integer} value of the layout ID
     */
    @LayoutRes
    @Nullable
    Integer getLayoutCreate();

    /**
     * Method which provide the getting of the layout for the {@link View} with displaying of the
     * widget
     *
     * @return {@link Integer} value of the layout ID
     */
    @LayoutRes
    @Nullable
    Integer getLayoutDisplay();

    /**
     * Method which provide the getting of the layout for the {@link View} with displaying of the
     * widget in the {@link AdapteredRecyclerView}
     *
     * @return {@link Integer} value of the layout ID
     */
    @LayoutRes
    @Nullable
    Integer getLayoutList();

    //==============================================================================================
    //                                     VIEWS
    //==============================================================================================

    /**
     * Method which provide the getting view for create widget
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    BSView getViewCreate();

    /**
     * Method which provide the checking if widget need to have of the create view
     *
     * @return checking if widget need to have of the create view
     */
    boolean isNeedCreateView();

    /**
     * Method which provide the getting view for details
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    BSView getViewDetails();

    /**
     * Method which provide the checking if widget need to have of the details view
     *
     * @return checking if widget need to have of the create view
     */
    boolean isNeedDetailsView();

    /**
     * Method which provide the getting view for list representation
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    BaseObject getViewList();

    /**
     * Method which provide the checking if widget need to have of the create view
     *
     * @return checking if widget need to have of the create view
     */
    boolean isNeedListView();

    //==============================================================================================
    //                                     APPLY
    //==============================================================================================

    /**
     * Method which provide the applying of the widget from the instance of inner {@link Object}
     */
    void apply();

    /**
     * Method which provide applying of the content from {@link Object}
     *
     * @param object instance of {@link Object}
     */
    void apply(@Nullable final T object);

    //==============================================================================================
    //                                CREATION MENU HEADER
    //==============================================================================================

    /**
     * Method which provide the getting of headers for the menu of the create of the widget
     *
     * @return {@link String} value of the menu header
     */
    @Nullable
    List<KitMenuModel> getMenuHeaders();

    /**
     * Method which provide the getting of the menu header
     *
     * @return checking result
     */
    boolean isNeedMenuHeader();

    /**
     * Method which provide the getting of the instance of {@link Object}
     *
     * @return instance of {@link Object}
     */
    @Nullable
    T getObject();


}
