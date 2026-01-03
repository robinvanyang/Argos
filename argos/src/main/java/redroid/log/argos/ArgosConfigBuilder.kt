package redroid.log.argos

import com.dianping.logan.LoganConfig

/**
 * 日志库配置
 *
 * @author RobinVanYang
 * @since 2026-01-03 22:35
 */
class ArgosConfigBuilder {
	var cachePath: String? = null //mmap缓存路径
	var path: String? = null //file文件路径
	var maxFileSize: Long = DEFAULT_FILE_SIZE //删除文件最大值
	var retainDay: Long = DEFAULT_DAY //删除天数
	var encryptKey16: ByteArray? = null //128位ase加密Key
	var encryptIv16: ByteArray? = null //128位aes加密IV
	var minSDCard: Long = DEFAULT_MIN_SDCARD_SIZE

	internal fun toLoganConfig(): LoganConfig {
		return LoganConfig.Builder()
			.setCachePath(cachePath)
			.setPath(path)
			.setMaxFile(maxFileSize)
			.setMinSDCard(minSDCard)
			.setDay(retainDay)
			.setEncryptKey16(encryptKey16)
			.setEncryptIV16(encryptIv16)
			.build()
	}

	companion object {
		private const val DAYS = (24 * 60 * 60 * 1000).toLong() //天
		private const val DEFAULT_DAY = 7 * DAYS //默认删除天数

		private const val M = (1024 * 1024).toLong() //M
		private const val DEFAULT_FILE_SIZE = 10 * M
		private const val DEFAULT_MIN_SDCARD_SIZE = 50 * M //最小的SD卡小于这个大小不写入
	}
}