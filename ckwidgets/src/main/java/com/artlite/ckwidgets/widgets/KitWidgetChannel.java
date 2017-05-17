package com.artlite.ckwidgets.widgets;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckconcept.ui.abs.KitBaseCreateView;
import com.artlite.ckconcept.ui.abs.KitBaseDetailsView;
import com.artlite.ckconcept.ui.views.KitChannelsView;
import com.artlite.ckwidgets.R;
import com.artlite.ckwidgets.ui.create.KitCreateChannelView;
import com.artlite.ckwidgets.ui.details.KitDetailsChannelView;
import com.artlite.ckwidgets.ui.list.KitListChannelView;
import com.magnet.mmx.client.api.ChannelDetail;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dlernatovich on 5/15/2017.
 */

public final class KitWidgetChannel extends KitWidgetModel<ChannelDetail> {

    /**
     * Constructor which provide the create of the {@link KitWidgetModel}
     */
    public KitWidgetChannel() {
        super();
    }

    /**
     * Constructor which provide the create of the {@link KitWidgetModel} from instance of
     * {@link Object}
     *
     * @param object instance of {@link Object}
     */
    public KitWidgetChannel(@Nullable ChannelDetail object) {
        super(object);
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public KitBaseCreateView getViewCreate(@NonNull Context context) {
        return new KitCreateChannelView(context);
    }

    /**
     * Method which provide the checking if widget need to have of the create view
     *
     * @return checking if widget need to have of the create view
     */
    @Override
    public boolean isNeedCreateView() {
        return true;
    }

    /**
     * Method which provide the getting view for details
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public KitBaseDetailsView getViewDetails(@NonNull Context context,
                                             @Nullable final Parcelable object) {
        return new KitDetailsChannelView(context, object);
    }

    /**
     * Method which provide the checking if widget need to have of the details view
     *
     * @return checking if widget need to have of the create view
     */
    @Override
    public boolean isNeedDetailsView() {
        return true;
    }

    /**
     * Method which provide the getting view for list representation
     *
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public KitBaseListObject getViewList(@Nullable final Parcelable object) {
        return new KitListChannelView(object);
    }

    /**
     * Method which provide the checking if widget need to have of the create view
     *
     * @return checking if widget need to have of the create view
     */
    @Override
    public boolean isNeedListView() {
        return true;
    }

    /**
     * Method which provide the applying of the widget from the instance of inner {@link Object}
     */
    @Override
    public void apply() {
        apply(getObject());
    }

    /**
     * Method which provide applying of the content from {@link Object}
     *
     * @param object instance of {@link Object}
     */
    @Override
    public void apply(@Nullable ChannelDetail object) {

    }

    /**
     * Method which provide the getting of headers for the menu of the create of the widget
     *
     * @return {@link String} value of the menu header
     */
    @Nullable
    @Override
    public List<KitMenuModel> getMenuHeaders() {
        return Arrays.asList(
                new KitMenuModel("Create channel",
                        R.mipmap.ic_wgt_create_channel,
                        KitWidgetChannel.class,
                        KitChannelsView.class,
                        KitWidgetType.CHANNEL.getValue())

        );
    }

    /**
     * Method which provide the getting of the menu header
     *
     * @return checking result
     */
    @Override
    public boolean isNeedMenuHeader() {
        return true;
    }
}
