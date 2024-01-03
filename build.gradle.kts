// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.13" apply false
    id("com.android.dynamic-feature") version "8.2.0" apply false
}

val navComponentVersion by extra {"2.7.6"}
val androidKtxVersion by extra {"1.12.0"}
val coroutineAndroidVersion by extra {"1.12.0"}
val appCompatVersion by extra {"1.6.1"}
val androidMaterialVersion by extra {"1.11.0"}
val constraintLayoutVersion by extra {"2.1.4"}
val glideVersion by extra {"4.16.0"}
val viewModelVersion by extra {"2.6.2"}
val liveDataVersion by extra {"2.6.2"}
val koinVersion by extra {"3.5.3"}
val roomVersion by extra {"2.5.2"}
val retrofitVersion by extra {"2.9.0"}
val retoriftInterceptorVersion by extra {"4.11.0"}

