//import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id ("kotlin-parcelize")

}

//fun getApiKey(propertyKey: String): String {
//    return gradleLocalProperties(rootDir,providers).getProperty(propertyKey)
//}

android {
    namespace = "com.example.miniyoutube"
    compileSdk = 34

//    buildFeatures {
//        buildConfig = true
//    }

    defaultConfig {
        applicationId = "com.example.miniyoutube"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        buildConfigField("String", "GOOGLE_API_KEY", getApiKey("GOOGLE_API_KEY"))
    }

    packaging {
        resources.excludes += "META-INF/DEPENDENCIES"
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

    buildFeatures{
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
    implementation(libs.androidx.core.splashscreen)

    //Glid
    implementation (libs.glide.android)
    ksp(libs.glide.android.compiler)
//    annotationProcessor(libs.glide.android.compiler)

    //youtube
    implementation (libs.google.api.services.youtube){
        exclude("org.apache.httpcomponents")
    }
    implementation (libs.google.http.client.android)
    implementation (libs.google.api.client.android){
        exclude("org.apache.httpcomponents")
    }
    implementation (libs.google.api.client.gson)

    //okhttp
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    //retorfit2
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //viewModelScope
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    // viewModels
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.fragment.ktx)

    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //youtube video player
    implementation (libs.core)
}