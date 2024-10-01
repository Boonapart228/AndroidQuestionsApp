plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //Hilt
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.balan.androidquestionsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.balan.androidquestionsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Preferences DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0-alpha01")
    //Animation
    implementation("com.airbnb.android:lottie-compose:6.0.0")

    //Material Design 3
    implementation("androidx.compose.material3:material3")

    //Room
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.8") // Библиотека "Room"
    ksp("androidx.room:room-compiler:2.5.0") // Кодогенератор
    implementation("androidx.room:room-ktx:2.5.0") // Дополнительно для Kotlin Coroutines, Kotlin Flows

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //GSON
    implementation("com.google.code.gson:gson:2.8.8")
    //Serializable
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    //Navigation
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}