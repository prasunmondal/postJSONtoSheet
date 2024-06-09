package com.prasunmondal.libs.gsheet.clients.APIRequests.ReadAPIs

import com.prasunmondal.libs.gsheet.clients.APIRequests.APIRequests
import com.prasunmondal.libs.gsheet.clients.APIResponses.APIResponse
import com.prasunmondal.libs.gsheet.clients.APIResponses.CreateResponse
import com.prasunmondal.libs.gsheet.clients.APIResponses.ReadResponse
import com.prasunmondal.libs.gsheet.serializer.parsers.Parser

abstract class ReadAPIs<T> : APIRequests() {
    lateinit var sheetId: String
    lateinit var tabName: String
    lateinit var data: String
    lateinit var classTypeForResponseParsing: Class<T>

    fun sheetId(sheetId: String) {
        this.sheetId = sheetId
    }

    fun tabName(tabName: String) {
        this.tabName = tabName
    }

//    private fun <T> prepareResponse(apiRequest: APIRequests?, apiResponse: APIResponse) {
//        val t = ReadResponse<T>()
//        super.prepareResponse(t, apiResponse)
//        val apiRequest_ = apiRequest as ReadAPIs<*>
//        t.sheetId = this.sheetId
//        t.tabName = this.tabName
//        t.parsedResponse = Parser.convertJsonArrayStringToJavaObjList(apiResponse.content, apiRequest_.classTypeForResponseParsing) as List<T>
//    }

    override fun prepareResponse(requestObj: APIRequests, receivedResponseObj: APIResponse, buildingResponseObj: APIResponse?): APIResponse {
        var buildingResponseObj_ = buildingResponseObj as ReadResponse<T>?
        if(buildingResponseObj_ == null)
            buildingResponseObj_ = ReadResponse()

        super.prepareResponse(requestObj, receivedResponseObj, buildingResponseObj)
        buildingResponseObj_.sheetId = this.sheetId
        buildingResponseObj_.tabName = this.tabName
        buildingResponseObj_.parsedResponse = Parser.convertJsonArrayStringToJavaObjList(receivedResponseObj.content, (requestObj as ReadAPIs<T>).classTypeForResponseParsing) as List<T>
        return buildingResponseObj_
    }
}