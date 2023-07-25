@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)

    kotlin("kapt")  //room配置
}

android {
    namespace = "cn.jack.roomsimple"
    compileSdk = 33

    defaultConfig {
        applicationId = "cn.jack.roomsimple"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //room配置
        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
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

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //room配置                https://developer.android.google.cn/topic/libraries/architecture/room?hl=zh-cn
    val room_version = "2.5.0"
    implementation("androidx.room:room-runtime:$room_version")
    //annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    //根据实际情况选择...
    // optional - RxJava2 support for Room
    // optional - RxJava3 support for Room
    // optional - Guava support for Room, including Optional and ListenableFuture
    // optional - Test helpers
    // optional - Paging 3 Integration

    val lifecycle_version = "2.5.1"
    // Lifecycles only (without ViewModel or LiveData)      https://developer.android.google.cn/jetpack/androidx/releases/lifecycle?hl=zh-cn
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    //https://github.com/sqlcipher/android-database-sqlcipher/
    //https://www.zetetic.net/sqlcipher/sqlcipher-for-android-legacy/
//    val sqlcipher_version = "4.5.4"
//    implementation("net.zetetic:android-database-sqlcipher:$sqlcipher_version")

    implementation("com.commonsware.cwac:saferoom.x:1.3.0")
}