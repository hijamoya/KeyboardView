# KeyboardView
Since KeyboardView was deprecated in API level 29. Google recommends to copy the file to your project.
This library just do the thing for you. For more information, please refer:

```java
https://developer.android.com/reference/android/inputmethodservice/KeyboardView
```

Reminder
-----

This library remove accessibility support, if you need that, you may find other solution.

Usage
-----
You should replace `KeyboardView` attributes in "android" namespace to the third party namespace "app".

Replace the deprecated KeyboardView: 
```xml
 <android.inputmethodservice.KeyboardView
        android:id="@+id/keyboardview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/background_gray"
        android:keyBackground="@drawable/key_bg"
        android:keyTextColor="@color/app_text_color"
        android:shadowColor="@android:color/transparent" />
```

To the custom one:
```xml
 <com.hijamoya.keyboardview.KeyboardView
        android:id="@+id/keyboardview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/background_gray"
        app:keyBackground="@drawable/key_bg"
        app:keyTextColor="@color/app_text_color"
        app:shadowColor="@android:color/transparent"  />
```

Also change namespace of your keyboard xml layout:

```xml
<Keyboard xmlns:android="http://schemas.android.com/apk/res/android"
    android:keyWidth="12.5%p"
    android:keyHeight="8%p">
    <Row>
        <Key
            android:codes="49"
            android:keyEdgeFlags="left"
            android:keyLabel="1" />
        <Key
            android:codes="50"
            android:keyLabel="2" />
    </Row>
</Keyboard>
```

To

```xml
<Keyboard xmlns:app="http://schemas.android.com/apk/res-auto"
    app:keyWidth="12.5%p"
    app:keyHeight="8%p">
    <Row>
        <Key
            app:codes="49"
            app:keyEdgeFlags="left"
            app:keyLabel="1" />
        <Key
            app:codes="50"
            app:keyLabel="2" />
    </Row>
</Keyboard>
```

Since we don't provide the default theme in android framework, you should define a custom theme by yourself:
```xml
  <style name="Widget.KeyboardView" parent="android:Widget">
        <item name="android:background">@android:drawable/keyboard_background</item>
        <item name="android:keyBackground">@android:drawable/btn_keyboard_key</item>
        <item name="android:keyTextSize">22sp</item>
        <item name="android:keyTextColor">#FFFFFFFF</item>
        <item name="android:keyPreviewLayout">@android:layout/keyboard_key_preview</item>
        <item name="android:keyPreviewOffset">-12dip</item>
        <item name="android:keyPreviewHeight">80dip</item>
        <item name="android:labelTextSize">14sp</item>
        <item name="android:popupLayout">@android:layout/keyboard_popup_keyboard</item>
        <item name="android:verticalCorrection">-10dip</item>
        <item name="android:shadowColor">#BB000000</item>
        <item name="android:shadowRadius">2.75</item>
  </style>
```

To

```xml
    <style name="KeyboardView" parent="android:Widget">
        <item name="android:background">@color/background_gray</item>
        <item name="keyBackground">@drawable/key_bg</item>
        <item name="keyTextSize">22sp</item>
        <item name="keyTextColor">@color/app_text_color</item>
        <item name="keyPreviewLayout">@android:layout/keyboard_key_preview</item>
        <item name="keyPreviewOffset">-12dp</item>
        <item name="keyPreviewHeight">80dp</item>
        <item name="labelTextSize">14sp</item>
        <item name="popupLayout">@android:layout/keyboard_popup_keyboard</item>
        <item name="verticalCorrection">-10dp</item>
        <item name="shadowColor">@android:color/transparent</item>
        <item name="shadowRadius">2.75</item>
    </style>
```

Adding Library
-----

You just add the following dependency to your build.gradle:
```groovy
 dependencies {
    implementation 'com.hijamoya:keyboardview:0.0.3'
  }
```

License
-----
    Copyright 2020 Jam Hsu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.