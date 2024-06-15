package com.prasunmondal.libs.caching

import com.prasunmondal.libs.reflections.code.current.ClassDetailsUtils

open class CacheKey {
    fun getCacheKey(key: String, appendCacheKeyPrefix: Boolean = true): String {
        if(!appendCacheKeyPrefix)
            return key
        return ClassDetailsUtils.getCaller() + "/" + key
    }

    fun getClassKey(thresholdClass: String = ""): String {
        return ClassDetailsUtils.getCaller(thresholdClass)
    }
}