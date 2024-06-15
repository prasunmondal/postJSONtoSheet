package com.prasunmondal.libs.caching

import com.tech4bytes.extrack.centralCache.Configuration
import com.tech4bytes.extrack.centralCache.utils.CacheUtils

open class CacheFileName {
    fun getFileName(cacheKey: String): String {
        return "CentralCache-" + if (Configuration.configs.storagePatternType == Configuration.DATA_STORING_TYPE.CLASS_FILES) {
            CacheUtils.getClassKey()
        } else if (Configuration.configs.storagePatternType == Configuration.DATA_STORING_TYPE.CACHE_KEY) {
            cacheKey.replace("/", "-")
        } else {
            "data.dat"
        }
    }
}