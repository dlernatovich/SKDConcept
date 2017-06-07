package com.artlite.ckconcept.models.widget;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.constants.KitWidgetPriority;
import com.artlite.ckconcept.models.define.KitBaseDefiner;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.ui.abs.create.KitBaseCreateView;
import com.artlite.ckconcept.ui.abs.details.KitBaseDetailsView;

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
    KitBaseDetailsView getViewDetails(@NonNull Context context, @Nullable final Parcelable object);

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
    KitBaseListObject getViewList(@Nullable final Parcelable object);

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
     * Method which provide the checking if widget is need of the menu headers
     *
     * @return checking if widget is need of the menu headers
     */
    boolean isNeedHeaders();

    /**
     * Method which provide the getting of the {@link List} of the {@link KitBaseDefiner}
     *
     * @return {@link List} of the {@link KitBaseDefiner}
     */
    @Nullable
    List<KitBaseDefiner> getDefiners();

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

    /**
     * Method which provide the getting of the priority for the widget
     *
     * @return instance of the {@link KitWidgetPriority}
     */
    @NonNull
    KitWidgetPriority getPriority();

}
