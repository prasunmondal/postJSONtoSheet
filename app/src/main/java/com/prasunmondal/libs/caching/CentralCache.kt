package com.tech4bytes.extrack.centralCache

import android.content.Context
import com.prasunmondal.libs.app.contexts.AppContexts
import com.prasunmondal.libs.caching.CacheFileOps
import com.prasunmondal.libs.logs.instant.terminal.LogMe
import com.prasunmondal.libs.files.IOObjectToFile
import com.tech4bytes.extrack.centralCache.utils.CacheUtils
import com.prasunmondal.libs.reflections.code.current.ClassDetailsUtils
import com.tech4bytes.mbrosv3.Utils.centralCache.CacheFilesList
import com.tech4bytes.mbrosv3.Utils.centralCache.CacheModel
import java.time.LocalDateTime
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
open class CentralCache: CacheFileOps() {

    // Map of filenames, and (contents with key)
    var cache: MutableMap<String, MutableMap<String, CacheModel>> = hashMapOf()


        private var centralCache = CentralCache()

        fun <T> get(context: Context, key: String, useCache: Boolean = true, appendCacheKeyPrefix: Boolean = true): T? {

            // if user wants to force refresh the values in the cache, pass useCache as false
            if (!useCache) {
                LogMe.log("UseCache: False (Forced to not use cached data)")
                return null
            }

            val cacheObjectKey = getCacheKey(key, appendCacheKeyPrefix)
            val cacheClassKey = CacheUtils.getClassKey()
            LogMe.log("Getting data from Cache - key: $cacheObjectKey")
            var classElements = centralCache.cache[cacheClassKey]

            if (classElements != null && classElements.containsKey(cacheObjectKey)) {
                LogMe.log("Cache Hit (key:$cacheObjectKey)- Local Memory")
                val cacheObj = classElements[cacheObjectKey]!!
                if (cacheObj.expiryTime.isBefore(LocalDateTime.now())) {
                    LogMe.log("Data Expired (key:$cacheObjectKey)")
                    LogMe.log("Deleting cache data")
                    centralCache.cache[cacheClassKey]!!.remove(cacheObjectKey)
                    centralCache.saveCacheDataToFile(cacheObjectKey, centralCache.cache)
                    return null
                }
                return cacheObj.content as T
            }

            centralCache.cache = centralCache.getCacheDataFromFile(context, cacheObjectKey)
            classElements = centralCache.cache[cacheClassKey]
            if (classElements != null && classElements.containsKey(cacheObjectKey)) {
                LogMe.log("Cache Hit (key:$cacheObjectKey)- File")
                val cacheObj = classElements[cacheObjectKey]!!
                if (cacheObj.expiryTime.isBefore(LocalDateTime.now())) {
                    LogMe.log("Data Expired (key:$cacheObjectKey)- Local Memory")
                    LogMe.log("Deleting cache data")
                    centralCache.cache[cacheClassKey]!!.remove(cacheObjectKey)
                    centralCache.saveCacheDataToFile(cacheObjectKey, centralCache.cache)
                    return null
                }
                return cacheObj.content as T
            }

            LogMe.log("Cache Miss (key:$cacheObjectKey)")
            return null
        }

        /**
         * First try to get the value from cache,
         * if not available,
         * prepares the data, saves to cache for future use and returns it
         */
//        fun <T: Any> getOrPutNGet(context: Context, key: String, contentGenerator: Consumer<T>): T {
//            val isCacheHit = get<T>(context, key)
//            if(isCacheHit == null) {
//                var content = contentGenerator.accept() as T
//                put(key, content)
//            }
//            return content
//        }

        fun <T> put(key: String, data: T, appendCacheKeyPrefix: Boolean = true) {
            val cacheClassKey = CacheUtils.getClassKey()
            val cacheKey = getCacheKey(key, appendCacheKeyPrefix)
            LogMe.log("Putting data to Cache - key: $cacheKey")
            val presentData = centralCache.cache[cacheClassKey]
            if (presentData == null) {
                centralCache.cache[cacheClassKey] = hashMapOf()
            }
            centralCache.cache[cacheClassKey]!![cacheKey] = CacheModel(data as Any?)
            centralCache.saveCacheDataToFile(cacheKey, centralCache.cache)
        }

        fun <T> putNGet(key: String, data: T): T {
            put(key, data)
            return data
        }

        fun invalidateFullCache() {
            centralCache.cache.clear()
            val cacheFiles = CacheFilesList.getCacheFilesList()
            val writeObj = IOObjectToFile()
            cacheFiles.forEach {
                LogMe.log("Clearing cache: deleting file - $it")
                writeObj.WriteObjectToFile(AppContexts.get(), it, null)
            }
            CacheFilesList.clearCacheFilesList()
        }

        fun invalidateClassCache(cacheKey: String) {
            centralCache.cache[CacheUtils.getClassKey()] = hashMapOf()
            centralCache.saveCacheDataToFile(cacheKey, centralCache.cache)
        }

        fun <T : Any> invalidateClassCache(clazz: KClass<T>, cacheKey: String) {
            centralCache.cache[ClassDetailsUtils.getClassName(clazz)] = hashMapOf()
            centralCache.saveCacheDataToFile(cacheKey, centralCache.cache)
        }

}