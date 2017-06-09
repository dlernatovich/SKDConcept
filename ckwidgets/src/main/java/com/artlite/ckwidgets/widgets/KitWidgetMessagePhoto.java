package com.artlite.ckwidgets.widgets;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.constants.KitMessageType;
import com.artlite.ckconcept.constants.KitWidgetPriority;
import com.artlite.ckconcept.helpers.message.KitMessageHelper;
import com.artlite.ckconcept.models.define.KitBaseDefiner;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckconcept.ui.abs.create.KitBaseCreateView;
import com.artlite.ckconcept.ui.abs.details.KitBaseDetailsView;
import com.artlite.ckconcept.ui.views.chat.KitChatView;
import com.artlite.ckwidgets.definers.KitDefinerMessagePhoto;
import com.artlite.ckwidgets.ui.details.KitDetailsMessageLocation;
import com.artlite.ckwidgets.ui.details.KitDetailsMessagePhoto;
import com.artlite.ckwidgets.ui.list.KitListMessagePhotoMy;
import com.artlite.ckwidgets.ui.list.KitListMessagePhotoOther;
import com.magnet.mmx.client.api.MMXMessage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dlernatovich on 6/9/2017.
 */

public final class KitWidgetMessagePhoto extends KitWidgetModel<MMXMessage> {

    /**
     * Constructor which provide the create of the {@link KitWidgetModel}
     */
    public KitWidgetMessagePhoto() {
        super();
    }

    /**
     * Constructor which provide the create of the {@link KitWidgetModel} from instance of
     * {@link Object}
     *
     * @param object instance of {@link Object}
     */
    public KitWidgetMessagePhoto(@Nullable final MMXMessage object) {
        super(object);
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @param context instance of {@link Context}
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public KitBaseCreateView getViewCreate(@NonNull Context context) {
        return null;
    }

    /**
     * Method which provide the checking if widget need to have of the create view
     *
     * @return checking if widget need to have of the create view
     */
    @Override
    public boolean isNeedCreateView() {
        return false;
    }

    /**
     * Method which provide the getting view for details
     *
     * @param context instance of {@link Context}
     * @param object
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public KitBaseDetailsView getViewDetails(@NonNull Context context, @Nullable Parcelable object) {
        return new KitDetailsMessagePhoto(context, object);
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
     * @param object
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public KitBaseListObject getViewList(@Nullable Parcelable object) {
        if ((object != null) && (object instanceof MMXMessage)) {
            final MMXMessage message = (MMXMessage) object;
            final KitMessageType type = KitMessageHelper.getType(message);
            if (type == KitMessageType.PHOTO) {
                if (KitMessageHelper.isMy(message)) {
                    return new KitListMessagePhotoMy(message);
                } else {
                    return new KitListMessagePhotoOther(message);
                }
            }
        }
        return null;
    }

    /**
     * Method which provide the getting of headers for the menu of the create of the widget
     *
     * @return {@link String} value of the menu header
     */
    @Nullable
    @Override
    public List<KitMenuModel> getMenuHeaders() {
        return null;
    }

    /**
     * Method which provide the checking if widget is need of the menu headers
     *
     * @return checking if widget is need of the menu headers
     */
    @Override
    public boolean isNeedHeaders() {
        return false;
    }

    /**
     * Method which provide the getting of the {@link List} of the {@link KitBaseDefiner}
     *
     * @return {@link List} of the {@link KitBaseDefiner}
     */
    @Nullable
    @Override
    public List<KitBaseDefiner> getDefiners() {
        return Arrays.asList(
                new KitBaseDefiner[]{
                        new KitDefinerMessagePhoto(KitChatView.class)
                }
        );
    }

    /**
     * Method which provide the getting of the menu header
     *
     * @return checking result
     */
    @Override
    public boolean isNeedMenuHeader() {
        return false;
    }

    /**
     * Method which provide the getting of the priority for the widget
     *
     * @return instance of the {@link KitWidgetPriority}
     */
    @NonNull
    @Override
    public KitWidgetPriority getPriority() {
        return KitWidgetPriority.MIDDLE;
    }
}
