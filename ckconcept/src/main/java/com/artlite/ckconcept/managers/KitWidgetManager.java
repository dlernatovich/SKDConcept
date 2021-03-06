package com.artlite.ckconcept.managers;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.adapteredrecyclerview.models.BaseObject;
import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSBaseManager;
import com.artlite.bslibrary.managers.BSEventManager;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.factories.OnKitCreatorFactory;
import com.artlite.ckconcept.helpers.KitNameHelper;
import com.artlite.ckconcept.models.define.KitBaseDefiner;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.models.widget.KitWidgetModel;

import java.util.ArrayList;
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
    private Map<String, OnKitCreatorFactory> typedMap;

    /**
     * Instance of the {@link Map}
     */
    private Map<String, Set<KitMenuModel>> menuHeaders;

    /**
     * Instance of the {@link Map}
     */
    private Map<Class, List<KitBaseDefiner>> definers;

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
     * Method which provide the checking if {@link OnKitCreatorFactory} need to be registering
     *
     * @param type    instance of {@link String} type
     * @param creator instance of the {@link OnKitCreatorFactory}
     * @return checking result
     */
    private static boolean isNeedRegistration(@Nullable final String type,
                                              @Nullable final OnKitCreatorFactory creator) {
        if (BSValidationHelper.validateNull(type, creator, instance)) {
            final OnKitCreatorFactory factory = instance.getTypedMap().get(type);
            if (factory != null) {
                final KitWidgetModel registered = factory.createForRegistration();
                final KitWidgetModel current = creator.createForRegistration();
                if (BSValidationHelper.validateNull(registered, current)) {
                    return registered.getPriority().lessThan(current.getPriority());
                }
            }
        }
        return true;
    }

    /**
     * Method which provide the register of the {@link OnKitCreatorFactory} by type
     *
     * @param type    {@link String} value of the type
     * @param creator instance of the {@link OnKitCreatorFactory}
     * @return registering result
     */
    public static boolean register(@Nullable final String type,
                                   @Nullable final OnKitCreatorFactory creator) {
        if (BSValidationHelper.validateNull(type, creator, instance)) {
            if (isNeedRegistration(type, creator)) {
                boolean result = instance.getTypedMap().put(type, creator) != null;
                registerMenus(type, creator);
                registerDefiners(type, creator);
                return result;
            }
        }
        return false;
    }

    /**
     * Method which provide the register of the {@link OnKitCreatorFactory} by type
     *
     * @param type    {@link String} value of the type
     * @param creator instance of the {@link OnKitCreatorFactory}
     * @return registering result
     */
    public static boolean register(@Nullable final Class type,
                                   @Nullable final OnKitCreatorFactory creator) {
        return register(KitNameHelper.getClassType(type), creator);
    }

    /**
     * Method which provide the menus registering
     *
     * @param type    {@link String} value of the type
     * @param creator instance of the {@link OnKitCreatorFactory}
     */
    protected static void registerMenus(@Nullable final String type,
                                        @Nullable final OnKitCreatorFactory creator) {
        if (BSValidationHelper.validateEmpty(type, creator)) {
            final KitWidgetModel widget = creator.createForRegistration();
            if ((widget != null) && (widget.isNeedHeaders())) {
                final List<KitMenuModel> items = widget.getMenuHeaders();
                if (items != null) {
                    for (KitMenuModel item : items) {
                        addMenu(item);
                    }
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

    /**
     * Method which provide the menus registering
     *
     * @param type    {@link String} value of the type
     * @param creator instance of the {@link OnKitCreatorFactory}
     */
    protected static void registerDefiners(@Nullable final String type,
                                           @Nullable final OnKitCreatorFactory creator) {
        if (BSValidationHelper.validateEmpty(type, creator)) {
            final KitWidgetModel widget = creator.createForRegistration();
            if (widget != null) {
                final List<KitBaseDefiner> items = widget.getDefiners();
                if (items != null) {
                    for (KitBaseDefiner item : items) {
                        addDefiner(item);
                    }
                }
            }
        }
    }

    /**
     * Method which provide the add of the {@link KitMenuModel}
     *
     * @param definer instance of the {@link KitMenuModel}
     */
    protected synchronized static void addDefiner(KitBaseDefiner definer) {
        if (BSValidationHelper.validateNull(instance, definer)) {
            final Class callerClass = definer.getCallerClass();
            final Map<Class, List<KitBaseDefiner>> definers = instance.getDefiners();
            if (definers.containsKey(callerClass)) {
                List<KitBaseDefiner> items = definers.get(definer.getCallerClass());
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.add(definer);
                definers.put(callerClass, items);
            } else {
                List<KitBaseDefiner> items = new ArrayList<>();
                items.add(definer);
                definers.put(callerClass, items);
            }
        }
    }

    //==============================================================================================
    //                                    GET CREATOR
    //==============================================================================================

    /**
     * Method which provide the getting of the registered {@link OnKitCreatorFactory}
     *
     * @param type instance of {@link Class}
     * @return instance of the {@link OnKitCreatorFactory}
     */
    @Nullable
    protected static OnKitCreatorFactory getCreator(@Nullable final Class type) {
        return getCreator(KitNameHelper.getClassType(type));
    }

    /**
     * Method which provide the getting of the registered {@link OnKitCreatorFactory}
     *
     * @param type {@link String} value of type
     * @return instance of the {@link OnKitCreatorFactory}
     */
    @Nullable
    protected static OnKitCreatorFactory getCreator(@Nullable final String type) {
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
    public static boolean showViewCreate(@Nullable final Context context,
                                         @Nullable final String type,
                                         @Nullable final Object object,
                                         @Nullable final BSView.OnDialogCallback callback) {
        final BSView view = getViewCreate(context, type, object);
        if (view != null) {
            view.showAsDialog(callback);
            return true;
        }
        return false;
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static boolean showViewCreate(@Nullable final Context context,
                                         @Nullable final Class type,
                                         @Nullable final Object object,
                                         @Nullable final BSView.OnDialogCallback callback) {
        return showViewCreate(context, KitNameHelper.getClassType(type), object, callback);
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @param object instance of {@link Object}
     * @param type   instance of {@link Class}
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BSView getViewCreate(@Nullable final Context context,
                                       @Nullable final Class type,
                                       @Nullable final Object object) {
        return getViewCreate(context, KitNameHelper.getClassType(type), object);
    }

    /**
     * Method which provide the getting view for create widget
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BSView getViewCreate(@Nullable final Context context,
                                       @Nullable final String type,
                                       @Nullable final Object object) {
        final OnKitCreatorFactory creator = getCreator(type);
        if (BSValidationHelper.validateNull(creator, context)) {
            final KitWidgetModel widget = creator.create(object);
            if ((widget != null) && (widget.isNeedCreateView())) {
                return widget.getViewCreate(context);
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
    public static boolean showViewDetails(@Nullable final Context context,
                                          @Nullable final String type,
                                          @Nullable final Parcelable object,
                                          @Nullable final BSView.OnDialogCallback callback) {
        final BSView view = getViewDetails(context, type, object);
        if (view != null) {
            view.showAsDialog(callback);
            return true;
        }
        return false;
    }

    /**
     * Method which provide the showing of the view for display details
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static boolean showViewDetails(@Nullable final Context context,
                                          @Nullable final Class type,
                                          @Nullable final Parcelable object,
                                          @Nullable final BSView.OnDialogCallback callback) {
        return showViewDetails(context, KitNameHelper.getClassType(type), object, callback);
    }

    /**
     * Method which provide the getting view for display details
     *
     * @param object instance of {@link Object}
     * @param type   instance of {@link Class}
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BSView getViewDetails(@Nullable final Context context,
                                        @Nullable final Class type,
                                        @Nullable final Parcelable object) {
        return getViewDetails(context, KitNameHelper.getClassType(type), object);
    }

    /**
     * Method which provide the getting view for display details
     *
     * @param object instance of {@link Object}
     * @param type   {@link String} value of the type
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static BSView getViewDetails(@Nullable final Context context,
                                        @Nullable final String type,
                                        @Nullable final Parcelable object) {
        final OnKitCreatorFactory creator = getCreator(type);
        if (BSValidationHelper.validateNull(creator, context)) {
            final KitWidgetModel widget = creator.create(object);
            if ((widget != null) && (widget.isNeedDetailsView())) {
                return widget.getViewDetails(context, object);
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
    public static BaseObject getViewList(@Nullable final Class type,
                                         @Nullable final Parcelable object) {
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
    public static BaseObject getViewList(@Nullable final String type,
                                         @Nullable final Parcelable object) {
        final OnKitCreatorFactory creator = getCreator(type);
        if (BSValidationHelper.validateNull(creator)) {
            final KitWidgetModel widget = creator.create(object);
            if ((widget != null) && (widget.isNeedListView())) {
                return widget.getViewList(object);
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
    protected Map<String, OnKitCreatorFactory> getTypedMap() {
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

    /**
     * Method which provide the getting of the {@link List} of the {@link KitMenuModel}
     *
     * @param aClass instance of {@link Class}
     * @return {@link List} of the {@link KitMenuModel}
     */
    @NonNull
    public static List<KitMenuModel> getCreateMenus(@Nullable final Class aClass) {
        if (BSValidationHelper.validateEmpty(aClass)) {
            return getCreateMenus(aClass.getSimpleName());
        }
        return new ArrayList<>();
    }

    /**
     * Method which provide the getting of the {@link List} of the {@link KitMenuModel}
     *
     * @param type instance of {@link String} type
     * @return {@link List} of the {@link KitMenuModel}
     */
    @NonNull
    public static List<KitMenuModel> getCreateMenus(@Nullable final String type) {
        if (BSValidationHelper.validateEmpty(type, instance)) {
            if (instance.getMenuHeaders().containsKey(type)) {
                return new ArrayList<>(instance.getMenuHeaders().get(type));
            }
        }
        return new ArrayList<>();
    }

    /**
     * Method which provide the getting of the definers {@link Map}
     *
     * @return instance of the definers {@link Map}
     */
    protected Map<Class, List<KitBaseDefiner>> getDefiners() {
        if (definers == null) {
            definers = new HashMap<>();
        }
        return definers;
    }

    /**
     * Method which provide the getting of the definers {@link Map}
     *
     * @param aClass instance of {@link Class}
     * @return instance of the definers {@link Map}
     */
    @NonNull
    public static List<KitBaseDefiner> getDefiners(@Nullable Class aClass) {
        if (instance != null) {
            if (instance.getDefiners().containsKey(aClass)) {
                final List<KitBaseDefiner> definers = instance.getDefiners().get(aClass);
                if (definers != null) {
                    return definers;
                }
            }
        }
        return new ArrayList<>();
    }
}
