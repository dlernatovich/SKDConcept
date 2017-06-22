# SDK customization

## XML

### Simple customization

Developer able to customize of the the **Chat Kit SDK** through the many way. Simple way to customize, this is override the colors through the ```Manifest.xml``` and any **xml** with the colors items.

**Code 1:** *Example of the customization of the Chat Kit through the base colors*

```xml
<!--Accent color-->
<color name="color_ck_accent">#28df8a</color>
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
<color name="color_ck_list_highlight">#aaa</color>
<!--Color for unread mark-->
<color name="color_ck_unread_mark">#f57c00</color>
<!--Color for the divider headers-->
<color name="color_ck_header_divider">#f7f7f7</color>
<!--Colors for bubbles-->
<!--Bubble other-->
<color name="color_ck_bubble_other">#E5E5E9</color>
<!--Bubble me-->
<color name="color_ck_bubble_me">@color/color_ck_accent</color>
<color name="color_ck_warning">#FFF44336</color>
```



