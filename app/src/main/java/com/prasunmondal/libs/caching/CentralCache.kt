package com.tech4bytes.extrack.centralCache

import android.content.Context
import com.prasunmondal.libs.app.contexts.AppContexts
import com.prasunmondal.libs.caching.CacheFileOps
import com.prasunmondal.libs.logs.instant.terminal.LogMe
import com.prasunmondal.libs.files.IOObjectToFile
import com.prasunmondal.libs.reflections.code.current.ClassDetailsUtils
import com.tech4bytes.mbrosv3.Utils.centralCache.CacheFilesList
import com.tech4bytes.mbrosv3.Utils.centralCache.CacheModel
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
            val cacheClassKey = getClassKey()

            // check if the value is available in local cache
            var valueFromCache =  getFromCacheMemory<T>(key, appendCacheKeyPrefix)
            if(valueFromCache != null) {
                return valueFromCache
            }

            // if not available in local cache,
            // load from cache file
            centralCache.cache = centralCache.getCacheDataFromFile(context, cacheObjectKey)
            valueFromCache = getFromCacheMemory<T>(key, appendCacheKeyPrefix)
            return if(valueFromCache != null) {
                valueFromCache
            } else {
                LogMe.log("Cache Miss (key:$cacheObjectKey)")
                null
            }
        }

    fun <T> getFromCacheMemory(key: String, appendCacheKeyPrefix: Boolean): T? {
        val cacheObjectKey = getCacheKey(key, appendCacheKeyPrefix)
        val cacheClassKey = getClassKey()

        val classElements = centralCache.cache[cacheClassKey]
        if (classElements != null && classElements.containsKey(cacheObjectKey)) {
            LogMe.log("Cache Hit (key:$cacheObjectKey)- File")
            val cacheObj = classElements[cacheObjectKey]!!
            if (cacheObj.isExpired(cacheObjectKey, cacheClassKey)) {
                return null
            }
            return cacheObj.content as T
        }
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
            val cacheClassKey = getClassKey()
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
            centralCache.cache[getClassKey()] = hashMapOf()
            centralCache.saveCacheDataToFile(cacheKey, centralCache.cache)
        }

        fun <T : Any> invalidateClassCache(clazz: KClass<T>, cacheKey: String) {
            centralCache.cache[ClassDetailsUtils.getClassName(clazz)] = hashMapOf()
            centralCache.saveCacheDataToFile(cacheKey, centralCache.cache)
        }

}