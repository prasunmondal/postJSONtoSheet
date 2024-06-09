package com.prasunmondal.libs.gsheet.clients.APIRequests

import com.prasunmondal.libs.StringUtils.StringUtils
import com.prasunmondal.libs.gsheet.clients.APIResponses.APIResponse
import com.prasunmondal.libs.gsheet.clients.GScript

abstract class APIRequests : GScript() {
    private var uId: String = setUId()
    var opCode: String = ""

    fun getUId(): String {
        return uId
    }

    fun setUId(uId: String = ""): String {
        if (uId.isBlank()) {
            this.uId = StringUtils.generateUniqueString()
        } else {
            this.uId = uId
        }
        return this.uId
    }

    open fun prepareResponse(
        requestObj: APIRequests,
        receivedResponseObj: APIResponse,
        buildingResponseObj: APIResponse? = null
    ): APIResponse {
        var buildingResponseObj_ = buildingResponseObj
        if (buildingResponseObj_ == null)
            buildingResponseObj_ = APIResponse()
        buildingResponseObj_.affectedRows = receivedResponseObj.statusCode
        buildingResponseObj_.statusCode = receivedResponseObj.statusCode
        buildingResponseObj_.content = receivedResponseObj.content
        buildingResponseObj_.opId = receivedResponseObj.opId
        buildingResponseObj_.logs = receivedResponseObj.logs
        return buildingResponseObj_
    }
}