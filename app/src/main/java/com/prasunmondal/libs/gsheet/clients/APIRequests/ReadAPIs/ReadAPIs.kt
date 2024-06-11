package com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs

import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.responseCaching.APIRequestsTemplates
import com.prasunmondal.libs.gsheet.clients.APIResponses.APIResponse
import com.prasunmondal.libs.gsheet.clients.APIResponses.ReadResponse
import com.prasunmondal.libs.gsheet.clients.responseCaching.ResponseCache
import com.prasunmondal.libs.gsheet.serializer.parsers.Parser

abstract class ReadAPIs<T> : APIRequests(), ResponseCache {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    open lateinit var classTypeForResponseParsing: Class<T>
    var cacheData: Boolean = true

    fun sheetId(sheetId: String) {
        this.sheetId = sheetId
    }

    fun tabName(tabName: String) {
        this.tabName = tabName
    }

    override fun getCacheKey(): String {
        return "${this.sheetId}\\${this.tabName}\\${getJSON()}"
    }

    override fun <T> defaultInitialize(
        request: APIRequests,
        reqValues: APIRequestsTemplates<T>
    ): APIRequests {
        var request_ = request as ReadAPIs<T>
        super.defaultInitialize(request, reqValues)
        request_.sheetId = reqValues.sheetURL
        request_.tabName = reqValues.tabname
        request_.classTypeForResponseParsing = reqValues.classTypeForResponseParsing
        request_.cacheData = cacheData
        return request
    }

    override fun prepareResponse(
        requestObj: APIRequests,
        receivedResponseObj: APIResponse,
        buildingResponseObj: APIResponse?
    ): APIResponse {
        var buildingResponseObj_ = buildingResponseObj as ReadResponse<T>?
        if (buildingResponseObj_ == null)
            buildingResponseObj_ = ReadResponse()

        super.prepareResponse(requestObj, receivedResponseObj, buildingResponseObj)
        buildingResponseObj_.sheetId = this.sheetId
        buildingResponseObj_.tabName = this.tabName
        buildingResponseObj_.parsedResponse = Parser.convertJsonArrayStringToJavaObjList(
            receivedResponseObj.content,
            (requestObj as ReadAPIs<T>).classTypeForResponseParsing
        ) as List<T>
        return buildingResponseObj_
    }
}