# RecyclerViewAdapterPlugin
Auto Create RecycleViewAdapter
## To use this plugin if only modify build.gradle file as below.
- add dataBinding support 
- add java 1.8 grammar support
- add dependencies include recyclerview,rxjava,rxlifecycle,rxbinding
```
android {
    .....
    
    dataBinding {
        enabled = true
    }
    
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    ...
    implementation 'com.android.support:recyclerview-v7:25.3.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.0.7'
    implementation 'com.trello.rxlifecycle2:rxlifecycle:2.0.1'
    implementation 'com.trello.rxlifecycle2:rxlifecycle-android:2.0.1'
    implementation 'com.trello.rxlifecycle2:rxlifecycle-components:2.0.1'
    implementation 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
}

```
## How to use this pulgin
- right click directory in which you want to create adapter file
- select new -> CreateAdapter




