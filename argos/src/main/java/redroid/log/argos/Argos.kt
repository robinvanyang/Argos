package redroid.log.argos

import android.annotation.SuppressLint
import android.util.Log
import com.dianping.logan.Logan
import com.dianping.logan.LoganConfig
import org.jetbrains.annotations.NonNls
import timber.log.Timber

/**
 * 日志封装
 * 包括终端输出(使用Timber)及日志上报(美团Logan)
 *
 * @author RobinVanYang
 * @since 2026-01-02 16:19
 */
object Argos {
	private const val TAG = "Argos"

	// 使用 volatile 保证线程安全
	@Volatile
	private var isInitialized = false

	fun init(block: ArgosConfigBuilder.() -> Unit) {
		if (isInitialized) return

		val builder = ArgosConfigBuilder()
		builder.block()

		initTimber()
		initLogan(builder.toLoganConfig())

		isInitialized = true
	}

	private fun initTimber() {
		Log.d("Argos", "initTimber: ")
		//remove all trees in the forest.
		if (Timber.forest().isNotEmpty()) {
			Timber.uprootAll()
		}

		Timber.plant(ConsoleTree())
//		Timber.plant(ReleaseTree())
	}

	private fun initLogan(loganConfig: LoganConfig) {
		Logan.init(loganConfig)
		// TODO: 2021/5/30
		Logan.setDebug(false)
		Logan.setOnLoganProtocolStatus { cmd, code ->
			Log.d(TAG, "clogan > cmd : $cmd | code: $code")
		}
		Logan.f()
	}

	fun v(@NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.v(message, *args)
	}

	fun v(t: Throwable?, @NonNls message: String? = null, vararg args: Any?) {
		checkForestTree()
		Timber.v(t, message, *args)
	}

	fun v(t: Throwable?) {
		checkForestTree()
		Timber.v(t)
	}

	fun d(@NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.d(message, *args)
	}

	fun d(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.d(t, message, *args)
	}

	fun d(t: Throwable?) {
		checkForestTree()
		Timber.d(t)
	}

	fun i(@NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.i(message, *args)
	}

	fun i(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.i(t, message, *args)
	}

	fun i(t: Throwable?) {
		checkForestTree()
		Timber.i(t)
	}

	fun w(@NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.w(message, *args)
	}

	fun w(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.w(t, message, *args)
	}

	fun w(t: Throwable?) {
		checkForestTree()
		Timber.w(t)
	}

	fun e(@NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.e(message, *args)
	}

	fun e(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
		checkForestTree()
		Timber.e(t, message, *args)
	}

	fun e(t: Throwable?) {
		checkForestTree()
		Timber.e(t)
	}

	fun tag(tag: String): Timber.Tree {
		checkForestTree()
		return Timber.tag(tag)
	}

	@SuppressLint("LogNotTimber")
	private fun checkForestTree() {
		if (Timber.forest().isEmpty()) {
			Log.d(TAG, "plantDefaultTree")
			Timber.plant(ConsoleTree())
		}
	}
}