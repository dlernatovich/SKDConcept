package com.artlite.ckconcept.ui.views.chat.input;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which provide the action menu for the {@link KitInputMessageView}
 */
class KitInputActionsMenu implements Menu {

    /**
     * Instance of the {@link Context}
     */
    private final Context context;

    /**
     * {@link List} of the {@link InputActionsMenuItem}
     */
    private final List<InputActionsMenuItem> mItems = new ArrayList<>();

    /**
     * Constructor which provide to create the {@link KitInputActionsMenu} from the instance
     * of the {@link Context}
     *
     * @param context instance of the {@link Context}
     */
    public KitInputActionsMenu(@NonNull Context context) {
        this.context = context;
    }

    /**
     * Method which provide the adding of the {@link MenuItem} from the {@link String} value of
     * the title
     *
     * @param title {@link String} value of the title
     * @return instance of the {@link MenuItem}
     */
    @Override
    public MenuItem add(@NonNull final CharSequence title) {
        InputActionsMenuItem item = new InputActionsMenuItem();
        item.setTitle(title);
        mItems.add(item);
        return item;
    }

    /**
     * Method which provide the adding of the {@link MenuItem} from the {@link String} value of
     * the title
     *
     * @param titleRes {@link Integer} value of the title resource
     * @return instance of the {@link MenuItem}
     */
    @Override
    public MenuItem add(int titleRes) {
        InputActionsMenuItem item = new InputActionsMenuItem();
        item.setTitle(titleRes);
        mItems.add(item);
        return item;
    }

    /**
     * Method which provide the adding of the {@link MenuItem} from
     *
     * @param groupId {@link Integer} value of the group ID
     * @param itemId  {@link Integer} value of the item ID
     * @param order   {@link Integer} value of the order
     * @param title   {@link CharSequence} value of the title
     * @return integer value of the {@link MenuItem}
     */
    @Override
    public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
        InputActionsMenuItem item = new InputActionsMenuItem(itemId);
        item.setTitle(title);
        mItems.add(item);
        return item;
    }

    /**
     * Method which provide the adding of the {@link MenuItem} from
     *
     * @param groupId  {@link Integer} value of the group ID
     * @param itemId   {@link Integer} value of the item ID
     * @param order    {@link Integer} value of the order
     * @param titleRes {@link Integer} value of the title resource
     * @return integer value of the {@link MenuItem}
     */
    @Override
    public MenuItem add(int groupId, int itemId, int order, int titleRes) {
        InputActionsMenuItem item = new InputActionsMenuItem(itemId);
        item.setTitle(titleRes);
        mItems.add(item);
        return item;
    }

    /**
     * Method which provide the adding of the {@link SubMenu}
     *
     * @param title {@link CharSequence} of the title
     * @return instance of the {@link SubMenu}
     */
    @Override
    public SubMenu addSubMenu(CharSequence title) {
        // Not supported
        return null;
    }

    /**
     * Method which provide the adding of the {@link SubMenu}
     *
     * @param titleRes {@link Integer} value of the title resource
     * @return instance of the {@link SubMenu}
     */
    @Override
    public SubMenu addSubMenu(int titleRes) {
        // Not supported
        return null;
    }

    /**
     * Method which provide the adding of the {@link SubMenu} from
     *
     * @param groupId {@link Integer} value of the group ID
     * @param itemId  {@link Integer} value of the item ID
     * @param order   {@link Integer} value of the order
     * @param title   {@link CharSequence} of the title
     * @return instance of the {@link SubMenu}
     */
    @Override
    public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
        // Not supported
        return null;
    }

    /**
     * Method which provide the adding of the {@link SubMenu} from
     *
     * @param groupId  {@link Integer} value of the group ID
     * @param itemId   {@link Integer} value of the item ID
     * @param order    {@link Integer} value of the order
     * @param titleRes {@link Integer} value of the title resource
     * @return instance of the {@link SubMenu}
     */
    @Override
    public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
        // Not supported
        return null;
    }

    /**
     * Method which provide the adding of the intent option
     *
     * @param groupId          {@link Integer} value of the group ID
     * @param itemId           {@link Integer} value of the item ID
     * @param order            {@link Integer} value of the order
     * @param caller           instance of the {@link ComponentName}
     * @param specifics        {@link Intent} array of the specifics
     * @param intent           instance of the {@link Intent}
     * @param flags            {@link Integer} value of the flags
     * @param outSpecificItems {@link MenuItem} array
     * @return {@link Integer} value of the options
     */
    @Override
    public int addIntentOptions(int groupId,
                                int itemId,
                                int order,
                                ComponentName caller,
                                Intent[] specifics,
                                Intent intent,
                                int flags,
                                MenuItem[] outSpecificItems) {
        // Not supported
        return 0;
    }

    /**
     * Method which provide the remove item
     *
     * @param id {@link Integer} value of the ID
     */
    @Override
    public void removeItem(int id) {
        // Not supported
    }

    /**
     * Method which provide the removing group
     *
     * @param groupId {@link Integer} value of the group ID
     */
    @Override
    public void removeGroup(int groupId) {
        // Not supported
    }

    /**
     * Method which provide the clearing functional
     */
    @Override
    public void clear() {
        // Not supported
    }

    /**
     * Method which provide the setting the group checkable
     *
     * @param group     {@link Integer} value of the group ID
     * @param checkable {@link Boolean} value if checkable
     * @param exclusive {@link Boolean} value if exclusive
     */
    @Override
    public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {
        // Not supported
    }

    /**
     * Method which provide the setting of the group visible
     *
     * @param group   {@link Integer} value of the group ID
     * @param visible {@link Boolean} value if visible
     */
    @Override
    public void setGroupVisible(int group, boolean visible) {
        // Not supported
    }

    /**
     * Method which provide the setting group enabled
     *
     * @param group   {@link Integer} value of the group ID
     * @param enabled {@link Boolean} value if enabled
     */
    @Override
    public void setGroupEnabled(int group, boolean enabled) {
        // Not supported
    }

    /**
     * Method which provide the checking if current item has visible items
     *
     * @return {@link Boolean} value if current item have visible group
     */
    @Override
    public boolean hasVisibleItems() {
        return true;
    }

    /**
     * Method which provide the finding of the {@link MenuItem}
     *
     * @param id {@link Integer} value of the ID
     * @return instance of the {@link MenuItem}
     */
    @Override
    public MenuItem findItem(int id) {
        // Not supported
        return null;
    }

    /**
     * Method which provide the getting of the size
     *
     * @return {@link Integer} value of the size
     */
    @Override
    public int size() {
        return mItems.size();
    }

    /**
     * Method which provide the getting og the {@link MenuItem}
     *
     * @param index {@link Integer} value of the index
     * @return instance of the {@link MenuItem}
     */
    @Override
    public MenuItem getItem(int index) {
        return mItems.get(index);
    }

    /**
     * Method which provide the closing of the {@link MenuItem}
     */
    @Override
    public void close() {
        // Not supported
    }

    /**
     * Method which provide the shortcut performing
     *
     * @param keyCode {@link Integer} value of the key code
     * @param event   instance of the {@link KeyEvent}
     * @param flags   {@link Integer} value of the flags
     * @return {@link Boolean} value if shortcut performed
     */
    @Override
    public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
        // Not supported
        return false;
    }

    /**
     * Method which provide the checking if current object is shortcut key
     *
     * @param keyCode {@link Integer} value of the key code
     * @param event   instance of the {@link KeyEvent}
     * @return {@link Boolean} value of the checking
     */
    @Override
    public boolean isShortcutKey(int keyCode, KeyEvent event) {
        // Not supported
        return false;
    }

    /**
     * Method which provide the performing of the identifier action
     *
     * @param id    {@link Integer} value of the ID
     * @param flags {@link Integer} value of the flags
     * @return {@link Boolean} value if performed
     */
    @Override
    public boolean performIdentifierAction(int id, int flags) {
        // Not supported
        return false;
    }

    /**
     * Method which provide the setting of the qwerty mode
     *
     * @param isQwerty {@link Boolean} value if qwerty
     */
    @Override
    public void setQwertyMode(boolean isQwerty) {
        // Not supported
    }

    class InputActionsMenuItem implements MenuItem {

        private int mItemId;
        private CharSequence mTitle;
        private Drawable mIcon;

        public InputActionsMenuItem() {
        }

        public InputActionsMenuItem(int itemId) {
            mItemId = itemId;
        }

        @Override
        public int getItemId() {
            return mItemId;
        }

        @Override
        public int getGroupId() {
            return 0;
        }

        @Override
        public int getOrder() {
            return 0;
        }

        @Override
        public MenuItem setTitle(CharSequence title) {
            mTitle = title;
            return this;
        }

        @Override
        public MenuItem setTitle(int title) {
            mTitle = context.getString(title);
            return this;
        }

        @Override
        public CharSequence getTitle() {
            return mTitle;
        }

        @Override
        public MenuItem setTitleCondensed(CharSequence title) {
            // Not Supported
            return this;
        }

        @Override
        public CharSequence getTitleCondensed() {
            // Not supported
            return null;
        }

        @Override
        public MenuItem setIcon(Drawable icon) {
            mIcon = icon;
            return this;
        }

        @Override
        public MenuItem setIcon(int iconRes) {
            //noinspection deprecation
            mIcon = context.getResources().getDrawable(iconRes);
            return this;
        }

        @Override
        public Drawable getIcon() {
            return mIcon;
        }

        @Override
        public MenuItem setIntent(Intent intent) {
            // Not supported
            return this;
        }

        @Override
        public Intent getIntent() {
            // Not supported
            return null;
        }

        @Override
        public MenuItem setShortcut(char numericChar, char alphaChar) {
            // Not supported
            return this;
        }

        @Override
        public MenuItem setNumericShortcut(char numericChar) {
            // Not supported
            return this;
        }

        @Override
        public char getNumericShortcut() {
            return 0;
        }

        @Override
        public MenuItem setAlphabeticShortcut(char alphaChar) {
            // Not supported
            return this;
        }

        @Override
        public char getAlphabeticShortcut() {
            // Not supported
            return 0;
        }

        @Override
        public MenuItem setCheckable(boolean checkable) {
            // Not supported
            return this;
        }

        @Override
        public boolean isCheckable() {
            // Not supported
            return false;
        }

        @Override
        public MenuItem setChecked(boolean checked) {
            // Not supported
            return this;
        }

        @Override
        public boolean isChecked() {
            // Not supported
            return false;
        }

        @Override
        public MenuItem setVisible(boolean visible) {
            // Not supported
            return this;
        }

        @Override
        public boolean isVisible() {
            // Not supported
            return true;
        }

        @Override
        public MenuItem setEnabled(boolean enabled) {
            // Not supported
            return this;
        }

        @Override
        public boolean isEnabled() {
            // Not supported
            return true;
        }

        @Override
        public boolean hasSubMenu() {
            return false;
        }

        @Override
        public SubMenu getSubMenu() {
            // Not supported
            return null;
        }

        @Override
        public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
            // Not supported
            return this;
        }

        @Override
        public ContextMenu.ContextMenuInfo getMenuInfo() {
            // Not supported
            return null;
        }

        @Override
        public void setShowAsAction(int actionEnum) {
            // Not supported
        }

        @Override
        public MenuItem setShowAsActionFlags(int actionEnum) {
            // Not supported
            return this;
        }

        @Override
        public MenuItem setActionView(View view) {
            // Not supported
            return this;
        }

        @Override
        public MenuItem setActionView(int resId) {
            // Not supported
            return this;
        }

        @Override
        public View getActionView() {
            // Not supported
            return null;
        }

        @Override
        public MenuItem setActionProvider(ActionProvider actionProvider) {
            // Not supported
            return this;
        }

        @Override
        public ActionProvider getActionProvider() {
            // Not supported
            return null;
        }

        @Override
        public boolean expandActionView() {
            // Not supported
            return false;
        }

        @Override
        public boolean collapseActionView() {
            // Not supported
            return false;
        }

        @Override
        public boolean isActionViewExpanded() {
            // Not supported
            return false;
        }

        @Override
        public MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
            // Not supported
            return this;
        }
    }

}