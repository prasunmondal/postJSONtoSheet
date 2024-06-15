package com.tech4bytes.extrack.centralCache.utils

import com.prasunmondal.libs.reflections.code.current.ClassDetailsUtils

class CacheUtils {

    companion object {
//        fun getCacheKey(key: String, appendCacheKeyPrefix: Boolean = true): String {
//
//        }

        fun getClassKey(thresholdClass: String = ""): String {
            return ClassDetailsUtils.getCaller(thresholdClass)
        }
    }
}