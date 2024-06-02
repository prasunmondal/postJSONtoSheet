package com.tech4bytes.extrack.centralCache.utils

class CacheUtils {

    companion object {
        fun getCacheKey(key: String): String {
            return ClassDetailsUtils.getCaller() + "/" + key
        }

        fun getClassKey(thresholdClass: String = ""): String {
            return ClassDetailsUtils.getCaller(thresholdClass)
        }
    }
}