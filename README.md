# ChatKit concept
## Main classes
### Manager
* `KitWidgetManager` - class which provide the registering of the widgets. <br>**WARNING:** registration should be in the application singleton;
```java
//Example
KitWidgetManager.register(KitWidgetType.CHANNEL.getValue(), new KitFactoryChannel());
```


### Factory
* `OnKitCreatorFactory` - class which provide the create of the `KitWidgetModel<T>` and should be registering inside the `KitWidgetManager`.

### Widget
* `KitWidgetModel<T>` - class which should be using for the creating of the new widget. This class have main methods which should be overriden;

### Interface
* `KitBaseListObject<T>` - class which provide the interface representation for the list view from the widget;
* `KitBaseDetailsView<T>` - class which provide the details view for the widget. This view show by default when user pressing of the widget list item;
* `KitBaseCreateView` - class which provide the view for widget creation;
* `KitMenuModel` - class which provide the creating of the menu for creation.

### Define type
* `KitBaseDefiner` - class which provide of the definition of the view type.

### Callback
* `OnKitViewCallback` - callback which provide the action from `ChatKit` base `View`. Base `ChatKit` views: user view, channels view, chat view. This callback can be using inside the `Activity`, `Fragment` and etc.

## How to
1. Create the instance of the `OnKitCreatorFactory` which allow to register of the new widget type;
2. Create the the Widget by inheritance of the `KitWidgetModel<T>`. Widget have 3 types of predefined views: create, details and list. If one of views is null, that means that developer will receive of the actions instead of displaying of the predefined views;
3. Implement all of `Views` that required by the `KitWidgetModel<T>`.<br>**VIEWS:** `KitBaseListObject<T>`, `KitBaseDetailsView<T>`, `KitBaseCreateView`. <br/>**WARNING:** Create view and Details view can be null, but list view can't be null;
4. Create the `KitBaseDefiner` objects for the defining of the object to the `Widget`;
5. If it needed, create the `KitMenuModel` of the menu item for the create widget list;
5. Register of the `OnKitCreatorFactory` in the `KitWidgetManager`.