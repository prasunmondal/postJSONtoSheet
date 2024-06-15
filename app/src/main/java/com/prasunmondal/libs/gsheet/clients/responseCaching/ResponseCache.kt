package com.prasunmondal.libs.gsheet.clients.responseCaching

import com.prasunmondal.libs.caching.CentralCacheObj
import com.prasunmondal.libs.logs.instant.terminal.LogMe
import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs.ReadAPIs
import com.prasunmondal.libs.gsheet.clients.APIResponses.APIResponse
import com.tech4bytes.extrack.centralCache.CentralCache
import java.io.Serializable

interface ResponseCache : Serializable {

    fun getCacheKey(): String

    companion object {

        fun saveToLocal(requestObj: APIRequests, responseObj: APIResponse) {
            if (requestObj is ReadAPIs<*>) {
                val cacheKey = requestObj.getCacheKey()
                LogMe.log("Expensive Operation - saving data to local: $cacheKey")
                CentralCacheObj.centralCache.put(cacheKey, responseObj, false)
            }
        }
    }
}