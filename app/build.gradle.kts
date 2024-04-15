plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.voduchuy.nikeshopping"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.voduchuy.nikeshopping"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //UI
    implementation(libs.dotsindicator)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.eventbus)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.fresco)
    //glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    //rxjava
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    //koin
    implementation (libs.koin.android)
    //retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.gson)
    //okhttp
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    implementation (libs.adapter.rxjava2)
    //timber
    implementation (libs.timber)
    //room
    implementation (libs.androidx.room.runtime.v240)
    annotationProcessor (libs.androidx.room.compiler.v240)
    // Optional: Kotlin Extensions and Coroutines support for Room
    implementation (libs.androidx.room.ktx)
    // Optional: RxJava2 support for Room
    implementation (libs.androidx.room.rxjava2)
    // Optional: Guava support for Room, including Optional and ListenableFuture
    implementation (libs.androidx.room.guava)
    // Test helpers
    testImplementation (libs.androidx.room.testing)
    // LiveData and ViewModel
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
}