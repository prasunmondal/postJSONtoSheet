package com.prasunmondal.libs.gsheet.clients.responseCaching

import android.content.Context
import com.prasunmondal.libs.caching.CentralCacheObj
import com.prasunmondal.libs.logs.instant.terminal.LogMe
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import com.prasunmondal.libs.gsheet.clients.Tests.TestBulkOps.TestSheet1Model.scriptURL
import com.prasunmondal.libs.gsheet.serializer.Tech4BytesSerializableLocks

open class CachingUtils {

//    companion object {

        fun <T> get(context: Context, request: ReadAPIs<T>, useCache: Boolean): List<T> {
            val cacheKey = request.getCacheKey()
            val cacheResults = try {
                CentralCacheObj.centralCache.get<T>(context, cacheKey, useCache, false)
            } catch (ex: ClassCastException) {
                arrayListOf(CentralCacheObj.centralCache.get<T>(context, cacheKey, useCache, false))
            }

            LogMe.log("Getting delivery records: Cache Hit: " + (cacheResults != null))
            return if (cacheResults != null) {
                cacheResults as List<T>
            } else {
                synchronized(Tech4BytesSerializableLocks.getLock(cacheKey)!!) {
                    // Synchronized code block
                    println("Synchronized function called with key: $cacheKey")
                    request.execute(scriptURL)
                    val cacheResults = try {
                        CentralCacheObj.centralCache.get<T>(context, cacheKey, useCache, false)
                    } catch (ex: ClassCastException) {
                        arrayListOf(CentralCacheObj.centralCache.get<T>(context, cacheKey, useCache, false))
                    }
                    if(cacheResults == null)
                        listOf()
                    else
                        cacheResults as List<T>
                }
            }
//        }
    }
}