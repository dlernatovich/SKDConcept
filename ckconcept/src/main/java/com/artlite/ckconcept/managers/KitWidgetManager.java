package com.artlite.ckconcept.managers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.adapteredrecyclerview.models.BaseRecyclerItem;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSBaseManager;
import com.artlite.bslibrary.managers.BSEventManager;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.callbacks.OnKitCreatorCallback;
import com.artlite.ckconcept.callbacks.OnKitEventCallback;
import com.artlite.ckconcept.helpers.KitNameHelper;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckconcept.models.menu.KitMenuModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class which provide the widget managing
 */

public final class KitWidgetManager extends BSBaseManager {

    /**
     * Instance of the {@link KitWidgetManager}
     */
    private static KitWidgetManager instance;

    /**
     * Instance of {@link Map}
     */
    private Map<String, OnKitCreatorCallback> typedMap;

    /**
     * Instance of the {@link Map}
     */
    private Map<String, Set<KitMenuModel>> menuHeaders;

    /**
     * Method which provide the initialization of {@link KitWidgetManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new KitWidgetManager(context);
        } else {
            Log.e(TAG, "KitWidgetManager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link KitWidgetManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static KitWidgetManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "KitWidgetManager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Default constructor
     *
     * @param context
     */
    public KitWidgetManager(@NonNull Context context) {
        super(context);
    }

    //==============================================================================================
    //                                      REGISTER
    //==============================================================================================

    /**
     * Method which provide the register of the {@link OnKitCreatorCallback} by type
     *
     * @param type    {@link String} value of the type
     * @param creator instance of the {@link OnKitCreatorCallback}
     * @return registering result
     */
    public static boolean register(@Nullable final String type,
                                   @Nullable final OnKitCreatorCallback creator) {
        if (BSValidationHelper.validateNull(type, creator, instance)) {
            boolean result = instance.getTypedMap().put(type, creator) != null;
            return result;
        }
        return false;
    }

    /**
     * Method which provide the register of the {@link OnKitCreatorCallback} by type
     *
     * @param type    {@link String} value of the type
     * @param creator instance of the {@link OnKitCreatorCallback}
     * @return registering result
     */
    public static boolean register(@Nullable final Class type,
                                   @Nullable final OnKitCreatorCallback creator) {
        return register(KitNameHelper.getClassType(type), creator);
    }

    /**
     * Method which provide the menus registering
     *
     * @param type {@link String} value of the type
     */
    protected static void registerMenus(@Nullable final String type) {
        final OnKitCreatorCallback creator = getCreator(type);
        if (creator != null) {
            final KitWidgetModel widget = creator.create(null);
            if (widget != null) {
                final List<KitMenuModel> items = widget.getMenuHeaders();
                for (KitMenuModel item : items) {
                    addMenu(item);
                }
            }
        }
    }

    /**
     * Method which provide the add of the {@link KitMenuModel}
     *
     * @param menuItem instance of the {@link KitMenuModel}
     */
    protected synchronized static void addMenu(KitMenuModel menuItem) {
        if (BSValidationHelper.validateNull(instance, menuItem)) {
            final String callerClass = menuItem.getCallerClass();
            final Map<String, Set<KitMenuModel>> menus = instance.getMenuHeaders();
            if (menus.containsKey(callerClass)) {
                Set<KitMenuModel> items = menus.get(menuItem.getCallerClass());
                if (items == null) {
                    items = new HashSet<>();
                }
                items.add(menuItem);
                menus.put(callerClass, items);
            } else {
                Set<KitMenuModel> items = new HashSet<>();
                items.add(menuItem);
                menus.put(callerClass, items);
            }
        }
    }

    //==============================================================================================
    //                                    GET CREATOR
    //==============================================================================================

    /**
     * Method which provide the getting of the registered {@link OnKitCreatorCallback}
     *
     * @param type instance of {@link Class}
     * @return instance of the {@link OnKitCreatorCallback}
     */
    @Nullable
    protected static OnKitCreatorCallback getCreator(@Nullable final Class type) {
        return getCreator(KitNameHelper.getClassType(type));
    }

    /**
     * Method which provide the getting of the registered {@link OnKitCreatorCallback}
     *
     * @param type {@link String} value of type
     * @return instance of the {@link OnKitCreatorCallback}
     */
    @Nullable
    protected static OnKitCreatorCallback getCreator(@Nullable final String type) {
        if (BSValidationHelper.validateNull(type, instance)) {
            if (instance.getTypedMap().containsKey(type)) {
                return instance.getTypedMap().get(type);
            }
        }
        return null;
    }

    //==============================================================================================
    //                                GET/SHOW VIEW CREATE
    //==============================================================================================

    /**
     * Method which provide the getting view for create widget
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static void showViewCreate(@Nullable final String type,
                                      @Nullable final Object object,
                                      @Nullable final OnKitEventCallback callback) {
        final BSView view = getViewCreate(type, object);
        if (view != null) {
            view.showAsDialog(callback);
        }
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static void showViewCreate(@Nullable final Class type,
                                      @Nullable final Object object,
                                      @Nullable final OnKitEventCallback callback) {
        showViewCreate(KitNameHelper.getClassType(type), object, callback);
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @param object instance of {@link Object}
     * @param type   instance of {@link Class}
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BSView getViewCreate(@Nullable final Class type, @Nullable final Object object) {
        return getViewCreate(KitNameHelper.getClassType(type), object);
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BSView getViewCreate(@Nullable final String type, @Nullable final Object object) {
        final OnKitCreatorCallback creator = getCreator(type);
        if (BSValidationHelper.validateNull(creator)) {
            final KitWidgetModel widget = creator.create(object);
            if ((widget != null) && (widget.isNeedCreateView())) {
                return widget.getViewCreate();
            }
        }
        return null;
    }

    //==============================================================================================
    //                                GET/SHOW VIEW DETAILS
    //==============================================================================================

    /**
     * Method which provide the showing of the view for display details
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static void showViewDetails(@Nullable final String type,
                                       @Nullable final Object object,
                                       @Nullable final OnKitEventCallback callback) {
        final BSView view = getViewDetails(type, object);
        if (view != null) {
            view.showAsDialog(callback);
        }
    }

    /**
     * Method which provide the showing of the view for display details
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static void showViewDetails(@Nullable final Class type,
                                       @Nullable final Object object,
                                       @Nullable final OnKitEventCallback callback) {
        showViewDetails(KitNameHelper.getClassType(type), object, callback);
    }

    /**
     * Method which provide the getting view for display details
     *
     * @param object instance of {@link Object}
     * @param type   instance of {@link Class}
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BSView getViewDetails(@Nullable final Class type, @Nullable final Object object) {
        return getViewDetails(KitNameHelper.getClassType(type), object);
    }

    /**
     * Method which provide the getting view for display details
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BSView getViewDetails(@Nullable final String type, @Nullable final Object object) {
        final OnKitCreatorCallback creator = getCreator(type);
        if (BSValidationHelper.validateNull(creator)) {
            final KitWidgetModel widget = creator.create(object);
            if ((widget != null) && (widget.isNeedDetailsView())) {
                return widget.getViewDetails();
            }
        }
        return null;
    }

    //==============================================================================================
    //                                GET/SHOW VIEW LIST
    //==============================================================================================

    /**
     * Method which provide the getting view for display details
     *
     * @param object instance of {@link Object}
     * @param type   instance of {@link Class}
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BaseRecyclerItem getViewList(@Nullable final Class type,
                                               @Nullable final Object object) {
        return getViewList(KitNameHelper.getClassType(type), object);
    }

    /**
     * Method which provide the getting view for display details
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BaseRecyclerItem getViewList(@Nullable final String type,
                                               @Nullable final Object object) {
        final OnKitCreatorCallback creator = getCreator(type);
        if (BSValidationHelper.validateNull(creator)) {
            final KitWidgetModel widget = creator.create(object);
            if ((widget != null) && (widget.isNeedListView())) {
                return widget.getViewList();
            }
        }
        return null;
    }

    //==============================================================================================
    //                                      GETTERS
    //==============================================================================================

    /**
     * Method which provide the getting of the typed {@link Map}
     *
     * @return instance of the typed {@link Map}
     */
    @NonNull
    protected Map<String, OnKitCreatorCallback> getTypedMap() {
        if (typedMap == null) {
            typedMap = new HashMap<>();
        }
        return typedMap;
    }

    /**
     * Method which provide the getting of the {@link Map} of the menu headers
     *
     * @return instance of the {@link String} value of the menu header
     */
    @NonNull
    protected Map<String, Set<KitMenuModel>> getMenuHeaders() {
        if (menuHeaders == null) {
            menuHeaders = new HashMap<>();
        }
        return menuHeaders;
    }

}
