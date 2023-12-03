plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.voidhash.hearthstonecodex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.voidhash.hearthstonecodex"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding {
        enable = true
    }

    packaging {
        resources.pickFirsts.add("META-INF/*")
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

val kotlinVersion = "1.12.0"
val lifecycleVersion = "2.6.2"
val navigationVersion = "2.7.5"
val rxjavaVersion = "3.1.5"
val rxAndroidVersion = "3.0.2"
val okhttpVersion = "4.10.0"
val retrofitVersion = "2.9.0"
val roomVersion = "2.6.1"
val koinVersion = "3.5.0"

dependencies {

    implementation("androidx.core:core-ktx:$kotlinVersion")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    //Lifecycle Library
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    //Navigation Library
    implementation ("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion")

    //Rx Java & Android
    implementation ("io.reactivex.rxjava3:rxandroid:$rxAndroidVersion")
    implementation ("io.reactivex.rxjava3:rxjava:$rxjavaVersion")

    //OkHttp Library
    implementation ("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")

    //Retrofit Library
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion")

    //Room Library
    implementation ("androidx.room:room-runtime:$roomVersion")
    ksp ("androidx.room:room-compiler:$roomVersion")
    implementation ("androidx.room:room-rxjava3:$roomVersion")

    //Koin Library
    implementation ("io.insert-koin:koin-core:$koinVersion")
    implementation ("io.insert-koin:koin-android:$koinVersion")
    implementation ("io.insert-koin:koin-androidx-navigation:$koinVersion")
    implementation ("io.insert-koin:koin-android-test:$koinVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}