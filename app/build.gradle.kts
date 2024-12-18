plugins {
    id("com.android.application") // Убираем дублирование
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services") // Google Services
}

android {
    namespace = "com.example.fullbuddy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.fullbuddy"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Навигация
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.0")

    // Стандартные зависимости
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    // Зависимости для Unit-тестов
    testImplementation("junit:junit:4.13.2")

    // Зависимости для Instrumentation-тестов
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-firestore-ktx:24.7.1")

}
