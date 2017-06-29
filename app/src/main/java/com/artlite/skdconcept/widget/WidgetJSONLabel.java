package com.artlite.skdconcept.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.artlite.bslibrary.helpers.json.BSJsonHelper;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.constants.KitWidgetPriority;
import com.artlite.ckconcept.factories.OnKitCreatorFactory;
import com.artlite.ckconcept.models.define.KitBaseDefiner;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckconcept.ui.abs.create.KitBaseCreateView;
import com.artlite.skdconcept.models.LabelObject;
import com.artlite.skdconcept.ui.views.ListObjectVerticalLabel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dlernatovich on 29.06.2017.
 */

public class WidgetJSONLabel extends KitWidgetModel<String> {

    public static final String K_TYPE = "JSONWidgetType:LabelObject";

    /**
     * Constructor which provide the create of the {@link KitWidgetModel}
     */
    public WidgetJSONLabel() {
        super();
    }

    /**
     * Constructor which provide the create of the {@link KitWidgetModel} from instance of
     * {@link Object}
     *
     * @param object instance of {@link Object}
     */
    public WidgetJSONLabel(@Nullable final String object) {
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
     * Method which provide the getting view for list representation
     *
     * @param object
     * @return instance of the {@link BSView}
     */
    @Nullable
    @Override
    public KitBaseListObject getViewList(@Nullable Object object) {
        if (object instanceof String) {
            String json = (String) object;
            if (BSJsonHelper.isKindOfClass(json, LabelObject.class)) {
                LabelObject labelObject = BSJsonHelper.fromJson(json, LabelObject.class);
                return new ListObjectVerticalLabel(labelObject);
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
                        new JSONDefiner(String.class)
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
        return KitWidgetPriority.HIGH;
    }

    /**
     * Definer class
     */
    private static class JSONDefiner extends KitBaseDefiner {

        /**
         * Default constructor for the {@link KitBaseDefiner}
         *
         * @param callerClass instance of the {@link Class}
         */
        public JSONDefiner(@NonNull Class callerClass) {
            super(callerClass);
        }

        /**
         * Method which provide the define functional for the {@link Object}
         *
         * @param object instance of the {@link Object}
         */
        @Nullable
        @Override
        public String define(@Nullable Object object) {
            if (object instanceof String) {
                String json = (String) object;
                if (json != null) {
                    if (BSJsonHelper.isKindOfClass(json, LabelObject.class)) {
                        return K_TYPE;
                    }
                }
            }
            return null;
        }
    }

    public static final class Creator implements OnKitCreatorFactory<String> {

        /**
         * Method which provide the create {@link KitWidgetModel} from
         *
         * @param object instance of {@link Object}
         * @return instance of {@link KitWidgetModel}
         */
        @Nullable
        @Override
        public KitWidgetModel create(@Nullable String object) {
            return new WidgetJSONLabel(object);
        }

        /**
         * Method which provide the create of the {@link KitWidgetModel} for first initialization
         *
         * @return instance of the {@link KitWidgetModel}
         */
        @NonNull
        @Override
        public KitWidgetModel createForRegistration() {
            return new WidgetJSONLabel();
        }

        /**
         * Method which provide the getting of the {@link String} value of the widget type
         *
         * @return {@link String} value of the widget type
         */
        @Nullable
        @Override
        public String getType() {
            return K_TYPE;
        }
    }
}
