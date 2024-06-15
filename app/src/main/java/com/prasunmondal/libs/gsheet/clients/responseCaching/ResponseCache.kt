package com.prasunmondal.libs.gsheet.clients.responseCaching

import com.prasunmondal.libs.app.contexts.AppContexts
import com.prasunmondal.libs.caching.CentralCacheObj
import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import com.prasunmondal.libs.gsheet.clients.APIResponses.ReadResponse
import com.prasunmondal.libs.logs.instant.terminal.LogMe
import java.io.Serializable

interface ResponseCache : Serializable {

    fun getCacheKey(): String

    companion object {

        fun saveToLocal(requestObj: APIRequests, responseObj: ReadResponse<*>) {
            if (requestObj is ReadAPIs<*>) {
                val cacheKey = requestObj.getCacheKey()
                LogMe.log("Expensive Operation - saving data to local: $cacheKey")
                CentralCacheObj.centralCache.put(cacheKey, responseObj.parsedResponse, false)
            }
        }

        fun isCached(requestObj: APIRequests): Boolean {
            if (requestObj is ReadAPIs<*>) {
                val cacheKey = requestObj.getCacheKey()
                LogMe.log("Expensive Operation - saving data to local: $cacheKey")
                return CentralCacheObj.centralCache.isAvailable(
                    AppContexts.get(),
                    cacheKey,
                    true,
                    false
                )
            }
            return false
        }
    }
}