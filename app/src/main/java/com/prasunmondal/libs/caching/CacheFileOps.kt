package com.prasunmondal.libs.caching

import android.content.Context
import com.prasunmondal.libs.app.contexts.AppContexts
import com.prasunmondal.libs.files.IOObjectToFile
import com.prasunmondal.libs.logs.instant.terminal.LogMe
import com.tech4bytes.mbrosv3.Utils.centralCache.CacheFilesList
import com.tech4bytes.mbrosv3.Utils.centralCache.CacheModel

open class CacheFileOps : CacheFileName() {
    fun saveCacheDataToFile(
        cacheKey: String,
        cache: MutableMap<String, MutableMap<String, CacheModel>>
    ) {
        LogMe.log("Saving cache data - File: ${getFileName(cacheKey)}")
        val filename = getFileName(cacheKey)
        val writeObj = IOObjectToFile()
        writeObj.WriteObjectToFile(AppContexts.get(), filename, cache)
        CacheFilesList.addToCacheFilesList(filename)
    }

    fun getCacheDataFromFile(
        context: Context,
        cacheKey: String
    ): MutableMap<String, MutableMap<String, CacheModel>> {
        return try {
            val readObj = IOObjectToFile()
            val result = readObj.ReadObjectFromFile(
                context,
                getFileName(cacheKey)
            ) as MutableMap<String, MutableMap<String, CacheModel>>
            LogMe.log("Reading records from file: ${getFileName(cacheKey)}: Successful")
            LogMe.log("Reading records from file contents: $result")
            result
        } catch (e: Exception) {
            LogMe.log("Reading records from file: ${getFileName(cacheKey)}: Failed")
            mutableMapOf()
        }
    }
}