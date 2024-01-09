val koinVersion: String by rootProject.extra
val androidKtxVersion : String by rootProject.extra
val appCompatVersion : String by rootProject.extra
val androidMaterialVersion : String by rootProject.extra
val constraintLayoutVersion : String by rootProject.extra
val glideVersion : String by rootProject.extra
val viewModelVersion : String by rootProject.extra
val liveDataVersion : String by rootProject.extra
val coroutineAndroidVersion : String by rootProject.extra
val navComponentVersion : String by rootProject.extra
val shimmerVersion : String by rootProject.extra


val firebaseAnalythicVersion : String by rootProject.extra
val firebaseCrashAnalythicVersion : String by rootProject.extra
val timberVersion : String by rootProject.extra

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}


android {
    namespace = "com.dicoding.magame"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.magame"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String","API_TOKEN","\"775746eef0bb417586b08ea73900b080\"")
        buildConfigField("String","BASE_URL","\"https://api.rawg.io/api/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:$androidKtxVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("com.google.android.material:material:$androidMaterialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")

    implementation("androidx.navigation:navigation-fragment-ktx:$navComponentVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navComponentVersion")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navComponentVersion")

    implementation ("com.github.bumptech.glide:glide:$glideVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelVersion") //viewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$liveDataVersion") //liveData
    //koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")

    implementation("com.facebook.shimmer:shimmer:$shimmerVersion")

    implementation("com.google.firebase:firebase-analytics-ktx:$firebaseAnalythicVersion")
    implementation("com.google.firebase:firebase-crashlytics:$firebaseCrashAnalythicVersion")
    implementation("com.jakewharton.timber:timber:$timberVersion")

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}