package com.prasunmondal.GSheet.Clients.commons.newSet.APIRequests

import com.prasunmondal.GSheet.Clients.commons.ExecutePostCalls
import com.prasunmondal.GSheet.Clients.commons.newSet.GScript
import com.prasunmondal.GSheet.Clients.post.serializable.PostObjectResponse
import com.prasunmondal.GSheet.StringUtils.StringUtils
import org.json.JSONObject
import java.net.URL
import java.util.function.Consumer

abstract class APIRequests2: GScript() {
    private var uId: String = setUId()
    var opCode: String = ""

    fun getUId(): String {
        return uId
    }
    fun setUId(uId: String = ""): String {
        if(uId.isBlank()) {
            this.uId = StringUtils.generateUniqueString()
        } else {
            this.uId = uId
        }
        return this.uId
    }
//    fun setOpCode(opCode: String) {
//        this.opCode = opCode
//    }


//    fun execute(scriptURL: String): PostObjectResponse {
//        val scriptUrl = URL(scriptURL)
//
//        val c = ExecutePostCalls(scriptUrl, getJSON()) { response -> postExecute(response) }
//        var response = c.execute().get()
//        return PostObjectResponse(response).getObject()
//    }


}