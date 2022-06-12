plugins {
    id("maven-publish")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.apollographql.apollo3").version("3.3.0")
}

version = "1.0"

kotlin {
    android {
        publishLibraryVariants("release")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
            implementation("com.apollographql.apollo3:apollo-runtime:3.3.0")
        }

        sourceSets["androidMain"].dependencies {
            implementation("org.jetbrains.kotlin:kotlin-stdlib")
        }
        val commonMain by getting {}
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

apollo {
    packageName.set("com.makswin.bifrost")
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
}