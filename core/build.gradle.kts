val koinVersion: String by rootProject.extra
val androidKtxVersion : String by rootProject.extra
val roomVersion : String by rootProject.extra
val retrofitVersion : String by rootProject.extra
val retoriftInterceptorVersion : String by rootProject.extra
val coroutineAndroidVersion : String by rootProject.extra
val appCompatVersion : String by rootProject.extra
val androidMaterialVersion : String by rootProject.extra
val glideVersion : String by rootProject.extra
val sqlCipherVersion : String by rootProject.extra
val sqliteKtxVersion : String by rootProject.extra
val lottieVersion : String by rootProject.extra


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
        consumerProguardFiles("consumer-rules.pro")
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String","API_TOKEN","\"775746eef0bb417586b08ea73900b080\"")
        buildConfigField("String","BASE_URL","\"https://api.rawg.io/api/\"")
        buildConfigField("String","HOST","\"api.rawg.io\"")
        buildConfigField("String","SHA_RAWG1","\"sha256/hgggx93d9MF4CiwZfCgWU/A/W7kRSMfzEXg/sAb89cQ=\"")
        buildConfigField("String","SHA_RAWG2","\"sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=\"")
        buildConfigField("String","SHA_RAWG3","\"sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    ksp("androidx.room:room-compiler:$roomVersion")
    //coroutine support
    implementation("androidx.room:room-ktx:$roomVersion") //room
    implementation("net.zetetic:android-database-sqlcipher:$sqlCipherVersion")
    implementation("androidx.sqlite:sqlite-ktx:$sqliteKtxVersion")

    //koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("com.airbnb.android:lottie:$lottieVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}