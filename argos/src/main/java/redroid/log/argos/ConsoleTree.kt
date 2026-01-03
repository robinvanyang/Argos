package redroid.log.argos

import android.os.Build
import timber.log.Timber

/**
 * Timber Console Log Tree.
 * Inspired By: https://xiazdong.github.io/2017/05/17/Timber/
 *
 * @author RobinVanYang
 * @since 2026-01-02 16:20
 */
class ConsoleTree : Timber.DebugTree() {
	override val fqcnIgnore: List<String?>
		get() = super.fqcnIgnore.plus(listOf(Argos::class.java.name))

	override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
		if (!isLoggable(tag, priority)) {
			return
		}

		var shadowTag = tag
		var shadowMessage = message

		val threadName = Thread.currentThread().name
		shadowMessage = "thread@$threadName: $shadowMessage"

		if (DEFAULT_TAG != shadowTag && Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
			shadowMessage = "$shadowTag,$shadowMessage"
			shadowTag = DEFAULT_TAG
		}

		super.log(priority, shadowTag, shadowMessage, t)
	}

	override fun createStackElementTag(element: StackTraceElement): String {
		var tag = super.createStackElementTag(element)
		val superTag = tag
		tag = if (null == superTag) {
			DEFAULT_TAG
		} else {
			if (element.fileName.contains(".")) {
				val begin = "("
				val suffix = element.fileName.substring(element.fileName.lastIndexOf("."))
				val end = ":" + element.lineNumber + ")"
				begin + superTag + suffix + end
			} else {
				superTag
			}
		}
		return tag
	}

	companion object {
		//static final String DEFAULT_TAG = "⇢";
		const val DEFAULT_TAG = ">"
	}
}