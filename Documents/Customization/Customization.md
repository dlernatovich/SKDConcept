# SDK customization

## XML

### Simple customization

Developer able to customize of the the **Chat Kit SDK** through the many way. Simple way to customize, this is override the colors through the ```Manifest.xml``` and any **xml** with the colors items.

**Code 1:** *Example of the customization of the Chat Kit through the base colors*

```xml
 <!--\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\-->
<!--MAIN COLORS FOR FAST CUSTOMIZATION-->
<!--\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\-->
<!--Accent color-->
<color name="color_ck_accent">#517daa</color>
<!--Color inside the accent objects/text-->
<color name="color_ck_accent_inner">#ffffff</color>
<!--Background colors-->
<color name="color_ck_background">#ffffff</color>
<!--Color for common text-->
<color name="color_ck_text_main">#000000</color>
<!--Color for description text-->
<color name="color_ck_text_description">#757575</color>
<!--Color for the divider line in the list-->
<color name="color_ck_list_line">#aaa</color>
<!--Color for highlight of the selected item-->
<color name="color_ck_list_highlight">#bdbdbd</color>
<!--Color for unread mark-->
<color name="color_ck_unread_mark">#f57c00</color>
<!--Colors for bubbles-->
<!--Bubble other-->
<color name="color_ck_bubble_other">#d7ccc8</color>
<color name="color_ck_bubble_other_text">@color/color_ck_text_main</color>
<!--Bubble me-->
<color name="color_ck_bubble_me">@color/color_ck_accent</color>
<color name="color_ck_bubble_me_text">@color/color_ck_accent_inner</color>
<color name="color_ck_warning">#FFF44336</color>
```

### Detailed customization

#### Channels view

Developer able to customize detailed the channels view through the colors inside the **xml**.

**Code 2:** *Example of the customization of the Channels view through the colors*

```xml
<!--\\\\\\\\\\\\\\\\\\-->
<!--CHANNELS LIST VIEW-->
<!--\\\\\\\\\\\\\\\\\\-->
<!--Backgrounds-->
<color name="color_ck_background_channels_list">@color/color_ck_background</color>
<color name="color_ck_background_channels_view">@color/color_ck_background</color>
<color name="color_ck_background_channels_unread_view">@color/color_ck_unread_mark</color>
<color name="color_ck_background_channels_avatar_view">@color/color_ck_accent</color>
<color name="color_ck_background_channels_type_view">@color/color_ck_accent</color>
<!--Text colors-->
<color name="color_ck_text_channels_avatar">@color/color_ck_accent_inner</color>
<color name="color_ck_text_channels_name">@color/color_ck_text_main</color>
<color name="color_ck_text_channels_time">@color/color_ck_text_description</color>
<color name="color_ck_text_channels_description">@color/color_ck_text_description</color>
<!--Divider line-->
<color name="color_ck_text_channels_divider">@color/color_ck_list_line</color>
```

#### Users view

Developer able to customize detailed the users view through the colors inside the **xml**.

**Code 3:** *Example of the customization of the Users view through the colors*

```xml
<!--\\\\\\\\\\\\\\\\-->
<!--GROUPS LIST VIEW-->
<!--\\\\\\\\\\\\\\\\-->
<!--Backgrounds-->
<color name="color_ck_background_groups_list">@color/color_ck_background</color>
<color name="color_ck_background_groups_view">@color/color_ck_background</color>
<color name="color_ck_background_groups_avatar_view">@color/color_ck_accent</color>
<color name="color_ck_background_groups_divider_header">@color/color_ck_accent</color>
<color name="color_ck_background_no_groups">@color/color_ck_background</color>
<!--Text colors-->
<color name="color_ck_text_groups_name">@color/color_ck_text_main</color>
<color name="color_ck_text_groups_header">@color/color_ck_accent_inner</color>
<color name="color_ck_text_groups_header_button">@color/color_ck_accent_inner</color>
<color name="color_ck_text_no_groups">@color/color_ck_accent</color>
<!--\\\\\\\\\\\\\\\-->
<!--USERS LIST VIEW-->
<!--\\\\\\\\\\\\\\\-->
<!--Backgrounds-->
<color name="color_ck_background_users_list">@color/color_ck_background</color>
<color name="color_ck_background_users_selected_list">@color/color_ck_background</color>
<color name="color_ck_background_users_highlight_list">@color/color_ck_list_highlight</color>
<color name="color_ck_background_users_view">#ffffff</color>
<color name="color_ck_background_users_avatar_view">@color/color_ck_accent</color>
<color name="color_ck_background_users_selected_avatar_view">@color/color_ck_accent</color>
<color name="color_ck_background_users_blocked_view">@color/color_ck_accent</color>
<!--Text colors-->
<color name="color_ck_text_users_avatar">@color/color_ck_accent_inner</color>
<color name="color_ck_text_users_selected_avatar">@color/color_ck_accent_inner</color>
<color name="color_ck_text_users_name">@color/color_ck_text_main</color>
<color name="color_ck_text_users_selected_name">@color/color_ck_text_main</color>
<!--\\\\\\\\\\\\\\\\\\-->
<!--ALPHABETIC DIVIDER-->
<!--\\\\\\\\\\\\\\\\\\-->
<!--Backgrounds-->
<color name="color_ck_background_alphabetic_background">@color/color_ck_accent</color>
<!--Text colors-->
<color name="color_ck_text_alphabetic">@color/color_ck_accent_inner</color>
```

