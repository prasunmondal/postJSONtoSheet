package com.prasunmondal.GSheet.serializer

import com.prasunmondal.GSheet.AppContexts.AppContexts
import com.prasunmondal.GSheet.Clients.commons.APIResponse
import com.prasunmondal.GSheet.Clients.delete.Delete
import com.prasunmondal.GSheet.Clients.get.Get
import com.prasunmondal.GSheet.Clients.get.GetResponse
import com.prasunmondal.GSheet.Clients.post.serializable.PostObject
import com.prasunmondal.GSheet.Logs.LogMe
import com.prasunmondal.GSheet.Tests.ProjectConfig
import com.tech4bytes.extrack.centralCache.CentralCache
import com.tech4bytes.mbrosv3.Utils.DB.clients.GScript
import com.tech4bytes.mbrosv3.Utils.DB.clients.get.ByQuery.GetByQuery
import java.lang.reflect.Type

abstract class Tech4BytesSerializable<T : Any> : java.io.Serializable {

    @Transient
    var scriptURL: String

    @Transient
    var sheetURL: String

    @Transient
    var tabname: String

    @Transient
    var query: String?

    @Transient
    var cacheObjectType: Type

    @Transient
    var appendInServer: Boolean

    @Transient
    var appendInLocal: Boolean

    @Transient
    var getEmptyListIfEmpty: Boolean

    @Transient
    var cacheTag: String = "default"

    constructor(
        scriptURL: String,
        sheetURL: String,
        tabname: String,
        query: String? = null,
        cacheObjectType: Type,
        appendInServer: Boolean,
        appendInLocal: Boolean,
        getEmptyListIfNoResultsFoundInServer: Boolean = false,
        cacheTag: String = "default") {
        this.scriptURL = scriptURL
        this.sheetURL = sheetURL
        this.tabname = tabname
        this.query = query
        this.cacheObjectType = cacheObjectType
        this.appendInServer = appendInServer
        this.appendInLocal = appendInLocal
        this.getEmptyListIfEmpty = getEmptyListIfNoResultsFoundInServer
        this.cacheTag = cacheTag
    }

    fun get(
        useCache: Boolean = true,
        getEmptyListIfEmpty: Boolean = false,
        cacheTag: String = this.cacheTag
    ): List<T> {
        val cacheKey = getFilterName(cacheTag)
        LogMe.log("Getting records: $cacheKey")
        val cacheResults = try {
            CentralCache.get<T>(AppContexts.get(), cacheKey, useCache)
        } catch (ex: ClassCastException) {
            arrayListOf(CentralCache.get<T>(AppContexts.get(), cacheKey, useCache))
        }

        LogMe.log("Getting delivery records: Cache Hit: " + (cacheResults != null))
        return if (cacheResults != null) {
            cacheResults as List<T>
        } else {
            synchronized(Tech4BytesSerializableLocks.getLock(cacheKey)!!) {
                // Synchronized code block
                println("Synchronized function called with key: $cacheKey")

                val r = getFromServer()
//                if(r.size == 1) {

//                    parseAndSaveToCache(r.get(0).content, cacheKey)
//                }
                r.forEach { k,v ->
                    LogMe.log(v.content)
                    parseAndSaveToCache2(v.content, cacheKey).forEach {
                        LogMe.log(it.toString())
                    }
                }

                // TODO

                listOf()
            }
        }
    }

    private fun parseNGetResponse2(rawResponse: String): List<T> {
        LogMe.log(rawResponse)
        var parsedResponse = APIResponse.JsonArrayToObjectArray<T>(rawResponse)
        LogMe.log(parsedResponse.size)
        if ((getEmptyListIfEmpty || this.getEmptyListIfEmpty) && parsedResponse.isEmpty())
            return listOf()
        parsedResponse = filterResults(parsedResponse)
        parsedResponse = sortResults(parsedResponse)
        return parsedResponse
    }

    fun isDataAvailable(cacheTag: String = "default"): Boolean {
        val useCache = true
        val cacheKey = getFilterName(cacheTag)
        LogMe.log("Getting records: " + cacheKey)
        val cacheResults = try {
            CentralCache.get<T>(AppContexts.get(), cacheKey, useCache)
        } catch (ex: ClassCastException) {
            arrayListOf(CentralCache.get<T>(AppContexts.get(), cacheKey, useCache))
        }
        return cacheResults != null
    }

    private fun getFromServer(): MutableMap<String, APIResponse> {
        LogMe.log("Expensive Operation - get data from server: $sheetURL - $tabname")
        val requestObj = if(query == null || query!!.isEmpty()) {
            Get.builder()
                .scriptId(scriptURL)
                .sheetId(sheetURL)
                .tabName(tabname)
                .build()
        } else {
            GetByQuery.builder()
                .scriptId(scriptURL)
                .sheetId(sheetURL)
                .tabName(tabname)
                .query(query!!)
                .build()
        }
        GScript.add(requestObj)
        val response = GScript.execute(ProjectConfig.dBServerScriptURL)
        return response
    }

    fun parseAndSaveToCache2(response: String, cacheKey: String? = null): List<T> {
        val resolvedCacheKey = if(cacheKey.isNullOrEmpty()) {
            getFilterName()
        } else {
            cacheKey
        }
        val parsedData = parseNGetResponse2(response)
        CentralCache.put(resolvedCacheKey, parsedData)
        LogMe.log("Put Complete")
        LogMe.log("cacheKey: $resolvedCacheKey")
        LogMe.log(parsedData.size)
        return parsedData
    }

    private fun getFilterName(cacheTag: String = "default"): String {
//        var callerClassName = ClassDetailsUtils.getCaller(ClassDetailsUtils.getCaller())
        return "$sheetURL/$tabname/$cacheTag"
    }

    open fun <T : Any> filterResults(list: ArrayList<T>): ArrayList<T> {
        return list
    }

    open fun <T : Any> filterResults(list: List<T>): List<T> {
        return list
    }

    open fun <T : Any> sortResults(list: ArrayList<T>): ArrayList<T> {
        return list
    }

    open fun <T : Any> sortResults(list: List<T>): List<T> {
        return list
    }

    fun <T : Any> saveToLocalThenServer(dataObject: T) {
        saveToLocal(dataObject)
        saveToServer(dataObject)
    }

    /*
    *
    *
    *
    * Save Data Code
    *
    *
    *
     */
    fun saveToServerThenLocal(dataObject: T) {
        saveToServer(dataObject)
        saveToLocal(dataObject)
    }

    /**
     * dataObject: Data to save
     * cacheKey: cacheKey used to identify the cache object, pass null to generate the cacheKey
     */
    fun saveToLocal(dataObject: Any?, cacheKey: String? = getFilterName()) {
        var finalCacheKey = cacheKey
        if(cacheKey == null) {
            finalCacheKey = getFilterName()
        }
        LogMe.log("Expensive Operation - saving data to local: $finalCacheKey")
        if (finalCacheKey == null) {
            finalCacheKey = getFilterName()
        }
        if (dataObject == null) {
            CentralCache.put(finalCacheKey, dataObject)
            return
        }

        val dataToSave = if (appendInLocal) {
            var dataList = get() as ArrayList
            dataList.addAll(arrayListOf(dataObject as T))
            dataList = filterResults(dataList)
            dataList = sortResults(dataList)
            dataList
        } else {
            dataObject
        }
        CentralCache.put(finalCacheKey, dataToSave)
    }

    fun <T> saveToServer(obj: T) {
        LogMe.log("Expensive Operation - saving data to server: $sheetURL - $tabname")
        if (!appendInServer) {
            deleteDataFromServer()
        }

        val requestObj = PostObject.builder()
            .scriptId(scriptURL)
            .sheetId(sheetURL)
            .tabName(tabname)
            .dataObject(obj as Any)
            .build()

        GScript.add(requestObj)
        GScript.execute(ProjectConfig.dBServerScriptURL)
    }


    /*
    *
    *
    *
    *
    * Deletion Codes
    *
    *
    *
     */

    fun deleteData() {
        deleteDataFromServer()
        deleteDataFromLocal()
    }

    fun deleteDataFromLocal() {
        saveToLocal(null)
    }

    fun deleteDataFromServer() {
        val requestObj = Delete.builder()
            .scriptId(scriptURL)
            .sheetId(sheetURL)
            .tabName(tabname)
            .build()

        GScript.add(requestObj)
        GScript.execute(ProjectConfig.dBServerScriptURL)
    }
}