package com.artlite.ckconcept.models.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.ui.abs.KitBaseCreateView;
import com.artlite.ckconcept.ui.abs.KitBaseDetailsView;

import java.util.List;

/**
 * Class which provide the container functional for the widget
 */

interface KitInterface<T> {

    //==============================================================================================
    //                                     VIEWS
    //==============================================================================================

    /**
     * Method which provide the getting view for create widget
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BSView}
     */
    @Nullable
    KitBaseCreateView getViewCreate(@NonNull Context context);

    /**
     * Method which provide the checking if widget need to have of the create view
     *
     * @return checking if widget need to have of the create view
     */
    boolean isNeedCreateView();

    /**
     * Method which provide the getting view for details
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BSView}
     */
    @Nullable
    KitBaseDetailsView getViewDetails(@NonNull Context context, @Nullable final Object object);

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
    KitBaseListObject getViewList();

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
