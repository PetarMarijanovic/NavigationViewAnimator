[ ![Download](https://api.bintray.com/packages/petarmarijanovic/maven/navigation-view-animator/images/download.svg) ](https://bintray.com/petarmarijanovic/maven/navigation-view-animator/_latestVersion)

# NavigationViewAnimator

NavigationViewAnimator is an extension of Androids [ViewAnimator][1] class. It provides a simple API for changing views with navigation like animations.

![NavigationViewAnimator](http://i.makeagif.com/media/6-08-2017/B867_Y.gif)

Download
--------

#### Gradle:
```groovy
repositories {
    jcenter()
}
    
dependencies {
    compile 'com.petarmarijanovic:navigation-view-animator:1.1.2'
}
```

#### Maven:
```xml
<dependency>
  <groupId>com.petarmarijanovic</groupId>
  <artifactId>navigation-view-animator</artifactId>
  <version>1.1.2</version>
  <type>pom</type>
</dependency>
```

Usage
-----

Create NavigationViewAnimator programmatically or get a reference of it from a layout.xml, then send in the desired view and the animation direction.
```kotlin
  animator.showView(view) // Without animation
  animator.showView(view, IN_RIGHT_OUT_LEFT)
  animator.showView(view, IN_LEFT_OUT_RIGHT)
  animator.showView(view, IN_BOTTOM_OUT_NOTHING)
  animator.showView(view, IN_NOTHING_OUT_BOTTOM)
```

License
-------

    Copyright 2017 Petar Marijanović

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://developer.android.com/reference/android/widget/ViewAnimator.html
