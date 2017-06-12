package com.artlite.ckconcept.managers.widget;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artlite.bslibrary.helpers.validation.BSValidationHelper;
import com.artlite.bslibrary.managers.BSBaseManager;
import com.artlite.bslibrary.managers.BSEventManager;
import com.artlite.bslibrary.ui.view.BSView;
import com.artlite.ckconcept.constants.KitWidgetType;
import com.artlite.ckconcept.factories.OnKitCreatorFactory;
import com.artlite.ckconcept.factories.OnKitListObjectFactory;
import com.artlite.ckconcept.helpers.name.KitNameHelper;
import com.artlite.ckconcept.models.define.KitBaseDefiner;
import com.artlite.ckconcept.models.list.KitBaseListObject;
import com.artlite.ckconcept.models.menu.KitMenuModel;
import com.artlite.ckconcept.models.widget.KitWidgetModel;
import com.artlite.ckconcept.ui.list.KitListUnsupported;
import com.artlite.ckconcept.ui.list.KitListUnsupportedMessageMy;
import com.artlite.ckconcept.ui.list.KitListUnsupportedMessageOther;
import com.magnet.max.android.User;
import com.magnet.mmx.client.api.MMXMessage;

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
     * Instance of the {@link KitBaseListObject}
     */
    private OnKitListObjectFactory unsupported;

    /**
     * Instance of the {@link KitBaseListObject}
     */
    private OnKitListObjectFactory unsupportedMyMessage;

    /**
     * Instance of the {@link KitBaseListObject}
     */
    private OnKitListObjectFactory unsupportedOtherMessage;

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
     * Method which provide the register of the {@link OnKitCreatorFactory}
     *
     * @param creator instance of the {@link OnKitCreatorFactory}
     * @return registering result
     */
    public static boolean register(@Nullable final OnKitCreatorFactory creator) {
        if ((creator != null) && (creator.getType() != null)) {
            return register(creator.getType(), creator);
        }
        return false;
    }

    /**
     * Method which provide the register of the {@link OnKitCreatorFactory} by type
     *
     * @param type    instance of the {@link KitWidgetType}
     * @param creator instance of the {@link OnKitCreatorFactory}
     * @return registering result
     */
    public static boolean register(@NonNull final KitWidgetType type,
                                   @Nullable final OnKitCreatorFactory creator) {
        return register(type.getValue(), creator);
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
            final Class objectClass = definer.getObjectClass();
            final Map<Class, List<KitBaseDefiner>> definers = instance.getDefiners();
            if (definers.containsKey(objectClass)) {
                List<KitBaseDefiner> items = definers.get(definer.getObjectClass());
                if (items == null) {
                    items = new ArrayList<>();
                }
                items.add(definer);
                definers.put(objectClass, items);
            } else {
                List<KitBaseDefiner> items = new ArrayList<>();
                items.add(definer);
                definers.put(objectClass, items);
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
     * @param callback instance of the {@link BSView.OnDialogCallback}
     * @param context  instance of {@link Context}
     * @param object   instance of {@link Object}
     * @param type     {@link String} value of the type
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
     * @param context  instance of {@link Context}
     * @param callback instance of the {@link BSView.OnDialogCallback}
     * @param object   instance of {@link Object}
     * @param type     {@link String} value of the type
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
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     * @param type    instance of {@link Class}
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
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     * @param type    {@link String} value of the type
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
     * @param context  instance of {@link Context}
     * @param callback instance of the {@link BSView.OnDialogCallback}
     * @param object   instance of {@link Object}
     * @param type     {@link String} value of the type
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
     * @param callback instance of the {@link BSView.OnDialogCallback}
     * @param context  instance of {@link Context}
     * @param object   instance of {@link Object}
     * @param type     {@link String} value of the type
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
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     * @param type    instance of {@link Class}
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
     * @param context instance of {@link Context}
     * @param object  instance of {@link Object}
     * @param type    {@link String} value of the type
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
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static KitBaseListObject getViewList(@Nullable final Parcelable object) {
        String type = null;
        if (object != null) {
            final Class objectClass = object.getClass();
            final List<KitBaseDefiner> definers = getDefiners(objectClass);
            if (BSValidationHelper.validateEmpty(definers)) {
                for (KitBaseDefiner definer : definers) {
                    final String definedType = definer.define(object);
                    if (BSValidationHelper.validateEmpty(definedType)) {
                        type = definedType;
                        break;
                    }
                }
            }
        }
        return getViewList(type, object);
    }

    /**
     * Method which provide the getting view for display details
     *
     * @param object instance of {@link Object}
     * @param type   instance of {@link Class}
     * @return instance of the {@link BSView}
     */
    @Nullable
    public static KitBaseListObject getViewList(@Nullable final Class type,
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
    @NonNull
    public static KitBaseListObject getViewList(@Nullable final String type,
                                                @Nullable final Parcelable object) {
        final OnKitCreatorFactory creator = getCreator(type);
        if (BSValidationHelper.validateNull(creator)) {
            final KitWidgetModel widget = creator.create(object);
            if (widget != null) {
                final KitBaseListObject listObject = widget.getViewList(object);
                if (listObject != null) {
                    listObject.setType(type);
                    return listObject;
                }
            }
        }
        return getUnsupportedWidget(object);
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

    //==============================================================================================
    //                                LIST WIDGET NOT SUPPORT
    //==============================================================================================

    /**
     * Method which provide the getting of the list item for widgets that isn't support
     *
     * @return instance of the {@link KitBaseListObject}
     */
    @NonNull
    private static KitBaseListObject getUnsupported() {
        KitBaseListObject result = null;
        if (instance != null) {
            if (instance.unsupported != null) {
                result = instance.unsupported.create(null);
            } else {
                result = new KitListUnsupported();
            }
        } else {
            result = new KitListUnsupported();
        }
        return result;
    }

    /**
     * Method which provide the setting of the list item for widgets that isn't support
     *
     * @param object instance of the {@link OnKitListObjectFactory}
     */
    public static void setUnsupported(@Nullable final OnKitListObjectFactory object) {
        if ((instance != null) && (object != null)) {
            instance.unsupported = object;
        }
    }

    /**
     * Method which provide the getting of the list item for my message widgets that isn't support
     *
     * @param object instance of the {@link Object}
     * @return instance of the {@link KitBaseListObject}
     */
    @NonNull
    private static KitBaseListObject getMessageUnsupportedMy(@NonNull final MMXMessage object) {
        KitBaseListObject result = null;
        if (instance != null) {
            if (instance.unsupportedMyMessage != null) {
                result = instance.unsupportedMyMessage.create(object);
            } else {
                result = new KitListUnsupportedMessageMy(object);
            }
        } else {
            result = new KitListUnsupportedMessageMy(object);
        }
        return result;
    }

    /**
     * Method which provide the setting of the list item for my message widgets that isn't support
     *
     * @param object instance of the {@link OnKitListObjectFactory}
     */
    public static void setMessageUnsupportedMy(@Nullable final OnKitListObjectFactory object) {
        if ((instance != null) && (object != null)) {
            instance.unsupportedMyMessage = object;
        }
    }

    /**
     * Method which provide the getting of the list item for my message widgets that isn't support
     *
     * @return instance of the {@link KitBaseListObject}
     */
    @NonNull
    private static KitBaseListObject getMessageUnsupportedOther(@NonNull final MMXMessage object) {
        KitBaseListObject result = null;
        if (instance != null) {
            if (instance.unsupportedOtherMessage != null) {
                result = instance.unsupportedOtherMessage.create(object);
            } else {
                result = new KitListUnsupportedMessageOther(object);
            }
        } else {
            result = new KitListUnsupportedMessageOther(object);
        }
        return result;
    }

    /**
     * Method which provide the setting of the list item for my message widgets that isn't support
     *
     * @param object instance of the {@link KitBaseListObject}
     */
    public static void setMessageUnsupportedOther(@Nullable final OnKitListObjectFactory object) {
        if ((instance != null) && (object != null)) {
            instance.unsupportedOtherMessage = object;
        }
    }

    /**
     * Method which provide the getting of the {@link KitBaseListObject} with accordance to the
     * class object type
     *
     * @param object instance of the {@link Object}
     * @return instance of the {@link KitBaseListObject}
     */
    @NonNull
    private static KitBaseListObject getUnsupportedWidget(@Nullable final Object object) {
        if ((object != null) && (object instanceof MMXMessage)) {
            final MMXMessage message = (MMXMessage) object;
            final String currentId = User.getCurrentUserId();
            if ((message != null) && (currentId != null)) {
                if (currentId.equals(message.getSender().getUserIdentifier())) {
                    return getMessageUnsupportedMy(message);
                }
                return getMessageUnsupportedOther(message);
            }
        }
        return getUnsupported();
    }
}
