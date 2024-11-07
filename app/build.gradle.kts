plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 26
        targetSdk = 33
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
    buildFeatures {
        viewBinding = true
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    //is is the easiest solution and allows you to use the Instant class directly.
    implementation (libs.threetenabp) // Latest version

    implementation (libs.material.v1120)
    // Dependências do Room
    implementation (libs.gson)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.legacy.support.v4)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.room.rxjava3)
    implementation(libs.androidx.room.guava)
    annotationProcessor(libs.androidx.room.room.guava)
    implementation(libs.androidx.room.testing)
    testImplementation(libs.androidx.room.room.testing)
    androidTestImplementation(libs.androidx.room.room.testing)
    annotationProcessor(libs.androidx.room.compiler)

    // Outras dependências
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation (libs.androidx.cardview)

}
