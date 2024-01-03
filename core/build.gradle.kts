val koinVersion: String by rootProject.extra
val androidKtxVersion : String by rootProject.extra
val roomVersion : String by rootProject.extra
val retrofitVersion : String by rootProject.extra
val retoriftInterceptorVersion : String by rootProject.extra
val coroutineAndroidVersion : String by rootProject.extra
val appCompatVersion : String by rootProject.extra
val androidMaterialVersion : String by rootProject.extra
val glideVersion : String by rootProject.extra


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.dicoding.core"
    compileSdk = 34

    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String","API_TOKEN","\"775746eef0bb417586b08ea73900b080\"")
        buildConfigField("String","BASE_URL","\"https://api.rawg.io/api/\"")
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:$androidKtxVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("com.google.android.material:material:$androidMaterialVersion")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$retoriftInterceptorVersion")

    implementation ("com.github.bumptech.glide:glide:$glideVersion")

    //room
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    //coroutine support
    implementation("androidx.room:room-ktx:$roomVersion") //room

    //koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}