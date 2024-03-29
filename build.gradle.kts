// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.13" apply false
    id("com.android.dynamic-feature") version "8.2.0" apply false

    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.google.firebase.crashlytics") version "2.9.4" apply false
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
val roomVersion by extra {"2.6.1"}
val retrofitVersion by extra {"2.9.0"}
val retoriftInterceptorVersion by extra {"4.12.0"}
val shimmerVersion by extra {"0.5.0"}

val firebaseAnalythicVersion by extra {"21.5.0"}
val firebaseCrashAnalythicVersion by extra {"18.6.0"}
val timberVersion by extra {"5.0.1"}
val lottieVersion by extra {"6.3.0"}

val sqlCipherVersion by extra {"4.4.0"}
val sqliteKtxVersion by extra {"2.4.0"}

val mockitoTestVersion by extra {"5.8.0"}
val junitTestVersion by extra {"4.13.2"}
val extJunitTestVersion by extra {"1.1.5"}
val espressoVersion by extra {"3.5.1"}

