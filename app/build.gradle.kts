import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

val clientId = getLocalPropertyKey("CLIENT_ID")
val tmapAppKey = getLocalPropertyKey("TMAP_APP_KEY")
val searchAPIClientID = getLocalPropertyKey("SEARCH_API_CLIENT_ID")
val searchAPIClientSecret = getLocalPropertyKey("SEARCH_API_CLIENT_SECRET")
val odsayAppKey = getLocalPropertyKey("ODSAY_APP_KEY")
android {

    namespace = "com.hansung.sherpa"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hansung.sherpa"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "CLIENT_ID", clientId)
        buildConfigField("String", "TMAP_APP_KEY", tmapAppKey)
        buildConfigField("String", "SEARCH_API_CLIENT_ID", searchAPIClientID)
        buildConfigField("String", "SEARCH_API_CLIENT_SECRET", searchAPIClientSecret)
        buildConfigField("String", "ODSAY_APP_KEY", odsayAppKey)
        manifestPlaceholders["CLIENT_ID"] = clientId
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        dataBinding = true
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
}

fun getLocalPropertyKey(propertyKey : String) : String{
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.naver.maps:map-sdk:3.18.0")
    // for api request
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    //for User Location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Material Design 3
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.core:core-ktx:1.0.2")

}