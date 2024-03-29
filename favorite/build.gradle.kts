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

plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.dicoding.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding{
            enable = true
        }
    }

    buildTypes {
        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":app"))

    implementation("androidx.navigation:navigation-fragment-ktx:$navComponentVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelVersion") //viewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$liveDataVersion") //liveData

    //koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")

    implementation("androidx.core:core-ktx:$androidKtxVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("com.google.android.material:material:$androidMaterialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation ("com.github.bumptech.glide:glide:$glideVersion")

}