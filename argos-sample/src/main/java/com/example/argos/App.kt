package com.example.argos

import android.app.Application
import redroid.log.argos.Argos
import timber.log.Timber
import java.io.File

/**
 * @author RobinVanYang
 * @since 2026-01-03 13:39
 */
class App : Application(){
	override fun onCreate() {
		super.onCreate()
		Argos.init {
			cachePath = cacheDir.absolutePath
			path = filesDir.absolutePath + File.separator + "logan_v1"
		}
	}
}