plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
}

android {
	namespace = "com.example.argos"
	compileSdk {
		version = release(36)
	}

	buildFeatures {
		buildConfig = true
		viewBinding = true
	}

	defaultConfig {
		applicationId = "com.example.argos"
		minSdk = 23
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

	}

//	buildTypes {
//		release {
//			isMinifyEnabled = false
//			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//		}
//	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlin {
		compilerOptions {
			jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
		}
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)

	implementation(project(":argos"))
	implementation(libs.timber)
}