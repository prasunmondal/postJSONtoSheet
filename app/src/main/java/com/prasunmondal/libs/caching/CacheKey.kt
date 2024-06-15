package com.prasunmondal.libs.caching

import com.prasunmondal.libs.reflections.code.current.ClassDetailsUtils

open class CacheKey: CacheFileName() {
    fun getCacheKey(key: String, appendCacheKeyPrefix: Boolean = true): String {
        if(!appendCacheKeyPrefix)
            return key
        return ClassDetailsUtils.getCaller() + "/" + key
    }
}